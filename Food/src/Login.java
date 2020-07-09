import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Login extends JFrame implements ActionListener{
	public static String user;
	private JLabel title;
	private JLabel label1;
	private JLabel label2;
	public static JTextField username;
	private JPasswordField password;
	private JButton login;
	private JButton forgotpass;
	
	public Login() {
		super("Login");
		
		title = new JLabel("Login");
		title.setFont(new java.awt.Font("Times New Roman", 0, 20));
		title.setBounds(220, 50, 140, 40);
		add(title);
		
		label1 = new JLabel("Username");
		label1.setBounds(125, 100, 140, 40);
		add(label1);
		
		label2 = new JLabel("Password");
		label2.setBounds(125, 150 , 140 ,40);
		add(label2);
		
		username = new JTextField();
		username.setBounds(200, 110, 140, 30);
		add(username);
		
		password = new JPasswordField();
		password.setBounds(200, 155, 140, 30);
		add(password);
		
		login = new JButton("Login");
		login.setBounds(110, 220, 140, 40);
		add(login);
		login.addActionListener(this);
		
		forgotpass = new JButton("Forgot Password");
		forgotpass.setBounds(260, 220, 140, 40);
		add(forgotpass);
		forgotpass.addActionListener(this);
		
		
		setSize(500,350);
		setResizable(false);
		//setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setLayout(null);
		//setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		user = username.getText();
		String pass = String.valueOf(password.getPassword());
		
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","iamroot");
			PreparedStatement st = (PreparedStatement) conn.prepareStatement("SELECT username,passwd FROM Details WHERE username=? AND passwd=?");
			st.setString(1, user);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			
			if(e.getSource() == login) {
				if(rs.next()) {
					if(user.equals("admin") && pass.equals("admin")) {
						JOptionPane.showMessageDialog(login, "Welcome Admin!");
						Admin ad = new Admin();
						ad.setVisible(true);
						this.dispose();
					}
					else if(user.equals("manager") && pass.equals("manager")) {
						JOptionPane.showMessageDialog(login, "Welcome Mr. Manager!");
						RestaurantManager rm = new RestaurantManager();
						rm.setVisible(true);
						this.dispose();
					}
					else {
						this.dispose();
						JOptionPane.showMessageDialog(login, "You have successfully logged in");
						Home h = new Home();
						h.setVisible(true);	
					}
				}
				else { 
					//this.dispose();
					JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			if(e.getSource() == forgotpass) {
				ForgotPassword fp = new ForgotPassword();
				fp.setVisible(true);
			}
		}
		
		catch(Exception except) {
			System.out.println("Error "+except);
		}
	}
	
	public static void main(String[]args) {
		
		new Login().setVisible(true);;
		
		/*
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/food","root","iamroot");
			Statement mystmt = conn.createStatement();
			ResultSet rs = mystmt.executeQuery("select * from details");
			while(rs.next()) {
				//System.out.println(rs.getString("username")+","+rs.getString("passwd"));
			}
		}
		catch(Exception e) {
			System.out.println("Error");
		}
		*/
	}
}
