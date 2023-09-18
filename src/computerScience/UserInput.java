package computerScience;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserInput extends DatabaseConnect {

    ResultSet set;
    Statement statement;
    PreparedStatement st;

	JFrame inputframe;
	private JTextField txtPassword;
	private JTextField firstname;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JTextField txtConfirmPassword;
	private JTextField txtLastName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInput window = new UserInput();
					window.inputframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserInput() {
		initialize();
		connectDatabase();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inputframe = new JFrame();
		inputframe.setBounds(100, 100, 270, 369);
		inputframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inputframe.getContentPane().setLayout(null);
		inputframe.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 269, 347);
		inputframe.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText(null);
			}
		});	
		txtUsername.setText("Username");
		txtUsername.setBounds(24, 159, 209, 26);
		panel.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEmail.setText(null);
			}
		});	
		txtEmail.setText("Email");
		txtEmail.setBounds(24, 121, 209, 26);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		firstname = new JTextField();
		firstname.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				firstname.setText(null);
			}
		});
		firstname.setText("First Name");
		firstname.setBounds(24, 83, 102, 26);
		panel.add(firstname);
		firstname.setColumns(10);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtConfirmPassword.setText(null);
			}
		});	
		txtConfirmPassword.setText("Confirm Password");
		txtConfirmPassword.setBounds(24, 235, 209, 26);
		panel.add(txtConfirmPassword);
		txtConfirmPassword.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setText(null);
			}
		});	
		txtPassword.setText("Password");
		txtPassword.setBounds(24, 197, 209, 26);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtLastName.setText(null);
			}
		});
		txtLastName.setText("Last Name");
		txtLastName.setColumns(10);
		txtLastName.setBounds(131, 83, 102, 26);
		panel.add(txtLastName);
		
		JLabel lblNewLabel = new JLabel("User Input");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
		lblNewLabel.setBounds(54, 25, 151, 46);
		panel.add(lblNewLabel);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			       String name = firstname.getText();
			       String lastname = txtLastName.getText();
			       String username = txtUsername.getText();
			       String password = txtPassword.getText();
			       String email = txtEmail.getText();

			        try{
			            String sql =  "INSERT INTO UserInput(FirstName, Surename, Username, Password, Email) VALUES (?,?,?,?,?);";
			            //inserting the values that are inputed within this form into the database using the sql language
			            connectDatabase(); //using the function to connect to the database
			            st=conn.prepareStatement(sql);
			            //the preparestatement function is used to execute the query so in this case it will be to insert the information into the lab
			            st.setString(1, name);
			            st.setString(2, lastname);
			            st.setString(3, username);
			            st.setString(4, password);
			            st.setString(5, email);
			            st.executeUpdate();
			            JOptionPane.showMessageDialog(null, "inserted successfully");//message showing that the data has been inserted successfully
			            UserLogin fram = new UserLogin();
			            fram.loginframe.setVisible(true);
			            inputframe.setVisible(false);

			        } catch (SQLException ex) {
			            JOptionPane.showMessageDialog(null, ex);

			        } 
			}
		});
		btnSubmit.setBounds(71, 273, 117, 29);
		panel.add(btnSubmit);
	}
}
