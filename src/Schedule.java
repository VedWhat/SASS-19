import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.CardLayout;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.border.SoftBevelBorder;

import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleResultSet;
import oracle.sql.NUMBER;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;

@SuppressWarnings("unused")
public class Schedule extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	static String aid;
	Connection conn=null;
    OraclePreparedStatement pst=null;
    OracleResultSet rs=null;
    private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {//this serves merely to run this as a standalone, can be deleted.
			public void run() {
				try {
					//do something?
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public Schedule(String regno) {
		setTitle("Let's play - SASS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		String currUser = null;
		JLabel lblInfo;
		conn = JavaConnectDB.ConnectDB();
		JPanel mainPanel = new JPanel();
		//Logged in as ?
		lblInfo = new JLabel("Logged in as : ");
		lblInfo.setFont(new Font("Courier 10 Pitch", Font.BOLD, 19));
		lblInfo.setBounds(24, 0, 453, 42);
		try {
			pst = (OraclePreparedStatement) conn.prepareStatement("select name, reg_no, sec_id, cyear from student where reg_no =?");
			pst.setString(1, regno);
			rs = (OracleResultSet)pst.executeQuery();
			rs.next();
			currUser = rs.getString(1);
			OraclePreparedStatement gsp = (OraclePreparedStatement) conn.prepareStatement("select distinct name from sport natural join plays where reg_no=?" );
			gsp.setString(1, regno);
			OracleResultSet gsr = (OracleResultSet)gsp.executeQuery();
			String spInfo = "<html><b>Your sports:</b><br/>";
			while(gsr.next()) {
				spInfo = spInfo + gsr.getString(1) + "<br/";
			}
			
			JLabel lblUserInfo = new JLabel("<html>"+currUser+"<br/>"+rs.getString(2)+"<br/>"+rs.getString(3)+"<br/>Year: 0"+rs.getString(4)+"</html>");
			
			JLabel lblSportInfo = new JLabel(spInfo);
			
			lblUserInfo.setFont(new Font("URW Gothic L", Font.ITALIC, 17));
			lblUserInfo.setBounds(24, 37, 233, 115);
			mainPanel.add(lblUserInfo);
			
			lblSportInfo.setFont(new Font("URW Gothic L", Font.ITALIC, 17));
			lblSportInfo.setBounds(255, 37, 233, 115);
			mainPanel.add(lblSportInfo);
			
		}
		
		catch(SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "SQLException", JOptionPane.ERROR_MESSAGE );
			e.printStackTrace();
		}
		
		
		
		
		contentPane.add(mainPanel, "Schedule");
		
		JPanel resultPanel = new JPanel();
		contentPane.add(resultPanel, "GameSet");
		
		mainPanel.setLayout(null);
		resultPanel.setLayout(null);
		
		JLabel lblItsAMatch = new JLabel("It's a Match!");
		lblItsAMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblItsAMatch.setFont(new Font("Courier 10 Pitch", Font.BOLD, 23));
		lblItsAMatch.setBounds(12, 0, 476, 43);
		resultPanel.add(lblItsAMatch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 53, 488, 319);
		resultPanel.add(scrollPane);
		
		
		
		
		JPanel btnpanel = new JPanel();
		btnpanel.setLayout(null);
		btnpanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnpanel.setBounds(0, 385, 500, 54);
		resultPanel.add(btnpanel);
		
		JButton btnNextF = new JButton("Next");
		btnNextF.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnNextF.setBounds(393, 12, 95, 33);
		btnpanel.add(btnNextF);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnCancel.setBounds(271, 12, 110, 33);
		btnpanel.add(btnCancel);
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPanel.setBounds(0, 385, 500, 54);
		mainPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		CardLayout cl = (CardLayout)contentPane.getLayout();
		JButton btnNext = new JButton("Next");
		
		btnNext.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnNext.setBounds(393, 12, 95, 33);
		buttonPanel.add(btnNext);
		
		mainPanel.add(lblInfo);
		
		
		JButton btnPrevious = new JButton("Cancel");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPrevious.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnPrevious.setBounds(271, 12, 110, 33);
		buttonPanel.add(btnPrevious);
		
		JButton btnAddSport = new JButton("Add Sport");
		btnAddSport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSport newSport = new AddSport(regno);
				newSport.setVisible(true);
				newSport.setAlwaysOnTop(true);
			}
		});
		btnAddSport.setBounds(371, 174, 106, 33);
		mainPanel.add(btnAddSport);
		
		JLabel lblSelectSport = new JLabel("Select sport:");
		lblSelectSport.setFont(new Font("URW Gothic L", Font.PLAIN, 17));
		lblSelectSport.setBounds(24, 286, 207, 30);
		mainPanel.add(lblSelectSport);
		
		String sport[] = {"Tennis", "Football", "Cricket", "Futsal", "Table Tennis"};
		ArrayList<String> arena = new ArrayList<String>(4);
		
		
		@SuppressWarnings("rawtypes")
		JComboBox comboBoxSport = new JComboBox(sport);
		comboBoxSport.setToolTipText("Sport once selected will be final for current instance!");
		
		
		
		comboBoxSport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sid = ((Integer)(comboBoxSport.getSelectedIndex()+1)).toString();
				boolean flag = false;
				try {
					pst = (OraclePreparedStatement)conn.prepareStatement("select aid, location from arena natural join offered_by where sid = ?");
					pst.setString(1, sid);
					rs = (OracleResultSet)pst.executeQuery();
					while(rs.next()) {
						arena.add(rs.getString(2));
					}
					
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
				
				String[] arrayArena = arena.toArray(new String[arena.size()]);
				
				JComboBox comboBoxArena = new JComboBox(arrayArena);
				aid =((Integer)(comboBoxArena.getSelectedIndex()+1)).toString();
				comboBoxArena.setBounds(270, 328, 207, 29);
				mainPanel.add(comboBoxArena);
				comboBoxSport.setEnabled(false);
				
				//btnNext must work only after the arena has been selected:
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cl.next(contentPane);
					}
				});
				
				
			}
		});
		
		comboBoxSport.setBounds(270, 286, 207, 29);
		mainPanel.add(comboBoxSport);
		
		JLabel lblSelectArena = new JLabel("Select arena:");
		lblSelectArena.setFont(new Font("URW Gothic L", Font.PLAIN, 17));
		lblSelectArena.setBounds(24, 328, 207, 30);
		mainPanel.add(lblSelectArena);
		
		JLabel lblLearntToPlay = new JLabel("Learnt to play something new?");
		lblLearntToPlay.setFont(new Font("URW Gothic L", Font.PLAIN, 17));
		lblLearntToPlay.setBounds(25, 176, 300, 30);
		mainPanel.add(lblLearntToPlay);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(24, 218, 453, 3);
		mainPanel.add(separator_1);
		
		JLabel lblReady = new JLabel("Ready?");
		lblReady.setFont(new Font("Courier 10 Pitch", Font.BOLD, 19));
		lblReady.setBounds(24, 233, 453, 41);
		mainPanel.add(lblReady);
		
		try {
			//regno has regno, sid will have sid, aid is static
			String sid = ((Integer)(comboBoxSport.getSelectedIndex()+1)).toString();
			//get DAY and TIME
			CallableStatement cstmt = conn.prepareCall("{call getDay(?, ?)}");
			cstmt.setString(1, "sysdate");
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.executeUpdate();
			String day = cstmt.getString(2);
			
			
			pst = (OraclePreparedStatement)conn.prepareStatement("(select reg_no, name, sec_id, cyear" + 
					"from student natural join section natural join timeslot natural join plays " + 
					"where ?=? and sid = ?) minus (select reg_no, name, sec_id, cyear from student natural join section natural join timeslot where reg_no = ?)");
			pst.setString(1, day);
			pst.setString(2, "(select calMatchHalf from dual");
			pst.setString(3, sid);
			pst.setString(4, regno);
			rs = (OracleResultSet)pst.executeQuery();
			
			while(rs.next()) {
				
			}
			
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		//String sid has sportid and 
		
		
		
		
		
		
		
		
		
		
	}
}
