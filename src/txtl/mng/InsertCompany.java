package txtl.mng;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InsertCompany extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cmpName;
	private JTextField cmpCountry;
	private JTextField cmpEmail;
	private JTextField cmpPhone;
	private JTextArea cmpAddress;
	private Connection connect = null;
	private PreparedStatement prpstm = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertCompany frame = new InsertCompany();
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
	public InsertCompany() {
		
		try {
			
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" : " +e.getMessage());
			System.exit(0);
		}
		
		setTitle("INSERT COMPANY");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Company Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 16));
		lblNewLabel.setBounds(406, 216, 162, 30);
		contentPane.add(lblNewLabel);
		
		cmpName = new JTextField();
		cmpName.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpName.setBounds(635, 214, 213, 35);
		contentPane.add(cmpName);
		cmpName.setColumns(10);
		
		JLabel lblCompanyCountry = new JLabel("Company Country");
		lblCompanyCountry.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyCountry.setForeground(Color.WHITE);
		lblCompanyCountry.setFont(new Font("Consolas", Font.BOLD, 16));
		lblCompanyCountry.setBounds(406, 284, 162, 30);
		contentPane.add(lblCompanyCountry);
		
		cmpCountry = new JTextField();
		cmpCountry.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpCountry.setColumns(10);
		cmpCountry.setBounds(635, 282, 213, 35);
		contentPane.add(cmpCountry);
		
		JLabel lblCompanyEmail = new JLabel("Company Email");
		lblCompanyEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyEmail.setForeground(Color.WHITE);
		lblCompanyEmail.setFont(new Font("Consolas", Font.BOLD, 16));
		lblCompanyEmail.setBounds(406, 351, 162, 30);
		contentPane.add(lblCompanyEmail);
		
		cmpEmail = new JTextField();
		cmpEmail.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpEmail.setColumns(10);
		cmpEmail.setBounds(635, 349, 213, 35);
		contentPane.add(cmpEmail);
		
		JLabel lblCompanyPhone = new JLabel("Company Phone");
		lblCompanyPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyPhone.setForeground(Color.WHITE);
		lblCompanyPhone.setFont(new Font("Consolas", Font.BOLD, 16));
		lblCompanyPhone.setBounds(406, 415, 162, 30);
		contentPane.add(lblCompanyPhone);
		
		cmpPhone = new JTextField();
		cmpPhone.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpPhone.setColumns(10);
		cmpPhone.setBounds(635, 413, 213, 35);
		contentPane.add(cmpPhone);
		
		JLabel lblCompanyAddress = new JLabel("Company Address");
		lblCompanyAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyAddress.setForeground(Color.WHITE);
		lblCompanyAddress.setFont(new Font("Consolas", Font.BOLD, 16));
		lblCompanyAddress.setBounds(406, 488, 162, 30);
		contentPane.add(lblCompanyAddress);
		
		cmpAddress = new JTextArea();
		cmpAddress.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpAddress.setBounds(635, 491, 213, 71);
		contentPane.add(cmpAddress);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					String query = "INSERT INTO insert_company_table(Comp_Name, Comp_Cntry, Comp_Mail, Comp_Phn, Comp_Adrs) VALUES(?, ?, ?, ?, ?)";
					prpstm = connect.prepareStatement(query);
					prpstm.setString(1, cmpName.getText());
					prpstm.setString(2, cmpCountry.getText());
					prpstm.setString(3, cmpEmail.getText());
					prpstm.setString(4, cmpPhone.getText());
					prpstm.setString(5, cmpAddress.getText());
					prpstm.execute();
					JOptionPane.showMessageDialog(null, "Company Details inserted.");
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
				
				
				cmpName.setText(null);
				cmpCountry.setText(null);
				cmpEmail.setText(null);
				cmpPhone.setText(null);
				cmpAddress.setText(null);
				
//				InsertCompany inscmp = new InsertCompany();
//				inscmp.dispose();
//                CompanyDetails cmpdt = new CompanyDetails();
//                cmpdt.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 16));
		btnNewButton.setBounds(711, 588, 85, 22);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				InsertCompany inscmp = new InsertCompany();
//				inscmp.dispose();
//				Dashboard cmpdt = new Dashboard();
//                cmpdt.setVisible(true);
//                close_page();
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
				Dashboard cmpdt = new Dashboard();
				cmpdt.setVisible(true);
				
				
			}
		});
		btnBack.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBack.setBounds(96, 575, 118, 35);
		contentPane.add(btnBack);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				InsertCompany icp = new InsertCompany();
//				icp.dispose();
				dispose();
				UpdateCompany ucp = new UpdateCompany();
				ucp.setVisible(true);
               		
			}
		});
		btnEdit.setFont(new Font("Consolas", Font.BOLD, 16));
		btnEdit.setBounds(806, 588, 85, 22);
		contentPane.add(btnEdit);
	}
	
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
	
}
