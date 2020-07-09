import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Welcome extends JFrame implements ActionListener{
	private JButton loginButton;
	private JButton createAccount;
	private JLabel wallpaper;
	private JFrame jFrame1;
	
	public Welcome() {
		
		//super("Welcome!");
		
		initComponents();
		
        loginButton.setText("Login");
        createAccount.setText("Create Account");
        
        setLayout(null);
        setVisible(true);
        
        //setVisible(true);
	}
	private void initComponents() {
		
		jFrame1 = new JFrame();
		loginButton = new JButton();
		createAccount = new JButton();
		
		//setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//setLayout(new FlowLayout());
		//setSize(1000,700);
		//setLocationRelativeTo(null);
		//setResizable(false);
		//setVisible(true);
		
		/*javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );*/
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        wallpaper = new JLabel(new ImageIcon("src\\imgs\\wallpaper.png"));
        wallpaper.setText("wallpaper");
        wallpaper.setMaximumSize(new java.awt.Dimension(1000, 700));
        wallpaper.setMinimumSize(new java.awt.Dimension(1000, 700));
        getContentPane().add(wallpaper);
        wallpaper.setBounds(0, 0, 1000, 700);
        
        loginButton.setText("Login");
        wallpaper.add(loginButton);
        loginButton.setBounds(250, 400, 140, 39);
        loginButton.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == loginButton) {
        			dispose();
        			Login log = new Login();
        			log.setVisible(true);
        		}
        	}
        });
        //loginButton.setVisible(true);
        
        createAccount.setText("Create Account");
        wallpaper.add(createAccount);
        createAccount.setBounds(500, 400, 140, 39);
        createAccount.addActionListener(this);
        createAccount.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == createAccount) {
        			//dispose();
        			CreateAccount CA = new CreateAccount();
        			CA.setVisible(true);
        		}
        	}
        });
           
        pack();
		
		//JPanel panel = new JPanel();
		//panel.setLayout(new FlowLayout());
		//this.add(panel);
		/*
		setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Venus\\Desktop\\wallpaper.png")));
        setLayout(new FlowLayout());
        loginButton = new JButton("Login");
        createAccount = new JButton("Create Account");
        add(loginButton);
        add(createAccount);
        // Just for refresh :) Not optional!
        setSize(99,399);
        setSize(1000,700);
        */
		
		//wallpaper.setSize(1423,893);
		//wallpaper.setLayout(null);
		/*
		 * HERE
        //ImageIcon img = new ImageIcon("wallpaper.jpg");
        setLayout(new BorderLayout());
        wallpaper = new JLabel(new ImageIcon("C:\\Users\\Venus\\Desktop\\wallpaper.png"));
        setLayout(new FlowLayout());
        loginButton = new JButton("Login");
		createAccount = new JButton("Create Account");
        //add(wallpaper);
        //wallpaper.add(loginButton);
        //wallpaper.add(createAccount);
        //wallpaper.setSize(1000,700);
        getContentPane().add(wallpaper);
        getContentPane().add(loginButton);
        getContentPane().add(createAccount);
        wallpaper.setBounds(0, 0, 1000, 700);
        */
		/*
		setLayout(new BorderLayout());
        JLabel wallpaper = new JLabel(new ImageIcon("C:\\Users\\Venus\\Desktop\\wallpaper.png"));
        add(wallpaper);
        wallpaper.setLayout(new FlowLayout());
        loginButton = new JButton("Login");
        createAccount = new JButton("Create Account");
        add(loginButton);
        add(createAccount);
        wallpaper.setSize(1423,893);
        */
		
	}
	public static void main(String []args) {
		new Welcome();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
