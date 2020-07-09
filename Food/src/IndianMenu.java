import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class IndianMenu extends JFrame implements ActionListener{
	private JLabel menubg;
	private JLabel title;
	private JLabel indianheading;
	private JTable indiantable;
	private JScrollPane jScrollPane1;
	private JButton addtocart;
	
	static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;
    static PreparedStatement ps = null;
    static int amount[] = new int[40];
    static String price[] = new String[40];
    static String dishname[] = new String[40];
    static String user;
    static String d[] = new String[40];
    int total = 0;
    int rtc;
    int rowsAffected;
    
	public IndianMenu() {
		super("Indian Menu");
		
		try {
            initComponents();
            JTableHeader header = indiantable.getTableHeader();
            header.setEnabled(false);
            DefaultTableModel model = (DefaultTableModel) indiantable.getModel();
            connection();
            String q = "SELECT * FROM food.indian;";
            rs = stmt.executeQuery(q);
            int s = model.getRowCount();
            while (s != 0) {
                model.removeRow(0);
                s--;
            }
            while (rs.next()) {
            	
            	int id = rs.getInt("IND_id");
                String name = rs.getString("IND_name");
                float price = rs.getFloat("IND_price");
                Object row[] = {id, name, price, '0'};
                model.addRow(row);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndianMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void initComponents() {
		menubg = new JLabel();
		indianheading = new JLabel();
		indiantable = new JTable();
		jScrollPane1 = new JScrollPane();
		addtocart = new JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 820));
        setResizable(false);
        getContentPane().setLayout(null);
        
        title = new JLabel(new ImageIcon("src//imgs//indiantitle.png")); // NOI18N
        title.setOpaque(true);
        getContentPane().add(title);
        title.setBounds(170, 120, 244, 61);
        
        indianheading = new JLabel(new ImageIcon("src//imgs//indianheading.png")); // NOI18N
        indianheading.setOpaque(true);
        getContentPane().add(indianheading);
        indianheading.setBounds(90, 190, 400, 25);
        
        //indiantable.setBackground(new java.awt.Color(51, 51, 51));
        indiantable.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        indiantable.setFont(new java.awt.Font("Bookman Old Style", 0, 18)); // NOI18N
        indiantable.setForeground(new java.awt.Color(128,0,128));
        indiantable.setRowHeight(40);
        indiantable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        indiantable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        indiantable.setName(""); // NOI18N
        jScrollPane1.setViewportView(indiantable);
      
        
        if (indiantable.getColumnModel().getColumnCount() > 0) {
            indiantable.getColumnModel().getColumn(0).setResizable(false);
            indiantable.getColumnModel().getColumn(0).setPreferredWidth(50);
            indiantable.getColumnModel().getColumn(1).setResizable(false);
            indiantable.getColumnModel().getColumn(1).setPreferredWidth(200);
            indiantable.getColumnModel().getColumn(2).setResizable(false);
            indiantable.getColumnModel().getColumn(2).setPreferredWidth(80);
            indiantable.getColumnModel().getColumn(3).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 220, 400, 408);
        
        addtocart.setText("Add to Cart");
        getContentPane().add(addtocart);
        addtocart.setBounds(210,630,140,40);
        addtocart.addActionListener(this);
        
        menubg = new JLabel(new ImageIcon("src//imgs//indianmenu.png"));
        //menubg.setIcon(new ImageIcon("C:\\Users\\Venus\\Desktop\\resize-15936008151897207572Capture2.png")); // NOI18N
        menubg.setText("Menu Background");
        menubg.setMaximumSize(new java.awt.Dimension(600, 820));
        menubg.setMinimumSize(new java.awt.Dimension(600, 820));
        //menubg.setPreferredSize(new java.awt.Dimension(599, 890));
        getContentPane().add(menubg);
        menubg.setBounds(0, 0, 600, 820);
        
        pack();
        
	}
	
	public static void connection() {
		try {
	
	        Class.forName("java.sql.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","iamroot");
	        stmt = con.createStatement();
	
	    } catch (ClassNotFoundException | SQLException e) {
	        JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
	
	    }
	}
	
	public void actionPerformed(ActionEvent e) {//GEN-FIRST:event_jButton1ActionPerformed
		try {
			int i = 0;
	        DefaultTableModel model = (DefaultTableModel) indiantable.getModel();
	        int rc = model.getRowCount();
	        rtc = 0;
	        connection();
	        //System.out.println(rc);
	        while (i != rc) {
	            d[i] = indiantable.getValueAt(i, 3).toString();
	            if (Integer.parseInt(d[i]) > 0) {
	            	String dish = indiantable.getValueAt(i, 1).toString();
	            	String pr = indiantable.getValueAt(i, 2).toString();
	            	float pric = Float.parseFloat(pr);
	            	int quant = Integer.parseInt(d[i]);
	            	PreparedStatement st = (PreparedStatement) con.prepareStatement("INSERT INTO food.cart (item_name,item_price,item_quantity) VALUES (?,?,?);");
	                st.setString(1, dish);
	                st.setFloat(2, pric);
	                st.setInt(3, quant);
	                rowsAffected = st.executeUpdate();
	            }
	            i++;
	        }
	        if(rowsAffected == 1) {
	        	dispose();
	        	JOptionPane.showMessageDialog(null, "Items Added!");
	        }
		}
		catch(Exception except) {
			System.out.println("Error "+except);
		}
	}

	public static void main(String[] args) {
		new IndianMenu().setVisible(true);

	}

}
