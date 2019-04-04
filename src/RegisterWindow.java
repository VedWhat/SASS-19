import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleResultSet;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

@SuppressWarnings({ "serial", "unused" })
public class RegisterWindow extends JFrame {
	private JPanel contentPane;
	private JTextField tfReg_no;
	private JPasswordField tfPassword;
	private JPasswordField tfCPassword;
	private JTextField tfName;
	private JTextField tfSec_id;
	
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
					RegisterWindow frame = new RegisterWindow();
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
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public RegisterWindow() {
		super("Register for SASS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 408, 535);
		contentPane = new JPanel();
		//setAlwaysOnTop(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
		conn = JavaConnectDB.ConnectDB();
		
		JLabel lblEnterDetails = new JLabel("Student details");
		lblEnterDetails.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblEnterDetails.setBounds(27, 12, 260, 26);
		contentPane.add(lblEnterDetails);
		
		JLabel lblRegistrationNumber = new JLabel("Registration number");
		lblRegistrationNumber.setBounds(27, 50, 150, 17);
		contentPane.add(lblRegistrationNumber);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 79, 150, 17);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setBounds(27, 108, 150, 17);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(27, 137, 150, 17);
		contentPane.add(lblName);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(27, 166, 150, 17);
		contentPane.add(lblGender);
		
		JLabel lblSectionid = new JLabel("Section-ID");
		lblSectionid.setBounds(27, 195, 150, 17);
		contentPane.add(lblSectionid);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(27, 224, 150, 17);
		contentPane.add(lblYear);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(27, 253, 150, 17);
		contentPane.add(lblAddress);
		
		tfReg_no = new JTextField();
		tfReg_no.setBounds(189, 45, 161, 27);
		contentPane.add(tfReg_no);
		tfReg_no.setColumns(10);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(189, 74, 161, 27);
		contentPane.add(tfPassword);
		
		tfCPassword = new JPasswordField();
		tfCPassword.setToolTipText("Password must be greater than 3 characters");
		tfCPassword.setBounds(189, 103, 161, 27);
		contentPane.add(tfCPassword);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(189, 132, 161, 27);
		contentPane.add(tfName);
		
		tfSec_id = new JTextField();
		tfSec_id.setToolTipText("Your section ID is BRANCH-SECTION");
		tfSec_id.setBounds(189, 190, 161, 27);
		contentPane.add(tfSec_id);
		tfSec_id.setColumns(10);
		
		String sex[] = {"M", "F"};
		JComboBox comboBoxGender = new JComboBox(sex);
		comboBoxGender.setBounds(189, 161, 161, 26);
		contentPane.add(comboBoxGender);
		
		String cyear[] = {"1", "2", "3", "4"};
		JComboBox comboBoxYear = new JComboBox(cyear);
		comboBoxYear.setBounds(189, 219, 161, 26);
		contentPane.add(comboBoxYear);
		
		JTextArea tfAddress = new JTextArea();
		tfAddress.setToolTipText("Your address must not exceed 19 characters");
		tfAddress.setBounds(189, 248, 161, 45);
		contentPane.add(tfAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 305, 330, 10);
		contentPane.add(separator);
		
		JLabel lblSportDetails = new JLabel("Sport");
		lblSportDetails.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblSportDetails.setBounds(27, 305, 133, 26);
		contentPane.add(lblSportDetails);
		
		
		JCheckBox chckbxTennis = new JCheckBox("Tennis");
		chckbxTennis.setBounds(27, 327, 133, 26);
		contentPane.add(chckbxTennis);
		
		JCheckBox chckbxFootball = new JCheckBox("Football");
		chckbxFootball.setBounds(27, 348, 133, 17);
		contentPane.add(chckbxFootball);
		
		JCheckBox chckbxCricket = new JCheckBox("Cricket");
		chckbxCricket.setBounds(27, 365, 133, 17);
		contentPane.add(chckbxCricket);
		
		JCheckBox chckbxFutsal = new JCheckBox("Futsal");
		chckbxFutsal.setBounds(27, 377, 133, 24);
		contentPane.add(chckbxFutsal);
		
		JCheckBox chckbxTableTennis = new JCheckBox("Table Tennis");
		chckbxTableTennis.setToolTipText("");
		chckbxTableTennis.setBounds(27, 394, 133, 24);
		contentPane.add(chckbxTableTennis);		
		
		Integer skillLevel[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		JComboBox comboBoxTennis = new JComboBox(skillLevel);
		comboBoxTennis.setToolTipText("How well do you play?");
		comboBoxTennis.setBounds(217, 334, 70, 17);
		contentPane.add(comboBoxTennis);
		
		JComboBox comboBoxFootball = new JComboBox(skillLevel);
		comboBoxFootball.setToolTipText("How well do you play?");
		comboBoxFootball.setBounds(217, 348, 70, 17);
		contentPane.add(comboBoxFootball);
		
		JComboBox comboBoxCricket = new JComboBox(skillLevel);
		comboBoxCricket.setToolTipText("How well do you play?");
		comboBoxCricket.setBounds(217, 365, 70, 17);
		contentPane.add(comboBoxCricket);
		
		JComboBox comboBoxFutsal = new JComboBox(skillLevel);
		comboBoxFutsal.setToolTipText("How well do you play?");
		comboBoxFutsal.setBounds(217, 381, 70, 17);
		contentPane.add(comboBoxFutsal);
		
		JComboBox comboBoxTableTennis = new JComboBox(skillLevel);
		comboBoxTableTennis.setToolTipText("How well do you play?");
		comboBoxTableTennis.setBounds(217, 398, 70, 17);
		contentPane.add(comboBoxTableTennis);
		
		JLabel lblSkill = new JLabel("Skill");
		lblSkill.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblSkill.setBounds(217, 305, 133, 26);
		contentPane.add(lblSkill);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = JavaConnectDB.ConnectDB();
				boolean flag = true;
				try {
					pst = (OraclePreparedStatement) conn.prepareStatement("insert into Student(reg_no, name, sex, sec_id, cyear, address, engaged, rel_factor) values(?,?,?,?,?,?,'N', 0)");
					String regno = tfReg_no.getText();
					
					try {
						Statement st = conn.createStatement();
						ResultSet r = st.executeQuery("Select reg_no from student");
						while(r.next()) {
							if(regno.equalsIgnoreCase(r.getString(1))) {
								flag = false;
								break;
							}
						}
						if(!flag)
							JOptionPane.showMessageDialog(null, "User already registered.", "Invalid user", JOptionPane.WARNING_MESSAGE);
						
					}catch(SQLException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
					pst.setString(1, regno);
					pst.setString(2, tfName.getText());
					pst.setString(3, comboBoxGender.getItemAt(comboBoxGender.getSelectedIndex()).toString());
					String secid = tfSec_id.getText();
					flag = false;
					try {
						Statement st = conn.createStatement();
						ResultSet r = st.executeQuery("Select sec_id from section");
						while(r.next()) {
							if(secid.equalsIgnoreCase(r.getString(1))) {
								flag = true;//section found
								break;
							}
						}
						if(!flag)
							JOptionPane.showMessageDialog(null, "Section not found", "Invalid section", JOptionPane.WARNING_MESSAGE);
						
					}catch(SQLException e1) {
						JOptionPane.showMessageDialog(null, "Section not found!","Error" , JOptionPane.ERROR_MESSAGE);
					}
					pst.setString(4, secid);
					pst.setString(5, comboBoxYear.getItemAt(comboBoxYear.getSelectedIndex()).toString());
					pst.setString(6, tfAddress.getText());
					
					String p = tfPassword.getText();
					String cp = tfCPassword.getText();
					boolean pflag = true;
					if(!(p.equals(cp)&&p.length()>3)) {
						flag = false;
						JOptionPane.showMessageDialog(null, "Improper password.\nPassword must be greater than 3 characters.\nPasswords must match", "Password error", JOptionPane.WARNING_MESSAGE);
						throw new SQLException("Password error");
					}
	
					rs = (OracleResultSet)pst.executeQuery();
					
					//to insert into LOGIN, reusing pst, rs
					pst = (OraclePreparedStatement) conn.prepareStatement("insert into login values (?, ?)");
					pst.setString(1, regno );
						
					pst.setString(2,p);
					
					if(flag&&pflag)//Login table is populated only if flag is true (flag=false whenever there is a constraint error)
						rs = (OracleResultSet)pst.executeQuery();
					
					//to insert into plays, reusing pst, rs
					
					pst = (OraclePreparedStatement) conn.prepareStatement("insert into plays values (?, ?, ?)");
					pst.setString(1, regno );
					if(chckbxTennis.isSelected()) {
						pst.setString(2, "1");
						pst.setString(3, comboBoxTennis.getItemAt(comboBoxTennis.getSelectedIndex()).toString());
						pst.executeQuery();
					}
					if(chckbxFootball.isSelected()) {
						pst.setString(2, "2");
						pst.setString(3, comboBoxFootball.getItemAt(comboBoxFootball.getSelectedIndex()).toString());
						pst.executeQuery();
					}
					if(chckbxCricket.isSelected()) {
						pst.setString(2, "3");
						pst.setString(3, comboBoxCricket.getItemAt(comboBoxCricket.getSelectedIndex()).toString());
						pst.executeQuery();
					}
					if(chckbxFutsal.isSelected()) {
						pst.setString(2, "4");
						pst.setString(3, comboBoxFutsal.getItemAt(comboBoxFutsal.getSelectedIndex()).toString());
						pst.executeQuery();
					}
					if(chckbxTennis.isSelected()) {
						pst.setString(2, "5");
						pst.setString(3, comboBoxTableTennis.getItemAt(comboBoxTableTennis.getSelectedIndex()).toString());
						pst.executeQuery();
					}
					
					JOptionPane.showMessageDialog(null, "Registered successfully!", "Success", JOptionPane.PLAIN_MESSAGE);
					
					
					}catch(SQLException ex) {
						if(flag)
							JOptionPane.showMessageDialog(null, ex.getMessage(), "SQL Exception", JOptionPane.WARNING_MESSAGE);
							//ex.printStackTrace();
					}
				
			}
		});
		btnRegister.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnRegister.setBounds(137, 448, 150, 31);
		contentPane.add(btnRegister);
		
		
	}

}
