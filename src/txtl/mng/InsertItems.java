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

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class InsertItems extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField prd_nm_txtFld;
	private JTextField prd_qnty_txtFld;
	private JTextField prd_prc_txtFld;
	private JTextField btch_txtFld;
	private JComboBox<String> CompName_combox;
	private JComboBox<String> prd_cat_cmBx;
	private JButton btnModify;
	
	private Connection connect = null;
	private PreparedStatement prdstm = null;
	private ResultSet rset = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertItems frame = new InsertItems();
					//frame.CompNameCombox();
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
	public InsertItems() {
		
		//CompNameCombox();
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" : " +e.getMessage());
			System.exit(0);
		}
		
		setTitle("INSERT ITEM");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Product Name");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 16));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(402, 306, 191, 29);
		contentPane.add(lblNewLabel);
		
		prd_nm_txtFld = new JTextField();
		prd_nm_txtFld.setFont(new Font("Consolas", Font.PLAIN, 16));
		prd_nm_txtFld.setBounds(635, 307, 260, 29);
		contentPane.add(prd_nm_txtFld);
		prd_nm_txtFld.setColumns(10);
		
		JLabel lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyName.setForeground(Color.WHITE);
		lblCompanyName.setFont(new Font("Consolas", Font.BOLD, 16));
		lblCompanyName.setBackground(Color.WHITE);
		lblCompanyName.setBounds(402, 416, 191, 29);
		contentPane.add(lblCompanyName);
		
		JLabel lblProductCategory = new JLabel("Product Catagory");
		lblProductCategory.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductCategory.setForeground(Color.WHITE);
		lblProductCategory.setFont(new Font("Consolas", Font.BOLD, 16));
		lblProductCategory.setBackground(Color.WHITE);
		lblProductCategory.setBounds(402, 362, 191, 29);
		contentPane.add(lblProductCategory);
		
		JLabel lblProductQuantity = new JLabel("Product Quantity");
		lblProductQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductQuantity.setForeground(Color.WHITE);
		lblProductQuantity.setFont(new Font("Consolas", Font.BOLD, 16));
		lblProductQuantity.setBackground(Color.WHITE);
		lblProductQuantity.setBounds(402, 475, 191, 29);
		contentPane.add(lblProductQuantity);
		
		prd_qnty_txtFld = new JTextField();
		prd_qnty_txtFld.setFont(new Font("Consolas", Font.PLAIN, 16));
		prd_qnty_txtFld.setColumns(10);
		prd_qnty_txtFld.setBounds(635, 476, 260, 29);
		contentPane.add(prd_qnty_txtFld);
		
		JLabel lblProductPrice = new JLabel("Price/Unit");
		lblProductPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductPrice.setForeground(Color.WHITE);
		lblProductPrice.setFont(new Font("Consolas", Font.BOLD, 16));
		lblProductPrice.setBackground(Color.WHITE);
		lblProductPrice.setBounds(402, 529, 191, 29);
		contentPane.add(lblProductPrice);
		
		prd_prc_txtFld = new JTextField();
		prd_prc_txtFld.setFont(new Font("Consolas", Font.PLAIN, 16));
		prd_prc_txtFld.setColumns(10);
		prd_prc_txtFld.setBounds(635, 530, 260, 29);
		contentPane.add(prd_prc_txtFld);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String query = "insert into insrt_itm_table(btch_num, Prdct_name, Prd_Catgry, Cmp_nm, Prdct_Qunty, Price_Unit) values(?, ?, ?, ?, ?, ?)";
					
					prdstm = connect.prepareStatement(query);
					
					prdstm.setString(1, btch_txtFld.getText());
					prdstm.setString(2, prd_nm_txtFld.getText());
					
					String value = prd_cat_cmBx.getSelectedItem().toString();
					prdstm.setString(3, value);
					
					String dB_value = CompName_combox.getSelectedItem().toString();
					prdstm.setString(4, dB_value);
					
					prdstm.setString(5, prd_qnty_txtFld.getText());
					//prdstm.setString(5, prd_qnty_txtFld.getText());
					prdstm.setString(6, prd_prc_txtFld.getText());
					
					prdstm.execute();
					JOptionPane.showMessageDialog(null, "Product Inserted!");
					
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
				
				
				btch_txtFld.setText(null);
				prd_nm_txtFld.setText(null);
				prd_qnty_txtFld.setText(null);
				prd_prc_txtFld.setText(null);
				
			}
		});
		btnAdd.setFont(new Font("Consolas", Font.BOLD, 16));
		btnAdd.setBounds(778, 592, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				InsertItems instm = new InsertItems();
//				instm.dispose();
//				Dashboard prdtls = new Dashboard();
//                prdtls.setVisible(true);
//                close_page();
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				dispose(); 	
				Dashboard prdtls = new Dashboard();
				prdtls.setVisible(true);
				
				
			}
		});
		btnBack.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBack.setBounds(31, 592, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblBatchNo = new JLabel("Batch No");
		lblBatchNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBatchNo.setForeground(Color.WHITE);
		lblBatchNo.setFont(new Font("Consolas", Font.BOLD, 16));
		lblBatchNo.setBackground(Color.WHITE);
		lblBatchNo.setBounds(390, 252, 191, 29);
		contentPane.add(lblBatchNo);
		
		btch_txtFld = new JTextField();
		btch_txtFld.setFont(new Font("Consolas", Font.PLAIN, 16));
		btch_txtFld.setColumns(10);
		btch_txtFld.setBounds(635, 253, 260, 29);
		contentPane.add(btch_txtFld);
		
		CompName_combox = new JComboBox<String>();
		CompName_combox.setFont(new Font("Consolas", Font.PLAIN, 16));
		CompName_combox.setBounds(635, 417, 260, 29);
		contentPane.add(CompName_combox);
		
		prd_cat_cmBx = new JComboBox<String>();
		prd_cat_cmBx.setFont(new Font("Consolas", Font.PLAIN, 16));
		prd_cat_cmBx.setModel(new DefaultComboBoxModel<String>(new String[] {"Men's Fashion ", "Women's Fashion", "Kid's Fashion"}));
		prd_cat_cmBx.setBounds(635, 363, 260, 29);
		contentPane.add(prd_cat_cmBx);
		
		btnModify = new JButton("EDIT");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				InsertItems instm = new InsertItems();
//				instm.dispose();
				dispose();
				Update_Delete_Items udi = new Update_Delete_Items();
				udi.setVisible(true);
				
			}
		});
		btnModify.setFont(new Font("Consolas", Font.BOLD, 16));
		btnModify.setBounds(908, 592, 89, 23);
		contentPane.add(btnModify);
		
		CompNameCombox();
	}
	
	public void CompNameCombox() {
		try {
			
			String query = "select Comp_Name from insert_company_table";
			prdstm = connect.prepareStatement(query);
			rset = prdstm.executeQuery();
			
			while(rset.next()) {
				CompName_combox.addItem(rset.getString("Comp_Name"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
	
}
