package txtl.mng;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;

public class NewSales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel PrdNm_Lable;
	private JComboBox<String> comboBox;
	private JButton show_bttn;
	private JLabel prdQunty_Label;
	private JTextField qnty_Textfld;
	private JButton addtoCart_Bttn;
	private JTable fromDB_Table;
	DefaultTableModel model;
	private JTable addcrt_Table;
	DefaultTableModel model_1;
	private JButton btnBack;
	public int s_qnty = 0;
	public int Prdct_Qunty = 0;
	
	Connection connect = null;
	PreparedStatement statement = null;
	ResultSet rSet = null;
	private JLabel lblTotalQuantity;
	private JTextField totalQnt_textField;
	private JLabel lblPrice;
	private JTextField prcPItm_textField;
	private JLabel lblCompanyName;
	private JTextField cmpName_textField;
	private JLabel lblBatchNo;
	private JTextField btchNo_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewSales frame = new NewSales();
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
	public NewSales() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" : " +e.getMessage());
			System.exit(0);
		}
		
		
		setTitle("NEW SALES");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				NewSales nwsl = new NewSales();
//				nwsl.dispose();
//              Dashboard frmnws = new Dashboard();
//              frmnws.setVisible(true);
//              close_page();
//              setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                dispose();
                Dashboard frmnws = new Dashboard(); 
                frmnws.setVisible(true);
                
                
			}
		});
		btnBack.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBack.setBounds(55, 616, 89, 23);
		contentPane.add(btnBack);
		
		PrdNm_Lable = new JLabel("Product Name");
		PrdNm_Lable.setForeground(new Color(255, 255, 255));
		PrdNm_Lable.setHorizontalAlignment(SwingConstants.CENTER);
		PrdNm_Lable.setFont(new Font("Consolas", Font.BOLD, 16));
		PrdNm_Lable.setBounds(89, 167, 147, 23);
		contentPane.add(PrdNm_Lable);
		
		comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Consolas", Font.BOLD, 16));
		comboBox.setBounds(242, 167, 261, 23);
		contentPane.add(comboBox);
		
		show_bttn = new JButton("Show");
		show_bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadTable();
			}
		});
		show_bttn.setFont(new Font("Consolas", Font.BOLD, 16));
		show_bttn.setBounds(513, 167, 89, 23);
		contentPane.add(show_bttn);
		
		prdQunty_Label = new JLabel("Enter Quantity");
		prdQunty_Label.setForeground(new Color(255, 255, 255));
		prdQunty_Label.setHorizontalAlignment(SwingConstants.CENTER);
		prdQunty_Label.setFont(new Font("Consolas", Font.BOLD, 16));
		prdQunty_Label.setBounds(838, 201, 147, 23);
		contentPane.add(prdQunty_Label);
		
		qnty_Textfld = new JTextField();
		qnty_Textfld.setFont(new Font("Consolas", Font.PLAIN, 16));
		qnty_Textfld.setBounds(1013, 201, 168, 23);
		contentPane.add(qnty_Textfld);
		qnty_Textfld.setColumns(10);
						
		addtoCart_Bttn = new JButton("");
		addtoCart_Bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				salesTable();
				
			}
		});
		addtoCart_Bttn.setBounds(1191, 176, 48, 48);
		Image img = new ImageIcon(getClass().getResource("/Cart-add-icon.png")).getImage();
		addtoCart_Bttn.setIcon(new ImageIcon(img));
		contentPane.add(addtoCart_Bttn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 278, 674, 327);
		contentPane.add(scrollPane);
		
		fromDB_Table = new JTable();
		scrollPane.setViewportView(fromDB_Table);
		Object[] colums = {"ID", "Batch No", "Product Name", "Company Name", "Product Quantity", "Price per Unit", "Product Category"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(colums);
		fromDB_Table.setModel(model);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(768, 295, 574, 310);
		contentPane.add(scrollPane_1);
		
		addcrt_Table = new JTable();
		scrollPane_1.setViewportView(addcrt_Table);
		Object[] colums_1 = {"Product Name", "Batch No", "Quantity", "Price"};
		model_1 = new DefaultTableModel();
		model_1.setColumnIdentifiers(colums_1);
		addcrt_Table.setModel(model_1);
		
		lblTotalQuantity = new JLabel("Total Quantity");
		lblTotalQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalQuantity.setForeground(Color.WHITE);
		lblTotalQuantity.setFont(new Font("Consolas", Font.BOLD, 16));
		lblTotalQuantity.setBounds(838, 167, 147, 23);
		contentPane.add(lblTotalQuantity);
		
		totalQnt_textField = new JTextField();
		totalQnt_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		totalQnt_textField.setColumns(10);
		totalQnt_textField.setBounds(1013, 167, 168, 23);
		contentPane.add(totalQnt_textField);
		
		lblPrice = new JLabel("Price per Item");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Consolas", Font.BOLD, 16));
		lblPrice.setBounds(440, 201, 147, 23);
		contentPane.add(lblPrice);
		
		prcPItm_textField = new JTextField();
		prcPItm_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		prcPItm_textField.setColumns(10);
		prcPItm_textField.setBounds(432, 244, 168, 23);
		contentPane.add(prcPItm_textField);
		
		lblCompanyName = new JLabel("Company Name");
		lblCompanyName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompanyName.setForeground(Color.WHITE);
		lblCompanyName.setFont(new Font("Consolas", Font.BOLD, 16));
		lblCompanyName.setBounds(257, 201, 147, 23);
		contentPane.add(lblCompanyName);
		
		cmpName_textField = new JTextField();
		cmpName_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpName_textField.setColumns(10);
		cmpName_textField.setBounds(249, 244, 168, 23);
		contentPane.add(cmpName_textField);
		
		lblBatchNo = new JLabel("Batch No");
		lblBatchNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBatchNo.setForeground(Color.WHITE);
		lblBatchNo.setFont(new Font("Consolas", Font.BOLD, 16));
		lblBatchNo.setBounds(78, 201, 147, 23);
		contentPane.add(lblBatchNo);
		
		btchNo_textField = new JTextField();
		btchNo_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		btchNo_textField.setColumns(10);
		btchNo_textField.setBounds(68, 244, 168, 23);
		contentPane.add(btchNo_textField);
		
		loadCombo();
	}
	
	
	private void loadCombo() {
		
		String query = "select Prdct_name from insrt_itm_table";
		
		try {
			
			statement = connect.prepareStatement(query);
			rSet = statement.executeQuery();
			
			while(rSet.next()) {
				comboBox.addItem(rSet.getString("Prdct_name").toString());
			}
			
		}catch (Exception e_1) {
			// TODO: handle exception
			e_1.printStackTrace();
		}
		
	}
	
	
	private void loadTable() {
		
		int rows = fromDB_Table.getRowCount();
		
		if(rows == 0) {
			String sql = "select * from insrt_itm_table where Prdct_name = ?";
			Object[] row = new Object[7];
			try {
				statement = connect.prepareStatement(sql);
				statement.setString(1, comboBox.getSelectedItem().toString());
				rSet = statement.executeQuery();
				while(rSet.next()) {
					row[0] = rSet.getString("Itm_ID");
					row[1] = rSet.getString("btch_num");
					row[2] = rSet.getString("Prdct_name");
					row[3] = rSet.getString("Cmp_nm");
					row[4] = rSet.getString("Prdct_Qunty");
					row[5] = rSet.getString("Price_Unit");
					row[6] = rSet.getString("Prd_Catgry");
					model.addRow(row);
					
					String btNo = rSet.getString("btch_num");
					btchNo_textField.setText(btNo);
					
					String cmpName = rSet.getString("Cmp_nm");
					cmpName_textField.setText(cmpName);
					
					String tQnt = rSet.getString("Prdct_Qunty");
					totalQnt_textField.setText(tQnt);
					
					String price = rSet.getString("Price_Unit");
					prcPItm_textField.setText(price);
					
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else {
			model.removeRow(rows - 1);
			String sql = "select * from insrt_itm_table where Prdct_name = ?";
			Object[] row = new Object[7];
			try {
				statement = connect.prepareStatement(sql);
				statement.setString(1, comboBox.getSelectedItem().toString());
				rSet = statement.executeQuery();
				while(rSet.next()) {
					row[0] = rSet.getString("Itm_ID");
					row[1] = rSet.getString("btch_num");
					row[2] = rSet.getString("Prdct_name");
					row[3] = rSet.getString("Cmp_nm");
					row[4] = rSet.getString("Prdct_Qunty");
					row[5] = rSet.getString("Price_Unit");
					row[6] = rSet.getString("Prd_Catgry");
					model.addRow(row);
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	
	
	private void updateItemTbl(int remain_item) {
		
	
		String sql_update = "update insrt_itm_table set Prdct_Qunty = ? where Prdct_name = ?";
		
		try {
			statement = connect.prepareStatement(sql_update);
			statement.setString(1, Integer.toString(remain_item));
			statement.setString(2, comboBox.getSelectedItem().toString());			
			statement.executeUpdate();
		}catch (Exception e_up) {
			// TODO: handle exception
			e_up.printStackTrace();
		}
			
	}
	
	
	private void salesTable() {
		
		String batchNo = btchNo_textField.getText();
		String productName = comboBox.getSelectedItem().toString();
		String companyName = cmpName_textField.getText();
		
		DateFormat dformatte = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		long millisec = System.currentTimeMillis();
		Date date = new Date(millisec);
		//String puchsDate;
		 
		int NoOfCelctdITM = Integer.parseInt(qnty_Textfld.getText().toString());
		int MRP = Integer.parseInt(prcPItm_textField.getText().toString());
		int totalAmount = 0;
		int totalNoItems = Integer.parseInt(totalQnt_textField.getText().toString());
		
		String sql = "select btch_num, Cmp_nm, Prdct_Qunty, Price_Unit from insrt_itm_table";
		
		try {
						
			statement = (PreparedStatement) connect.prepareStatement(sql);
			rSet = (ResultSet) statement.executeQuery();
			
			if(rSet.next()) {
				//batchNo = rSet.getString("btch_num");
				//companyName = rSet.getString("Cmp_nm");
				//puchsDate = dformatte.format(date).toString();
				//JOptionPane.showMessageDialog(null, "Value");
				
			//}
			
			if(NoOfCelctdITM <= totalNoItems) {
				totalAmount = NoOfCelctdITM * MRP; 
				try {
					
				 //totalAmount = MRP * NoOfCelctdITM;
					
					String query = "insert into sales_table (btch_No_p, prd_Nm_s, cmp_Nm_s, prch_date, prch_qnt, mrp) values (?, ?, ?, ?, ?, ?)";
					
					statement = connect.prepareStatement(query);
					statement.setString(1, batchNo);
					statement.setString(2, comboBox.getSelectedItem().toString());
					statement.setString(3, companyName);
					statement.setString(4, dformatte.format(date).toString());
					statement.setString(5, qnty_Textfld.getText().toString());
					statement.setString(6, Integer.toString(totalAmount));
					
					statement.execute();	
					JOptionPane.showMessageDialog(null, "Data inserted to Database");
					
					
					
				}catch (Exception exp_if) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, exp_if);
				}
				
				Object[] sales_table_row = new Object[4];
				sales_table_row[0] = productName;
			    sales_table_row[1] = batchNo;
			    sales_table_row[2] = qnty_Textfld.getText().toString();
			    sales_table_row[3] = Integer.toString(totalAmount);		
			    model_1.addRow(sales_table_row);
			    JOptionPane.showMessageDialog(addtoCart_Bttn, "Added to cart");
			    //updateItemTbl(totalNoItems - NoOfCelctdITM);
				
			}else {
				JOptionPane.showMessageDialog(addtoCart_Bttn, "Stock not available");
			}
			
			
			updateItemTbl(totalNoItems - NoOfCelctdITM);
			}	
			
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		
		//batchNo to sales_table 
		//productName
		//companyName
		//puchsDate
		//NoOfproducts
		//MRP
		
	}
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
	
}
