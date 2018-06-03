package txtl.mng;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class ViewSalesRecord extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField search;
	private JTable salesRcrd_Table;
	private JButton btnDelete;
	private DefaultTableModel model;
	
	
	Connection conect = null;
	PreparedStatement statement = null;
	ResultSet rSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSalesRecord frame = new ViewSalesRecord();
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
	public ViewSalesRecord() {
		
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" : " +e.getMessage());
			System.exit(0);
		}
		
		
		
		setTitle("SALES RECORD");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		search = new JTextField();
		search.setFont(new Font("Consolas", Font.PLAIN, 16));
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String query = "select s_ID as 'Sl.No', btch_No_p as 'Batch No', prd_Nm_s as 'Items', cmp_Nm_s as 'Company Name', prch_date as 'Date', prch_qnt as 'Quantity', mrp as 'Price' from sales_table where s_ID = ? or btch_No_p = ? or prd_Nm_s = ? or cmp_Nm_s = ? or prch_date = ? or prch_qnt = ? or mrp = ?";
				
				try {
					
					statement = conect.prepareStatement(query);
				    statement.setString(1, search.getText());
				    statement.setString(2, search.getText());
				    statement.setString(3, search.getText());
				    statement.setString(4, search.getText());
				    statement.setString(5, search.getText());
				    statement.setString(6, search.getText());
				    statement.setString(7, search.getText());
				    rSet = statement.executeQuery();
				    
				    salesRcrd_Table.setModel(DbUtils.resultSetToTableModel(rSet));
					
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		});
		search.setBounds(1067, 203, 211, 20);
		contentPane.add(search);
		search.setColumns(10);
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				ViewSalesRecord vsr = new ViewSalesRecord();
//				vsr.dispose();
//                Dashboard dshbrd = new Dashboard();
//                dshbrd.setVisible(true);
//                close_page();
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
                Dashboard dshbrd = new Dashboard(); 
                dshbrd.setVisible(true);  
			}
		});
		btnBack.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBack.setBounds(44, 616, 94, 23);
		contentPane.add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 244, 1221, 348);
		contentPane.add(scrollPane);
		
		salesRcrd_Table = new JTable();
		salesRcrd_Table.setFont(new Font("Consolas", Font.BOLD, 12));
		scrollPane.setViewportView(salesRcrd_Table);
		Object[] columName = {"Sl.No", "Batch No", "Items", "Company Name", "Date", "Quantity", "Price"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columName);
		salesRcrd_Table.setModel(model);
		
		btnDelete = new JButton("DELETE");
		btnDelete.setFont(new Font("Consolas", Font.BOLD, 14));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = salesRcrd_Table.getSelectedRow();
				String id = salesRcrd_Table.getModel().getValueAt(row, 0).toString();
//				String batch_No = salesRcrd_Table.getModel().getValueAt(row, 1).toString();
//				String item_Name = salesRcrd_Table.getModel().getValueAt(row, 2).toString();
//				String cmpany_Name = salesRcrd_Table.getModel().getValueAt(row, 3).toString();
//				String prchs_Date = salesRcrd_Table.getModel().getValueAt(row, 4).toString();
//				String Quantity = salesRcrd_Table.getModel().getValueAt(row, 5).toString();
//				String price_Unit = salesRcrd_Table.getModel().getValueAt(row, 6).toString();
				
				String query = "delete from sales_table where s_ID = '"+id+"' ";
				
				try {
					statement = conect.prepareStatement(query);
					statement.execute();
					JOptionPane.showMessageDialog(null, "Record Deleted");
					displayTable();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		});
		btnDelete.setBounds(1231, 603, 89, 23);
		contentPane.add(btnDelete);
		
		JButton refesh_Button = new JButton("");
		refesh_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayTable();
			}
		});
		Image refresh = new ImageIcon(this.getClass().getResource("/Button-Refresh-icon.png")).getImage();
		refesh_Button.setIcon(new ImageIcon(refresh));
		refesh_Button.setBounds(1288, 193, 32, 32);
		contentPane.add(refesh_Button);
		
		salesRecorTable();
	}
	
	
	
	private void salesRecorTable() {
		
		int rows = salesRcrd_Table.getRowCount();
		
		if(rows == 0) {
			
			String sql = "select * from sales_table";
			Object[] row = new Object[7];
			
			try {
				
				statement = conect.prepareStatement(sql);
				rSet = statement.executeQuery();
				
				while(rSet.next()) {
					
					row[0] = rSet.getString("s_ID"); 
					row[1] = rSet.getString("btch_No_p");
					row[2] = rSet.getString("prd_Nm_s");
					row[3] = rSet.getString("cmp_Nm_s");
					row[4] = rSet.getString("prch_date");
					row[5] = rSet.getString("prch_qnt");
					row[6] = rSet.getString("mrp");
					
					model.addRow(row);
					
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
			
		}else {
			
			model.removeRow(rows - 1);
			
			String sql = "select * from sales_table";
			Object[] row = new Object[7];
			
			try {
				
				statement = conect.prepareStatement(sql);
				rSet = statement.executeQuery();
				
				while(rSet.next()) {
					
					row[0] = rSet.getString("s_ID"); 
					row[1] = rSet.getString("btch_No_p");
					row[2] = rSet.getString("prd_Nm_s");
					row[3] = rSet.getString("cmp_Nm_s");
					row[4] = rSet.getString("prch_date");
					row[5] = rSet.getString("prch_qnt");
					row[6] = rSet.getString("mrp");
					
					model.addRow(row);
					
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
//
	
	
	private void displayTable() {
		
		String query = "select s_ID as 'Sl.No', btch_No_p as 'Batch No', prd_Nm_s as 'Items', cmp_Nm_s as 'Company Name', prch_date as 'Date', prch_qnt as 'Quantity', mrp as 'Price' from sales_table";
		
		
		try {
			
			statement = conect.prepareStatement(query);
		    
		    rSet = statement.executeQuery();
		    
		    salesRcrd_Table.setModel(DbUtils.resultSetToTableModel(rSet));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
	
}
