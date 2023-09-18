package computerScience;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

public class UserLogin extends DatabaseConnect {
	
    ResultSet set;
    Statement statement;
    PreparedStatement st;

	JFrame loginframe;
	protected JTextField username;
	protected JButton btnlogin;
	protected JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin window = new UserLogin();
					window.loginframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginframe = new JFrame();
		loginframe.setBounds(100, 100, 333, 438);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 333, 416);
		panel.setBackground(Color.WHITE);
		loginframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		username = new JTextField();
		username.setText("username");
		username.setBounds(87, 232, 150, 26);
		panel.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 33));
		lblNewLabel.setBounds(98, 6, 130, 57);
		panel.add(lblNewLabel);
		
		btnlogin = new JButton("Sign in");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username1 = username.getText();
				String password = String.valueOf(passwordField.getPassword());
	

			        try {
			        	String sql1 = "SELECT Username, Password FROM UserInput WHERE Username = '" + username1 + "'";
			        	connectDatabase();
			            st =conn.prepareStatement(sql1);
			            set = st.executeQuery();
			            if(set.next()) {
				            if(password.equals(set.getString("Password"))) {
				            	JOptionPane.showMessageDialog(null, "Login Successful");
				            	User user = new User();
						        mainframe main = new mainframe();
							    user.setName(username1);
						        main.setVisible(true);
							//    main.labelhello.setText("Hello" + user.getName());
 
				            	loginframe.dispose();
				            } else {
				            	JOptionPane.showMessageDialog(null, "Invalid Username / Password", "Login Error", 2);
	
				            }
			            }


			        } catch (SQLException ex) {
			            JOptionPane.showMessageDialog(null, ex);
			        } 
			        

			}
				
						
	
		});
		btnlogin.setBounds(98, 362, 117, 29);
		panel.add(btnlogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(87, 282, 150, 26);
		panel.add(passwordField);
		
		final JCheckBox showPass = new JCheckBox("Show Password");
		showPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showPass.isSelected()) {
					passwordField.setEchoChar((char)0);
				} else {
					passwordField.setEchoChar('●');
				}
			}
		});
		showPass.setBounds(100, 309, 128, 23);
		panel.add(showPass);
		
		JLabel labelimg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/login copy.png")).getImage();
		labelimg.setIcon(new ImageIcon(img));
		labelimg.setBounds(108, 75, 130, 145);
		panel.add(labelimg);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
