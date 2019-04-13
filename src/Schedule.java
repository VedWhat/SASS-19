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
import java.util.Vector;

import javax.swing.border.SoftBevelBorder;

import oracle.jdbc.internal.OraclePreparedStatement;
import oracle.jdbc.internal.OracleResultSet;
import oracle.jdbc.internal.OracleStatement;
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
import javax.swing.JTable;
import javax.swing.JTextArea;

@SuppressWarnings("unused")
public class Schedule extends JFrame {

	/**
	 * 
	 */
	static boolean ready = false;
	static int[] arr;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static Vector<String> v = new Vector<>();
	static Vector<String> reg = new Vector<>();
	static String aid;
	Connection conn=null;
    OraclePreparedStatement pst=null;
    OracleResultSet rs=null;
    private JTextField textField;
    private JTable table;
    static String mid;
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
		
		JPanel finalPanel = new JPanel();
		contentPane.add(finalPanel, "Match");
		finalPanel.setLayout(null);
		
		JTextArea txtrResult = new JTextArea();
		txtrResult.setText("Enter your match results here!");
		txtrResult.setFont(new Font("URW Gothic L", Font.PLAIN, 15));
		txtrResult.setToolTipText("Enter the result of the match here!");
		txtrResult.setBounds(12, 376, 348, 51);
		finalPanel.add(txtrResult);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					OracleStatement os = (OracleStatement)conn.createStatement();
					os.executeQuery("update match set result='"+txtrResult.getText()+"' where mid="+mid);
				}catch(SQLException eq1) {
					JOptionPane.showMessageDialog(null, eq1.getMessage());}
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Courier 10 Pitch", Font.BOLD, 23));
		btnNewButton.setBounds(372, 376, 116, 51);
		finalPanel.add(btnNewButton);
		
		JLabel pnlName = new JLabel("");
		pnlName.setVerticalAlignment(SwingConstants.TOP);
		pnlName.setBounds(12, 62, 143, 302);
		finalPanel.add(pnlName);
		
		JLabel pnlPhone = new JLabel("New label");
		pnlPhone.setVerticalAlignment(SwingConstants.TOP);
		pnlPhone.setBounds(167, 62, 143, 302);
		finalPanel.add(pnlPhone);
		
		JLabel pnlAddress = new JLabel("New label");
		pnlAddress.setVerticalAlignment(SwingConstants.TOP);
		pnlAddress.setBounds(322, 62, 166, 302);
		finalPanel.add(pnlAddress);
		
		JLabel lblHead = new JLabel();
		lblHead.setFont(new Font("Courier 10 Pitch", Font.BOLD, 21));
		lblHead.setBounds(12, 0, 476, 51);
		finalPanel.add(lblHead);
		
		
		
		
		
		
		
		mainPanel.setLayout(null);
		resultPanel.setLayout(null);
		
		
		JLabel lblItsAMatch = new JLabel("It's a Match!");
		lblItsAMatch.setHorizontalAlignment(SwingConstants.CENTER);
		lblItsAMatch.setFont(new Font("Courier 10 Pitch", Font.BOLD, 23));
		lblItsAMatch.setBounds(12, 0, 476, 41);
		resultPanel.add(lblItsAMatch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 115, 488, 257);
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
				v.clear();
				reg.clear();
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
		
		String sport[] = {"Tennis", "Football", "Cricket", "Basketball", "Table Tennis"};
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
				
				@SuppressWarnings("rawtypes")
				JComboBox comboBoxArena = new JComboBox(arrayArena);
				aid =((Integer)(comboBoxArena.getSelectedIndex()+1)).toString();
				comboBoxArena.setBounds(270, 328, 207, 29);
				mainPanel.add(comboBoxArena);
				comboBoxSport.setEnabled(false);
				
				//btnNext must work only after the arena has been selected:
				btnNext.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
						
						JList<String> list;
						try {
							//regno has regno, sid will have sid, aid is static
							String sid = ((Integer)(comboBoxSport.getSelectedIndex()+1)).toString();
							//get DAY and TIME
							conn = JavaConnectDB.ConnectDB();
							
							String req ="<html><b>For your current selection:<b><br/>";
							OracleStatement o = (OracleStatement)conn.createStatement();
							OracleResultSet x = (OracleResultSet)o.executeQuery("select a_req from arena where aid = "+aid);
							x.next();
							req = req+"<i>Arena requirements:</i>"+x.getString(1)+"<br/>";
							x = (OracleResultSet)o.executeQuery("select s_req from sport where sid = "+sid);
							x.next();
							req = req+ "<i>Sport requirements:</i>"+x.getString(1)+"<br/>";
							JOptionPane.showMessageDialog(null, req, "REQUIREMENTS", JOptionPane.WARNING_MESSAGE);
							
							CallableStatement cstmt = conn.prepareCall("{call getDay(?)}");
							cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
							cstmt.executeUpdate();
							String day = cstmt.getString(1);
							
							OracleStatement os = (OracleStatement)conn.createStatement();
							OracleResultSet f = (OracleResultSet)os.executeQuery("select calMatchHalf from dual");
							f.next();
							String half = "'"+f.getString(1)+"'";
							
							OracleResultSet rs1 = (OracleResultSet)os.executeQuery("(select distinct reg_no, name, cyear, skill, sex, primary_no, secondary from phone natural join student natural join section natural join timeslot natural join plays where "+day+"="+half+" and sid="+sid+") minus (select reg_no, name, cyear, skill, sex, primary_no, secondary from phone natural join student natural join section natural join timeslot natural join plays where reg_no = "+regno+")");

							
							
							
							while(rs1.next()) {
								//OracleResultSet spr = (OracleResultSet)osp.executeQuery("select primary_no, secondary from phone where reg_no = "+rs1.getString(1));
								v.add("<html><b>"+rs1.getString(2)+"</b><br/><i>Gender: </i>"+rs1.getString(5)+"<i>		Year:</i>"+rs1.getString(3)+"			<i>Skill:</i>"+rs1.getString(4)+"<br/><i>Primary no.: </i>"+rs1.getString(6)+"<i>Secondary no.: </i>"+rs1.getString(7)+"</html>");
								//System.out.println(rs1.getString(1)+"\t\t\t"+rs1.getString(2)+"\t\t\t\t\t"+rs1.getString(3)+"\t"+rs1.getString(4));
								reg.add(rs1.getString(1));
							}
							
							
							
							
						}catch(SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
						list = new JList<String>(v);
						scrollPane.setViewportView(list);
						cl.next(contentPane);
						btnNextF.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								arr = list.getSelectedIndices();
								try {
									OracleStatement os = (OracleStatement) conn.createStatement();
								rs = (OracleResultSet) os.executeQuery("select min_p from sport where sid = "+sid);
								rs.next();
								if(arr.length<rs.getInt(1));
									JOptionPane.showMessageDialog(null, "The number of players selected is less than minimum requirement for the sport. However, you may continue.", "Warning", JOptionPane.WARNING_MESSAGE);
								}
								catch(SQLException ew) {
									JOptionPane.showMessageDialog(null, ew.getMessage());
								}
								//for(int x: arr)
								//System.out.println(x);
										cl.next(contentPane);
								//arr has index values of all elements selected earlier
								//reg.get(index) will fetch these values
								Vector<String> v1 = new Vector<>();
								for(int i = 0; i< arr.length;i++)
									v1.add(reg.get(arr[i]));
								try {
									
									CallableStatement cs = conn.prepareCall("{call getMatchID(?)}");
									cs.registerOutParameter(1, java.sql.Types.NUMERIC);
									cs.executeUpdate();
									mid = cs.getString(1);
									lblHead.setText("Match ID : "+mid);
									
									
									OracleStatement os = (OracleStatement)conn.createStatement();
									os.executeQuery("insert into match values((select to_number(to_char(sysdate,'YYHHMMSS')) from dual),"+sid+","+aid+",sysdate,null)");
									
									for(int i = 0; i<v1.size();i++) {
										os.execute("insert into assigned values("+v1.get(i)+","+mid+")");
									}
									String n = "<html><b>NAME</b><br/>";
									String p = "<html><b>PHONE</b><br/>";
									String a = "<html><b>ADDRESS</b><br/>";
									for(int i = 0; i<v1.size();i++) {
										rs =(OracleResultSet) os.executeQuery("select name, primary_no, address from student natural join phone where reg_no = "+v1.get(i));
										rs.next();
										n = n + rs.getString(1)+"<br/>";
										p = p + rs.getString(2)+"<br/>";
										a = a + rs.getString(3)+"<br/>";
									}
									pnlName.setText(n);
									pnlPhone.setText(p);
									pnlAddress.setText(a);
										
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(null, e1.getMessage());
								}
								
								
								
								
							}
						});
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
		
		
		
		
		
		
		JLabel lblRequirements = new JLabel("<html>Select who you want to play with! <br/> Click 'next' when done.");
		lblRequirements.setBounds(12, 45, 476, 58);
		resultPanel.add(lblRequirements);
		//String sid has sportid and 
		
		
		
		
		
		
		
		
		
		
	}
}
