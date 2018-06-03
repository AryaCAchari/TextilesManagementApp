package txtl.mng;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;


import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TextileManagementClass {

	private JFrame frame;
	private JTextField UseName;
	private JLabel lblUserName; 
	private Connection connect = null;
	private PreparedStatement pstmnt = null;
	private ResultSet rset = null;
	private JPasswordField Usr_pass_feild;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextileManagementClass window = new TextileManagementClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextileManagementClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" " +e.getMessage());
			System.exit(0);
		}
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(46, 139, 87));
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 703, 474);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		UseName = new JTextField();
		UseName.setBounds(438, 114, 140, 33);
		frame.getContentPane().add(UseName);
		UseName.setColumns(10);
		
		lblUserName = new JLabel("User Name");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Consolas", Font.BOLD, 16));
		lblUserName.setBounds(322, 117, 103, 20);
		frame.getContentPane().add(lblUserName);
		
		Usr_pass_feild = new JPasswordField();
		Usr_pass_feild.setBounds(438, 169, 140, 33);
		frame.getContentPane().add(Usr_pass_feild);
		
		lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(322, 178, 94, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("Login ");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				
				try {
									
						String query = "select * from sign_up_table where User_name=? and Create_pass=?";
						
						pstmnt = connect.prepareStatement(query);
						pstmnt.setString(1, UseName.getText());
						pstmnt.setString(2, Usr_pass_feild.getText());
						
						rset = pstmnt.executeQuery();
						
						if(rset.next()) {
							JOptionPane.showMessageDialog(null, "Welcome!");
							frame.dispose();
							Dashboard dBoard = new Dashboard();
							dBoard.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(null, "User name or password is wrong!");
						}
						
					}catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}								

			}
		});
		btnLogin.setFont(new Font("Consolas", Font.BOLD, 16));
		Image imga = new ImageIcon(this.getClass().getResource("/Accept-icon.png")).getImage();
		btnLogin.setIcon(new ImageIcon(imga));
		btnLogin.setBounds(438, 230, 140, 33);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Login-icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(126, 106, 128, 128);
		frame.getContentPane().add(lblNewLabel);
		
		JButton exitButton = new JButton("");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int exitapp = JOptionPane.showConfirmDialog(null, this, "Are you sure you want to exit?", JOptionPane.YES_NO_OPTION);
				if(exitapp == JOptionPane.YES_OPTION) System.exit(0);
			}
		});
		Image imag = new ImageIcon(this.getClass().getResource("/Exit.png")).getImage();
		exitButton.setIcon(new ImageIcon(imag));
		exitButton.setBounds(24, 360, 48, 48);
		frame.getContentPane().add(exitButton);
		
		JButton Sign_up_btn = new JButton("Sign Up");
		Sign_up_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
                CreateUsers cu = new CreateUsers();
                cu.setVisible(true);
			}
		});
		Sign_up_btn.setFont(new Font("Consolas", Font.BOLD, 16));
		Sign_up_btn.setBounds(438, 274, 140, 23);
		frame.getContentPane().add(Sign_up_btn);
		
//		JLabel lblNewLabel_1 = new JLabel("");
//		lblNewLabel_1.setBounds(0, 0, 687, 435);
//		frame.getContentPane().add(lblNewLabel_1);
		

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	   if(b == true) {
		   frame.setVisible(true);
	   }
		
	}
}
