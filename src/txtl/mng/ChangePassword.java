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
//import java.awt.Toolkit;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ChangePassword extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	
	Connection connect = null;
	PreparedStatement statement = null;
	ResultSet rSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePassword frame = new ChangePassword();
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
	public ChangePassword() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" " +e.getMessage());
			System.exit(0);
		}
		
		setTitle("RESET PASSWORD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel user_name = new JLabel("User Name");
		user_name.setForeground(new Color(255, 255, 255));
		user_name.setFont(new Font("Consolas", Font.BOLD, 16));
		user_name.setHorizontalAlignment(SwingConstants.CENTER);
		user_name.setBounds(312, 257, 125, 28);
		contentPane.add(user_name);
		
		JLabel reset_pass = new JLabel("Reset Password");
		reset_pass.setHorizontalAlignment(SwingConstants.CENTER);
		reset_pass.setForeground(Color.WHITE);
		reset_pass.setFont(new Font("Consolas", Font.BOLD, 16));
		reset_pass.setBounds(312, 326, 153, 28);
		contentPane.add(reset_pass);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(571, 326, 153, 20);
		contentPane.add(passwordField);
		
		JLabel confrm_rst_pass = new JLabel("Confirm Password");
		confrm_rst_pass.setHorizontalAlignment(SwingConstants.CENTER);
		confrm_rst_pass.setForeground(Color.WHITE);
		confrm_rst_pass.setFont(new Font("Consolas", Font.BOLD, 16));
		confrm_rst_pass.setBounds(312, 392, 153, 28);
		contentPane.add(confrm_rst_pass);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(571, 392, 153, 20);
		contentPane.add(passwordField_1);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setFont(new Font("Consolas", Font.BOLD, 16));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				updatePasssword();
				
			}
		});
		btnUpdate.setBounds(729, 438, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ChangePassword cp = new ChangePassword();
//				cp.dispose();
//                Dashboard db = new Dashboard();
//                db.setVisible(true);
//                close_page();
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                dispose();
//                Dashboard db = new Dashboard();
//                db.setVisible(true);
                
                dispose();
        		TextileManagementClass tmLout = new TextileManagementClass();
        		tmLout.setVisible(true);
                
			}
		});
		btnBack.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBack.setBounds(157, 534, 89, 23);
		contentPane.add(btnBack);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(571, 265, 153, 20);
		contentPane.add(comboBox);
		userNameCombo();
	}
	
	
	private void userNameCombo() {
		
		String sql = "select User_name from sign_up_table";
		
		try {
			
			statement = connect.prepareStatement(sql);
			rSet = statement.executeQuery();
			
			while(rSet.next()) {
				comboBox.addItem(rSet.getString("User_name"));
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	private void updatePasssword() {
		
		@SuppressWarnings("deprecation")
		String pass = passwordField.getText();
		@SuppressWarnings("deprecation")
		String c_pass = passwordField_1.getText();
		String u_Name = (String) comboBox.getSelectedItem();
		String sql = "update sign_up_table set Create_pass = ? , Confirm_pass = ? where User_name = ? ";
		
		try {
			 
			statement = connect.prepareStatement(sql);
			statement.setString(1, pass);
			statement.setString(2, c_pass);
			statement.setString(3, u_Name);
			
			if(pass.equals(c_pass)) {
				statement.executeUpdate();
				JOptionPane.showMessageDialog(null, "Password Reset successfully!");
			}	
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		passwordField.setText(null);
		passwordField_1.setText(null);
		
	}
	
	
//	public void close_page() {
//		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
//		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
//	}
	
}
