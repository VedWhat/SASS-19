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
	public RegisterWindow() {
		super("Register for SASS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 408, 535);
		contentPane = new JPanel();
		//setAlwaysOnTop(true);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);
		
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
		tfCPassword.setBounds(189, 103, 161, 27);
		contentPane.add(tfCPassword);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(189, 132, 161, 27);
		contentPane.add(tfName);
		
		JRadioButton rdbtnMale = new JRadioButton("MALE");
		rdbtnMale.setBounds(189, 162, 71, 24);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("FEMALE");
		rdbtnFemale.setBounds(260, 162, 90, 24);
		contentPane.add(rdbtnFemale);
		
		tfSec_id = new JTextField();
		tfSec_id.setToolTipText("Your section ID is BRANCH-SECTION");
		tfSec_id.setBounds(189, 190, 161, 27);
		contentPane.add(tfSec_id);
		tfSec_id.setColumns(10);
		
		JRadioButton radioButton = new JRadioButton("4");
		radioButton.setBounds(310, 220, 40, 24);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("1");
		radioButton_1.setBounds(189, 220, 40, 24);
		contentPane.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("2");
		radioButton_2.setBounds(229, 220, 40, 24);
		contentPane.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("3");
		radioButton_3.setBounds(270, 220, 40, 24);
		contentPane.add(radioButton_3);
		
		JTextArea tfAddress = new JTextArea();
		tfAddress.setToolTipText("Your address must not exceed 19 characters");
		tfAddress.setBounds(189, 253, 161, 40);
		contentPane.add(tfAddress);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 305, 330, 10);
		contentPane.add(separator);
		
		JLabel lblSportDetails = new JLabel("Sport");
		lblSportDetails.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblSportDetails.setBounds(27, 305, 133, 26);
		contentPane.add(lblSportDetails);
		
		JCheckBox chckbxFootball = new JCheckBox("Football");
		chckbxFootball.setBounds(27, 348, 133, 17);
		contentPane.add(chckbxFootball);
		
		JCheckBox chckbxTableTennis = new JCheckBox("Table Tennis");
		chckbxTableTennis.setToolTipText("");
		chckbxTableTennis.setBounds(27, 394, 133, 24);
		contentPane.add(chckbxTableTennis);
		
		JCheckBox chckbxCricket = new JCheckBox("Cricket");
		chckbxCricket.setBounds(27, 365, 133, 17);
		contentPane.add(chckbxCricket);
		
		JCheckBox chckbxFootsal = new JCheckBox("Futsal");
		chckbxFootsal.setBounds(27, 377, 133, 24);
		contentPane.add(chckbxFootsal);
		
		JCheckBox chckbxTennis = new JCheckBox("Tennis");
		chckbxTennis.setBounds(27, 327, 133, 26);
		contentPane.add(chckbxTennis);
		
		JLabel lblSkill = new JLabel("Skill");
		lblSkill.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		lblSkill.setBounds(217, 305, 133, 26);
		contentPane.add(lblSkill);
		
		Integer skillLevel[] = {null, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		
		JComboBox comboBoxTennis = new JComboBox(skillLevel);
		comboBoxTennis.setBounds(217, 334, 70, 17);
		contentPane.add(comboBoxTennis);
		
		JComboBox comboBoxFootball = new JComboBox(skillLevel);
		comboBoxFootball.setBounds(217, 348, 70, 17);
		contentPane.add(comboBoxFootball);
		
		JComboBox comboBoxCricket = new JComboBox(skillLevel);
		comboBoxCricket.setBounds(217, 365, 70, 17);
		contentPane.add(comboBoxCricket);
		
		JComboBox comboBoxFutsal = new JComboBox(skillLevel);
		comboBoxFutsal.setBounds(217, 381, 70, 17);
		contentPane.add(comboBoxFutsal);
		
		JComboBox comboBoxTableTennis = new JComboBox(skillLevel);
		comboBoxTableTennis.setBounds(217, 398, 70, 17);
		contentPane.add(comboBoxTableTennis);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = JavaConnectDB.ConnectDB();
				try {
					pst = (OraclePreparedStatement) conn.prepareStatement("insert into Student(reg_no, name, sex, sec_id, cyear, address, engaged, rel_factor) values(?,?,?,?,?,?,'N', 0)");
					pst.setString(1, tfReg_no.getText());
					pst.setString(2, tfName.getText());
					pst.setString(3, rdbtnMale.getText());
					pst.setString(2, tfName.getText());
					pst.setString(1, tfReg_no.getText());
					pst.setString(2, tfName.getText());
	
					rs = (OracleResultSet)pst.executeQuery();

					}catch(Exception e) {
							JOptionPane.showMessageDialog(null, "Connection lost", "Oops", JOptionPane.WARNING_MESSAGE);
					}
			}
		});
		btnRegister.setFont(new Font("Courier 10 Pitch", Font.BOLD, 18));
		btnRegister.setBounds(137, 448, 150, 31);
		contentPane.add(btnRegister);
	}
}
