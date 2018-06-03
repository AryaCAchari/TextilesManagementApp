package txtl.mng;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class CreateUsers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel user_name;
	private JTextField username;
	private JLabel Create_Pass;
	private JPasswordField passwordField;
	private JLabel confirm_pass;
	private JPasswordField cnfrm_pass_field;
	private JLabel mobil_num;
	private JTextField mobilenum;
	private JLabel email_id;
	private JTextField emailid;
	private JLabel user_adrs;
	private JTextArea Adrs_textArea;
	private JButton Insert_dBase;
	
	private Connection con = null;
	private PreparedStatement pstm = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUsers frame = new CreateUsers();
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
	public CreateUsers() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" " +e.getMessage());
			System.exit(0);
		}
		
		setTitle("SIGN UP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		user_name = new JLabel("User Name");
		user_name.setForeground(new Color(255, 255, 255));
		user_name.setFont(new Font("Consolas", Font.BOLD, 16));
		user_name.setHorizontalAlignment(SwingConstants.CENTER);
		user_name.setBounds(445, 219, 170, 25);
		contentPane.add(user_name);
		
		username = new JTextField();
		username.setBounds(667, 216, 211, 30);
		contentPane.add(username);
		username.setColumns(10);
		
		Create_Pass = new JLabel("Create Password");
		Create_Pass.setForeground(new Color(255, 255, 255));
		Create_Pass.setHorizontalAlignment(SwingConstants.CENTER);
		Create_Pass.setFont(new Font("Consolas", Font.BOLD, 16));
		Create_Pass.setBounds(445, 273, 170, 25);
		contentPane.add(Create_Pass);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(667, 270, 211, 30);
		contentPane.add(passwordField);
		
		confirm_pass = new JLabel("Confirm Password");
		confirm_pass.setForeground(new Color(255, 255, 255));
		confirm_pass.setHorizontalAlignment(SwingConstants.CENTER);
		confirm_pass.setFont(new Font("Consolas", Font.BOLD, 16));
		confirm_pass.setBounds(445, 327, 170, 25);
		contentPane.add(confirm_pass);
		
		cnfrm_pass_field = new JPasswordField();
		cnfrm_pass_field.setBounds(667, 324, 211, 30);
		contentPane.add(cnfrm_pass_field);
		
		mobil_num = new JLabel("Mobile Number");
		mobil_num.setForeground(new Color(255, 255, 255));
		mobil_num.setHorizontalAlignment(SwingConstants.CENTER);
		mobil_num.setFont(new Font("Consolas", Font.BOLD, 16));
		mobil_num.setBounds(445, 382, 170, 25);
		contentPane.add(mobil_num);
		
		mobilenum = new JTextField();
		mobilenum.setColumns(10);
		mobilenum.setBounds(667, 379, 211, 30);
		contentPane.add(mobilenum);
		
		email_id = new JLabel("Email");
		email_id.setForeground(new Color(255, 255, 255));
		email_id.setHorizontalAlignment(SwingConstants.CENTER);
		email_id.setFont(new Font("Consolas", Font.BOLD, 16));
		email_id.setBounds(445, 434, 170, 25);
		contentPane.add(email_id);
		
		emailid = new JTextField();
		emailid.setColumns(10);
		emailid.setBounds(667, 431, 211, 30);
		contentPane.add(emailid);
		
		user_adrs = new JLabel("Address");
		user_adrs.setForeground(new Color(255, 255, 255));
		user_adrs.setHorizontalAlignment(SwingConstants.CENTER);
		user_adrs.setFont(new Font("Consolas", Font.BOLD, 16));
		user_adrs.setBounds(445, 486, 170, 25);
		contentPane.add(user_adrs);
		
		Adrs_textArea = new JTextArea();
		Adrs_textArea.setBounds(659, 486, 219, 129);
		contentPane.add(Adrs_textArea);
		
		Insert_dBase = new JButton("Create Account");
		Insert_dBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				createAccount();
			}
		});
		Insert_dBase.setForeground(new Color(46, 139, 87));
		Insert_dBase.setBackground(new Color(255, 255, 255));
		Insert_dBase.setFont(new Font("Consolas", Font.BOLD, 16));
		Insert_dBase.setBounds(896, 577, 170, 38);
		contentPane.add(Insert_dBase);		
	}
	
	@SuppressWarnings("deprecation")
	public void createAccount() {
		
		String password = new String(passwordField.getPassword());
		String confirmpassword = new String(cnfrm_pass_field.getPassword());
		
		String query = "INSERT INTO sign_up_table(User_name, Create_pass, Confirm_pass, Mobile_num, Email, Address) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			
			pstm = con.prepareStatement(query);
			
			if(username.getText().isEmpty() && passwordField.getText().isEmpty() && cnfrm_pass_field.getText().isEmpty() && mobilenum.getText().isEmpty() && emailid.getText().isEmpty() && emailid.getText().isEmpty() && Adrs_textArea.getText().isEmpty()) {
				
				Insert_dBase.setEnabled(false);
				JOptionPane.showMessageDialog(null, "All feilds are required, can not leave empty!");
				
			}else {
				pstm.setString(1, username.getText());
				pstm.setString(2, passwordField.getText());
				pstm.setString(3, cnfrm_pass_field.getText());
				pstm.setString(4, mobilenum.getText());
				pstm.setString(5, emailid.getText());
				pstm.setString(6, Adrs_textArea.getText());
				
				Insert_dBase.setEnabled(true);
				
				if(password.equals(confirmpassword)) {
					pstm.execute();
					JOptionPane.showMessageDialog(null, "Account Created!");
					
					dispose();
					TextileManagementClass txtMngclass = new TextileManagementClass();
					txtMngclass.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Password and Confirm password is not match!");
				}
				
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		username.setText(null);
		passwordField.setText(null);
		cnfrm_pass_field.setText(null);
		mobilenum.setText(null);
		emailid.setText(null);
		Adrs_textArea.setText(null);
	}
}
