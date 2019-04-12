import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleResultSet;

public class AddSport extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conn=null;
    OraclePreparedStatement pst=null;
    OracleResultSet rs=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddSport frame = new AddSport("1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddSport(String regno) {
		setTitle("Add sport");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 381, 203);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAddASport = new JLabel("Add a sport to your profile:");
		lblAddASport.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblAddASport.setBounds(12, 12, 339, 33);
		contentPane.add(lblAddASport);
		
		JLabel lblSport = new JLabel("Sport");
		lblSport.setBounds(12, 57, 70, 17);
		contentPane.add(lblSport);
		
		String sport[] = {"Tennis", "Football", "Cricket", "Basketball", "Table Tennis"};
		JComboBox<String> cbSport = new JComboBox(sport);
		cbSport.setToolTipText("Select the sport you want to add");
		cbSport.setBounds(136, 52, 215, 27);
		contentPane.add(cbSport);
		
		JLabel lblSkill = new JLabel("Skill");
		lblSkill.setBounds(12, 100, 70, 17);
		contentPane.add(lblSkill);
		
		Integer skill[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		@SuppressWarnings("unchecked")
		JComboBox<Integer> cbSkill = new JComboBox(skill);
		cbSkill.setToolTipText("How well do you play this sport?");
		cbSkill.setBounds(136, 91, 64, 27);
		contentPane.add(cbSkill);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = JavaConnectDB.ConnectDB();
				try {
					pst = (OraclePreparedStatement) conn.prepareStatement("insert into plays values(?, ?, ?)");
					pst.setString(1, regno);
					Integer x = (cbSport.getSelectedIndex());
					x++;//since getSelectedItem() returns int starting from 0, our sport_id's start from 1
					
					pst.setString(2,x.toString());
					pst.setString(3, ((Integer)cbSkill.getSelectedIndex()).toString());
					
					rs = (OracleResultSet)pst.executeQuery();
					
					JOptionPane.showMessageDialog(null, "Sport added successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
					dispose();
					
				}
				catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, "This sport is already listed in your profile!", "Cannot add sport", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		btnAdd.setBounds(253, 128, 106, 33);
		contentPane.add(btnAdd);
		
		
	}

}
