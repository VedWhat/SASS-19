import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleResultSet;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	
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
					LoginWindow frame = new LoginWindow();
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
	public LoginWindow() {
		super("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		contentPane.setLayout(null); 
		contentPane.setVisible(true);
		JLabel lblUsername = new JLabel("Registration no.");
		lblUsername.setFont(new Font("URW Gothic L", Font.PLAIN, 15));
		lblUsername.setBounds(66, 95, 127, 30);
		contentPane.add(lblUsername);
		
		JLabel lblWelcomeToSass = new JLabel("Welcome to SASS");
		lblWelcomeToSass.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToSass.setBounds(66, 26, 369, 30);
		lblWelcomeToSass.setFont(new Font("Courier 10 Pitch", Font.BOLD, 23));
		contentPane.add(lblWelcomeToSass);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("URW Gothic L", Font.PLAIN, 15));
		lblPassword.setBounds(66, 136, 93, 30);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(208, 95, 152, 27);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				conn = JavaConnectDB.ConnectDB();
				try {
					pst = (OraclePreparedStatement) conn.prepareStatement("select * from login where reg_no =? and pwd = ?");
					pst.setString(1, tfUsername.getText());
					pst.setString(2, tfPassword.getText());
					rs = (OracleResultSet)pst.executeQuery();
					if (rs.next()) {
						//JOptionPane.showMessageDialog(null, "Login successful!");
						Schedule s = new Schedule(rs.getString(1));
						s.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username or password incorrect!", "Oops!", JOptionPane.ERROR_MESSAGE);
					}
				}catch(SQLException exc) {
					JOptionPane.showMessageDialog(null, "Oops!", "Error in connection", JOptionPane.WARNING_MESSAGE);;
				}
			}
		});
		btnLogin.setFont(new Font("URW Gothic L", Font.BOLD, 16));
		btnLogin.setBounds(208, 186, 106, 31);
		contentPane.add(btnLogin);
		
		
		JButton btnRegister = new JButton("NOT A MEMBER?");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow reg_frame = new RegisterWindow();
				reg_frame.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("URW Gothic L", Font.BOLD, 16));
		btnRegister.setBounds(63, 239, 404, 31);
		contentPane.add(btnRegister);
		
		tfPassword = new JPasswordField();
		tfPassword.setBounds(208, 136, 152, 27);
		contentPane.add(tfPassword);		
	}
}
