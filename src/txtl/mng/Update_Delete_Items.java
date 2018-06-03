package txtl.mng;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;


public class Update_Delete_Items extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable updItm_tblDB;
	private JPanel table_Jpanel;
	private JComboBox<String> Pnm_frm_db;
	private JPanel update_Jpanl;
	
	private Connection con = null; 
	private PreparedStatement prpstm = null;
	private ResultSet rset = null;
	
	private JTextField ID_txtField;
	private JTextField Batch_textField;
	private JTextField prdNm_textField;
	private JTextField prdCAT_textField;
	private JTextField cmpNm_textField;
	private JTextField qnty_TextField;
	private JTextField mrp_TextField;
	private JButton upd_itm_mrp;
	private JButton delete_Bttn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Delete_Items frame = new Update_Delete_Items();
					//frame.prodtcNameFrm_db();
					//frame.dt_tbl_DB();
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
	public Update_Delete_Items() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" : " +e.getMessage());
			System.exit(0);
		}
		
		setTitle("EDIT ITEM DETAILS");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_Jpanel = new JPanel();
		table_Jpanel.setBounds(364, 176, 978, 463);
		contentPane.add(table_Jpanel);
		table_Jpanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 958, 441);
		table_Jpanel.add(scrollPane);
		
		updItm_tblDB = new JTable();
		updItm_tblDB.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				
			},
			new String[] {
				"ID", "Batch No", "Product Name", "Product Category", "Company Name", "Quantity", "Price/Unit"
			}
		));
		updItm_tblDB.getColumnModel().getColumn(0).setPreferredWidth(38);
		updItm_tblDB.getColumnModel().getColumn(1).setPreferredWidth(119);
		updItm_tblDB.getColumnModel().getColumn(2).setPreferredWidth(164);
		updItm_tblDB.getColumnModel().getColumn(3).setPreferredWidth(130);
		updItm_tblDB.getColumnModel().getColumn(4).setPreferredWidth(149);
		scrollPane.setViewportView(updItm_tblDB);
		
		JButton btnNewButton_1 = new JButton("BACK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Update_Delete_Items upditms = new Update_Delete_Items();
//				upditms.dispose();
//				Dashboard pdctdtls = new Dashboard();
//                pdctdtls.setVisible(true);
//                close_page();
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();
                InsertItems institm = new InsertItems();
                institm.setVisible(true);		
			}
		});
		btnNewButton_1.setFont(new Font("Consolas", Font.BOLD, 16));
//		Image img_1 = new ImageIcon(this.getClass().getResource("/arrow-back-icon.png")).getImage();
//		btnNewButton_1.setIcon(new ImageIcon(img_1));
		btnNewButton_1.setBounds(10, 610, 95, 29);
		contentPane.add(btnNewButton_1);
		
		update_Jpanl = new JPanel();
		update_Jpanl.setBounds(77, 176, 239, 430);
		update_Jpanl.setBackground(new Color(46, 139, 87));
		contentPane.add(update_Jpanl);
		update_Jpanl.setLayout(null);
		
		Pnm_frm_db = new JComboBox<String>();
		Pnm_frm_db.setFont(new Font("Consolas", Font.PLAIN, 16));
		Pnm_frm_db.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
				String pMenu = (String) Pnm_frm_db.getSelectedItem();
								
				try {
					 
					String query = "select * from insrt_itm_table where Prdct_name = ?";
					prpstm = con.prepareStatement(query);
					prpstm.setString(1, pMenu);
					rset = prpstm.executeQuery();
					if(rset.next()) {
						
						String ID  = rset.getString("Itm_ID");
						ID_txtField.setText(ID);
						
						String BatchNo  = rset.getString("btch_num");
						Batch_textField.setText(BatchNo);
						
						String PdctNm  = rset.getString("Prdct_name");
						prdNm_textField.setText(PdctNm);
						
						String CmpNm  = rset.getString("Cmp_nm");
						cmpNm_textField.setText(CmpNm);
						
						String prdQnt  = rset.getString("Prdct_Qunty");
						qnty_TextField.setText(prdQnt);
						
						String mrpUnit  = rset.getString("Price_Unit");
						mrp_TextField.setText(mrpUnit);
						
						String pCat  = rset.getString("Prd_Catgry");
						prdCAT_textField.setText(pCat);
					}
					 
				 }catch (Exception ex) {
					// TODO: handle exception
					 ex.printStackTrace();
				}
				
				dt_tbl_DB();
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
		});
		Pnm_frm_db.setBounds(27, 11, 183, 31);
		update_Jpanl.add(Pnm_frm_db);
		
		ID_txtField = new JTextField();
		ID_txtField.setFont(new Font("Consolas", Font.PLAIN, 16));
		ID_txtField.setBounds(27, 63, 183, 31);
		update_Jpanl.add(ID_txtField);
		ID_txtField.setColumns(10);
		
		Batch_textField = new JTextField();
		Batch_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		Batch_textField.setColumns(10);
		Batch_textField.setBounds(27, 105, 183, 31);
		update_Jpanl.add(Batch_textField);
		
		prdNm_textField = new JTextField();
		prdNm_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		prdNm_textField.setColumns(10);
		prdNm_textField.setBounds(27, 147, 183, 31);
		update_Jpanl.add(prdNm_textField);
		
		prdCAT_textField = new JTextField();
		prdCAT_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		prdCAT_textField.setColumns(10);
		prdCAT_textField.setBounds(27, 189, 183, 31);
		update_Jpanl.add(prdCAT_textField);
		
		cmpNm_textField = new JTextField();
		cmpNm_textField.setFont(new Font("Consolas", Font.PLAIN, 16));
		cmpNm_textField.setColumns(10);
		cmpNm_textField.setBounds(27, 231, 183, 31);
		update_Jpanl.add(cmpNm_textField);
		
		qnty_TextField = new JTextField();
		qnty_TextField.setFont(new Font("Consolas", Font.PLAIN, 16));
		qnty_TextField.setColumns(10);
		qnty_TextField.setBounds(27, 273, 183, 31);
		update_Jpanl.add(qnty_TextField);
		
		mrp_TextField = new JTextField();
		mrp_TextField.setFont(new Font("Consolas", Font.PLAIN, 16));
		mrp_TextField.setColumns(10);
		mrp_TextField.setBounds(27, 315, 183, 31);
		update_Jpanl.add(mrp_TextField);
		
		upd_itm_mrp = new JButton("");
		upd_itm_mrp.setBounds(27, 357, 64, 64);
		upd_itm_mrp.setBackground(new Color(46, 139, 87));
		Image img = new ImageIcon(this.getClass().getResource("/edit-icon-green.png")).getImage();
		upd_itm_mrp.setIcon(new ImageIcon(img));
		update_Jpanl.add(upd_itm_mrp);
		upd_itm_mrp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
//				DefaultTableModel model = (DefaultTableModel) updItm_tblDB.getModel();
//				
//				int selectedRowIndex = updItm_tblDB.getSelectedRow();
//				
//				String ID = model.getValueAt(selectedRowIndex, 0).toString();
//				String Batch_No = model.getValueAt(selectedRowIndex, 1).toString();
//				String Product_Name = model.getValueAt(selectedRowIndex, 2).toString();				
//				String Company_Name = model.getValueAt(selectedRowIndex, 3).toString();
//				String Quantity = model.getValueAt(selectedRowIndex, 4).toString();
//				String Price_Unit = model.getValueAt(selectedRowIndex, 5).toString();
//				String Product_Category = model.getValueAt(selectedRowIndex, 6).toString();
//				
//				String New_Quantity = JOptionPane.showInputDialog(null, "Enter new quantity.", Quantity);
//				String New_MRP = JOptionPane.showInputDialog(null, "Enter new Price.", Price_Unit);
//				
//				model.setValueAt(New_Quantity, selectedRowIndex, 4);
//				model.setValueAt(New_MRP, selectedRowIndex, 5);
				
				try {
					
					String sql = "Update insrt_itm_table set btch_num = ?, Prdct_name  = ?, Cmp_nm = ?, Prdct_Qunty = ?, Price_Unit = ?, Prd_Catgry = ? where Itm_ID  = ? ";
					prpstm = con.prepareStatement(sql);
					
					prpstm.setString(1, Batch_textField.getText());
					prpstm.setString(2, prdNm_textField.getText());
					prpstm.setString(3, cmpNm_textField.getText());
					prpstm.setString(4, qnty_TextField.getText());
					prpstm.setString(5, mrp_TextField.getText());
					prpstm.setString(6, prdCAT_textField.getText());
					prpstm.setString(7, ID_txtField.getText());
					
					prpstm.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Updated");
					
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
				
				Batch_textField.setText(null);
				prdNm_textField.setText(null);
			    cmpNm_textField.setText(null);
			    qnty_TextField.setText(null);
			    mrp_TextField.setText(null);
			    prdCAT_textField.setText(null);
			    ID_txtField.setText(null);
			    
			    dt_tbl_DB();
				
			}
		});
		upd_itm_mrp.setFont(new Font("Consolas", Font.BOLD, 16));
		
		delete_Bttn = new JButton("");
		delete_Bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int delt_action = JOptionPane.showConfirmDialog(null, "Do you really want to delete", "DELETE", JOptionPane.YES_NO_OPTION);
				
				if(delt_action == 0) {
					try {
						
						String sql = "delete from insrt_itm_table where Itm_ID = ? ";
						prpstm = con.prepareStatement(sql);
						prpstm.setString(1, ID_txtField.getText());
						
						prpstm.executeUpdate();
						
					}catch(Exception ex_1) {
						JOptionPane.showMessageDialog(null, ex_1);
					}
				}
				
				Batch_textField.setText(null);
				prdNm_textField.setText(null);
			    cmpNm_textField.setText(null);
			    qnty_TextField.setText(null);
			    mrp_TextField.setText(null);
			    prdCAT_textField.setText(null);
			    ID_txtField.setText(null);
				
				//dt_tbl_DB(); //show table when combo selected. 
				
			}
		});
		delete_Bttn.setFont(new Font("Consolas", Font.BOLD, 16));
		delete_Bttn.setBackground(new Color(46, 139, 87));
		delete_Bttn.setBounds(146, 357, 64, 64);
		Image img_2 = new ImageIcon(this.getClass().getResource("/Delete-icon.png")).getImage();
		delete_Bttn.setIcon(new ImageIcon(img_2));
		update_Jpanl.add(delete_Bttn);
		
//		Pnm_frm_db.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//			}
//		});
		
		prodtcNameFrm_db();
		dt_tbl_DB();

	}
	
	
	private void prodtcNameFrm_db() {
		try {
			
//			Class.forName("com.mysql.jdbc.Driver");
//			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase", "root","root");
			
			String sql = "select  Prdct_name from insrt_itm_table";
			prpstm = (PreparedStatement) con.prepareStatement(sql);
			rset = (ResultSet) prpstm.executeQuery();
			
			while(rset.next()) {
				Pnm_frm_db.addItem(rset.getString("Prdct_name"));
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void dt_tbl_DB() {
		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase", "root","root");
			
//			updItm_tblDB.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//			updItm_tblDB.setColumnSelectionAllowed(true);
//			updItm_tblDB.setRowSelectionAllowed(false);
//			
//			updItm_tblDB.setColumnSelectionInterval(0, 6);
			
			String sql = "select Itm_ID as 'Sl.No', btch_num as 'Batch No' , Prdct_name as 'Product Name', Prd_Catgry as 'Product Category', Cmp_nm as 'Company Name', Prdct_Qunty as 'Quantity', Price_Unit as 'Price/Unit' from insrt_itm_table";
			prpstm = con.prepareStatement(sql);
			//String productName = updItm_tblDB.getColumnName((2));
			
			rset = prpstm.executeQuery();
			updItm_tblDB.setModel(DbUtils.resultSetToTableModel(rset));
			
			
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		    //e.printStackTrace();
		}
	}
	
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
	
}
