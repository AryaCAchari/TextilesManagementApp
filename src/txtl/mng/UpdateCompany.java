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
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class UpdateCompany extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel comp_Panel;
	private JButton btnBack;
	private JComboBox<String> cmpNm_cmBox;
	private JTextField ID_textField;
	private JTextField cmpName_textField;
	private JTextField cmpCountry_textField;
	private JTextField cmpEml_textField;
	private JTextField cmpPHn_textField;
	private JTextArea Addrs_textArea;
	private JButton delete_Button;
	private JButton update_Button;
	private JTable company_Table;
	private JScrollPane scrollPane;
	
	private Connection connect = null;
	private PreparedStatement pStatement = null;
	private ResultSet rSet = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateCompany frame = new UpdateCompany();
					frame.setVisible(true);
					//frame.compNamefrmDB_Combox();
					//frame.fillTablefmDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateCompany() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
		}catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getClass().getName() +" : " +e.getMessage());
			System.exit(0);
		}
		
		setTitle("EDIT COMPANY DETAILS");
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
//				UpdateCompany updcmpbk = new UpdateCompany();
//				updcmpbk.dispose();
//				Dashboard cmp = new Dashboard();
//                cmp.setVisible(true);
//                close_page();
//                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
                InsertCompany cmp_insrt = new InsertCompany();
                cmp_insrt.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Consolas", Font.BOLD, 16));
		btnBack.setBounds(10, 606, 97, 33);
		contentPane.add(btnBack);
		
		comp_Panel = new JPanel();
		comp_Panel.setBounds(21, 179, 258, 416);
		comp_Panel.setBackground(new Color(46, 139, 87));
		contentPane.add(comp_Panel);
		comp_Panel.setLayout(null);
		
		cmpNm_cmBox = new JComboBox<String>();
		cmpNm_cmBox.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent arg0) {
			}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				
				String comboMenu = (String) cmpNm_cmBox.getSelectedItem();
				
				try {
										
					String query = "select * from insert_company_table where Comp_Name = ?";
					pStatement = connect.prepareStatement(query);
					pStatement.setString(1, comboMenu);
					rSet = pStatement.executeQuery();
					
					if(rSet.next()) {
						
						String ID = rSet.getString("C_ID");
						ID_textField.setText(ID);
						
						String comNm = rSet.getString("Comp_Name");
						cmpName_textField.setText(comNm);
						
						String comContry = rSet.getString("Comp_Cntry");
						cmpCountry_textField.setText(comContry);
						
						String email = rSet.getString("Comp_Mail");
						cmpEml_textField.setText(email);
						
						String phone = rSet.getString("Comp_Phn");
						cmpPHn_textField.setText(phone);
						
						String address = rSet.getString("Comp_Adrs");
						Addrs_textArea.setText(address);
						
					}
					
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
			public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			}
		});
		cmpNm_cmBox.setBounds(10, 11, 238, 32);
		comp_Panel.add(cmpNm_cmBox);
		
		ID_textField = new JTextField();
		ID_textField.setBounds(10, 64, 238, 26);
		comp_Panel.add(ID_textField);
		ID_textField.setColumns(10);
		
		cmpName_textField = new JTextField();
		cmpName_textField.setColumns(10);
		cmpName_textField.setBounds(10, 101, 238, 26);
		comp_Panel.add(cmpName_textField);
		
		cmpCountry_textField = new JTextField();
		cmpCountry_textField.setColumns(10);
		cmpCountry_textField.setBounds(10, 138, 238, 26);
		comp_Panel.add(cmpCountry_textField);
		
		cmpEml_textField = new JTextField();
		cmpEml_textField.setColumns(10);
		cmpEml_textField.setBounds(10, 175, 238, 26);
		comp_Panel.add(cmpEml_textField);
		
		cmpPHn_textField = new JTextField();
		cmpPHn_textField.setColumns(10);
		cmpPHn_textField.setBounds(10, 212, 238, 26);
		comp_Panel.add(cmpPHn_textField);
		
		Addrs_textArea = new JTextArea();
		Addrs_textArea.setBounds(10, 249, 238, 78);
		comp_Panel.add(Addrs_textArea);
		
		delete_Button = new JButton("");
		delete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int delete_row = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "DELETE", JOptionPane.YES_NO_OPTION);
				
				if(delete_row == 0) {
					
					try {
						
						String sql = "delete from insert_company_table where C_ID = ?";
						pStatement = connect.prepareStatement(sql);
						pStatement.setString(1, ID_textField.getText());
						
						pStatement.executeUpdate();

					}catch (Exception ex_1) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, ex_1);
					}
				}
				
				ID_textField.setText(null);
				cmpName_textField.setText(null);
				cmpCountry_textField.setText(null);
				cmpEml_textField.setText(null);
				cmpPHn_textField.setText(null);
				Addrs_textArea.setText(null);
				
				fillTablefmDB();				
				
			}
		});
		delete_Button.setBounds(167, 338, 64, 64);
		Image img_delete = new ImageIcon(getClass().getResource("/Delete-icon.png")).getImage();
		delete_Button.setIcon(new ImageIcon(img_delete));
		comp_Panel.add(delete_Button);
		
		update_Button = new JButton("");
		update_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
										
					String sql = "update insert_company_table set Comp_Name = ?, Comp_Cntry = ?, Comp_Mail = ?, Comp_Phn = ?, Comp_Adrs = ? where C_ID = ?";
					pStatement = connect.prepareStatement(sql);
					
					pStatement.setString(1, cmpName_textField.getText());
					pStatement.setString(2, cmpCountry_textField.getText());
					pStatement.setString(3, cmpEml_textField.getText());
					pStatement.setString(4, cmpPHn_textField.getText());
					pStatement.setString(5, Addrs_textArea.getText());
					pStatement.setString(6, ID_textField.getText());
					
					pStatement.executeUpdate();
					
				}catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
				
				ID_textField.setText(null);
				cmpName_textField.setText(null);
				cmpCountry_textField.setText(null);
				cmpEml_textField.setText(null);
				cmpPHn_textField.setText(null);
				Addrs_textArea.setText(null);
				
				fillTablefmDB();
				
			}
		});
		update_Button.setBounds(10, 338, 64, 64);
		Image img_updt = new ImageIcon(getClass().getResource("/edit-icon-green.png")).getImage();
		update_Button.setIcon(new ImageIcon(img_updt));
		comp_Panel.add(update_Button);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 179, 1053, 460);
		contentPane.add(scrollPane);
		
		company_Table = new JTable();
		company_Table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Sl.No", "Company", "Country", "Email", "Phone", "Address"
			}
		));
		company_Table.getColumnModel().getColumn(0).setPreferredWidth(25);
		company_Table.getColumnModel().getColumn(1).setPreferredWidth(145);
		company_Table.getColumnModel().getColumn(2).setPreferredWidth(141);
		company_Table.getColumnModel().getColumn(3).setPreferredWidth(137);
		company_Table.getColumnModel().getColumn(4).setPreferredWidth(110);
		company_Table.getColumnModel().getColumn(5).setPreferredWidth(193);
		scrollPane.setViewportView(company_Table);
		
		compNamefrmDB_Combox();
		fillTablefmDB();
		
	}
	
	
	private void compNamefrmDB_Combox() {
		try {
				
				String query = "select Comp_Name from insert_company_table";
				pStatement = (PreparedStatement) connect.prepareStatement(query);
				
				rSet = pStatement.executeQuery();
				while(rSet.next()) {
					cmpNm_cmBox.addItem(rSet.getString("Comp_Name"));
				}
				
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	
	
	
	private void fillTablefmDB() {
		try {
			
			String query = "select C_ID as 'Sl.No', Comp_Name as 'Company', Comp_Cntry as 'Country', Comp_Mail as 'Email', Comp_Phn as 'Phone', Comp_Adrs as 'Address' from insert_company_table";
			pStatement = connect.prepareStatement(query);
			
			rSet = pStatement.executeQuery();
			company_Table.setModel(DbUtils.resultSetToTableModel(rSet));
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
	
	
}
