package txtl.mng;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;



import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class Dashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField search_Textfeild;
	private JTable table;
	private JButton item_Button;
	private JButton companies_Button;
	private JButton salesRecrd_Bttn;
	private JButton newSales_Bttn;
	private JButton resetPass_Bttn;
	private JButton createUser_Bttn;
	private JButton logoutButton;
	private JButton btnRefresh;
	private JLabel garmenetLabel;
	
	Connection connect = null;
    PreparedStatement Statement = null;
    ResultSet Set= null;
    private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	public Dashboard() {
		
		try {
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Textile_Management_DataBase?allowPublicKeyRetrieval=true&useSSL=false", "root", "root");
			
				}catch (Exception e) {
					// TODO: handle exception
					System.err.println(e.getClass().getName() +" " +e.getMessage());
					System.exit(0);
				}
		
		setTitle("DASHBOARD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1368, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(46, 139, 87));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
				
		search_Textfeild = new JTextField();
		search_Textfeild.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyreleasedSearch();
			}
		});
		search_Textfeild.setBackground(new Color(255, 255, 255));
		search_Textfeild.setBounds(1023, 207, 219, 20);
		contentPane.add(search_Textfeild);
		search_Textfeild.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(379, 277, 905, 302);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Sl.No", "Batch No", "Product Name", "Product Category", "Company Name", "Quantity", "Price/Unit"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(38);
		table.getColumnModel().getColumn(1).setPreferredWidth(119);
		table.getColumnModel().getColumn(2).setPreferredWidth(164);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setPreferredWidth(149);
		scrollPane.setViewportView(table);
		
		item_Button = new JButton("ITEMS");
		item_Button.setBounds(110, 280, 190, 23);
		item_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dashboard objfrm = new Dashboard();
//				objfrm.dispose();
				dispose();
				InsertItems pdtlsItm = new InsertItems();
                pdtlsItm.setVisible(true);
			}
		});
		item_Button.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		item_Button.setForeground(Color.BLACK);
		item_Button.setBackground(Color.WHITE);
		contentPane.add(item_Button);
		
		companies_Button = new JButton("COMPANIES");
		companies_Button.setBounds(110, 331, 190, 23);
		companies_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dashboard objfrm_3 = new Dashboard();
//				objfrm_3.dispose();
				dispose();
				InsertCompany cmpdtls = new InsertCompany();
                cmpdtls.setVisible(true);
			}
		});
		companies_Button.setForeground(Color.BLACK);
		companies_Button.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		companies_Button.setBackground(Color.WHITE);
		contentPane.add(companies_Button);
		
		salesRecrd_Bttn = new JButton("VIEW SALES RECORD");
		salesRecrd_Bttn.setBounds(110, 376, 190, 23);
		salesRecrd_Bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dashboard objfrm1 = new Dashboard();
//				objfrm1.dispose();
				dispose();
                ViewSalesRecord vsr = new ViewSalesRecord();
                vsr.setVisible(true);
				
			}
		});
		salesRecrd_Bttn.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		salesRecrd_Bttn.setForeground(Color.BLACK);
		salesRecrd_Bttn.setBackground(Color.WHITE);
		contentPane.add(salesRecrd_Bttn);
		
		newSales_Bttn = new JButton("NEW SALES");
		newSales_Bttn.setBounds(110, 420, 190, 23);
		newSales_Bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dashboard objfrm2 = new Dashboard();
//				objfrm2.dispose();
				dispose();
                NewSales ns = new NewSales();
                ns.setVisible(true);
			}
		});
		newSales_Bttn.setForeground(Color.BLACK);
		newSales_Bttn.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		newSales_Bttn.setBackground(Color.WHITE);
		contentPane.add(newSales_Bttn);
		
		resetPass_Bttn = new JButton("CHANGE PASSWORD");
		resetPass_Bttn.setBounds(110, 466, 190, 23);
		resetPass_Bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dashboard objfrm_5 = new Dashboard();
//				objfrm_5.dispose();
				dispose();
                ChangePassword cp_d = new ChangePassword();
                cp_d.setVisible(true);  
			}
		});
		resetPass_Bttn.setForeground(Color.BLACK);
		resetPass_Bttn.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		resetPass_Bttn.setBackground(Color.WHITE);
		contentPane.add(resetPass_Bttn);
		
		createUser_Bttn = new JButton("CREATE USER");
		createUser_Bttn.setBounds(110, 513, 190, 23);
		createUser_Bttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Dashboard objfrm_6 = new Dashboard();
//				objfrm_6.dispose();
				dispose();
                CreateUsers cu_d = new CreateUsers();
                cu_d.setVisible(true);
			}
		});
		createUser_Bttn.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 15));
		createUser_Bttn.setForeground(Color.BLACK);
		createUser_Bttn.setBackground(Color.WHITE);
		contentPane.add(createUser_Bttn);
		
		logoutButton = new JButton("");
		logoutButton.setBounds(73, 559, 32, 32);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LogoutactionPerformed(e);	
				
			}
		});
		Image imglogout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		logoutButton.setIcon(new ImageIcon(imglogout));
		contentPane.add(logoutButton);
		
		btnRefresh = new JButton();
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fromTable();
			}
		});
		Image imgrfresh = new ImageIcon(this.getClass().getResource("/Button-Refresh-icon.png")).getImage();
		btnRefresh.setIcon(new ImageIcon(imgrfresh));
		btnRefresh.setBounds(1252, 206, 32, 32);
		contentPane.add(btnRefresh);
		
		garmenetLabel = new JLabel();
		garmenetLabel.setBounds(26, 23, 195, 181);
		Image imgGarmentLogo = new ImageIcon(this.getClass().getResource("/imageGarment.jpg")).getImage();
		garmenetLabel.setIcon(new ImageIcon(imgGarmentLogo));
		contentPane.add(garmenetLabel);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(246, 23, 912, 113);
//		Image imgGarment = new ImageIcon(this.getClass().getResource()).getImage();
//		lblNewLabel.setIcon(imgGarment);
		contentPane.add(lblNewLabel);
		
		fromTable();
		
	}
	
	private void fromTable() {
		
		String sql = "select Itm_ID as 'Sl.No', btch_num as 'Batch No' , Prdct_name as 'Product Name', Prd_Catgry as 'Product Category', Cmp_nm as 'Company Name', Prdct_Qunty as 'Quantity', Price_Unit as 'Price/Unit' from insrt_itm_table";
		
		try {
			Statement = (PreparedStatement) connect.prepareStatement(sql);
			Set = (ResultSet) Statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(Set));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	private void keyreleasedSearch() {
		
		String sql = "select Itm_ID as 'Sl.No', btch_num as 'Batch No' , Prdct_name as 'Product Name', Prd_Catgry as 'Product Category', Cmp_nm as 'Company Name', Prdct_Qunty as 'Quantity', Price_Unit as 'Price/Unit' from insrt_itm_table where Itm_ID = ? or btch_num = ? or Prdct_name = ? or Prd_Catgry = ? or Cmp_nm = ? or Prdct_Qunty = ? or Price_Unit = ?";
		
		try {
			
			Statement = connect.prepareStatement(sql);
			
			Statement.setString(1, search_Textfeild.getText());
			Statement.setString(2, search_Textfeild.getText());
			Statement.setString(3, search_Textfeild.getText());
			Statement.setString(4, search_Textfeild.getText());
			Statement.setString(5, search_Textfeild.getText());
			Statement.setString(6, search_Textfeild.getText());
			Statement.setString(7, search_Textfeild.getText());
			
			Set = Statement.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(Set));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public void LogoutactionPerformed(ActionEvent evt) {
		
		try {
			Set.close();
            Statement.close();
            connect.close();
		}catch (Exception e_logout) {
			// TODO: handle exception
			e_logout.printStackTrace();
		}
		
		dispose();
		TextileManagementClass tmLout = new TextileManagementClass();
		tmLout.setVisible(true);
		
		close_page();
		
	}
	
	
	public void close_page() {
		WindowEvent wnidoclose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wnidoclose);
	}
}
