package computerScience;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Subject extends DatabaseConnect {
	
    ResultSet set;
    Statement statement;
    PreparedStatement st;

	protected JFrame frame;
	protected JComboBox<String> subjectChoice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subject window = new Subject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Subject() {
		initialize();
		connectDatabase();


		
	}
	

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setBounds(0, 0, 450, 278);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Subject Area", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panel_1.setBounds(20, 17, 407, 242);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		final JComboBox<String> subjectChoice = new JComboBox<String>();
		try{
            conn = connectDatabase();//function to connect to the database
            //query from the database to select the column data of subjectName from the table SubjectArea
            statement = conn.createStatement();//preparing to execute the sql query
            String sql =  "SELECT SubjectName FROM Subject";
            set = statement.executeQuery(sql);//executing the query and retrieving the data from the database
            while(set.next()) {//while loop that is continuously running if there is a next data in the database
            	String course = set.getString(1);//getting the data from the database
            	subjectChoice.addItem(course);//adding the data from the database into the combobox
            }
        } catch (Exception ex) {
       JOptionPane.showMessageDialog(null, ex);
        }
		
		subjectChoice.setBounds(99, 143, 202, 27);
		panel_1.add(subjectChoice);
		
		final JTextField subjectText = new JTextField();
		subjectText.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				subjectText.setText("");
			}
		});
		subjectChoice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				subjectText.setText((String)subjectChoice.getSelectedItem());
			}
		});
		subjectText.setText("Add Subject Here");
		subjectText.setBounds(99, 58, 202, 26);
		panel_1.add(subjectText);
		subjectText.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        String insertSubject = subjectText.getText();//gets the text from the textfield
		       // subjectChoice.addItem(insertSubject); //adding the inputted subject into the jComboBox
		        
		         try{ 
		        	
		            String sql =  "INSERT INTO Subject (SubjectName) VALUES (?)";//inserting the subject into the database
		            connectDatabase();
		            st = conn.prepareStatement(sql);
		            st.setString(1, insertSubject);
		            st.executeUpdate();//executing the command from sql to form
		            JOptionPane.showMessageDialog(null, "inserted");
		    		try{
		    			subjectChoice.removeAllItems();
		                conn = connectDatabase();//function to connect to the database
		                //query from the database to select the column data of subjectName from the table SubjectArea
		                statement = conn.createStatement();//preparing to execute the sql query
		                String sql2 =  "SELECT SubjectName FROM Subject";
		                set = statement.executeQuery(sql2);//executing the query and retrieving the data from the database
		                while(set.next()) {//while loop that is continuously running if there is a next data in the database
		                	String course = set.getString(1);//getting the data from the database
		                	subjectChoice.addItem(course);//adding the data from the database into the combobox
		                }
		            } catch (Exception ex) {
		           JOptionPane.showMessageDialog(null, ex);
		            }
		            
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }

		        subjectText.setText("");

			}
		});
		btnNewButton.setBounds(23, 96, 117, 29);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try{ 
		            String sql =  "DELETE FROM Subject WHERE SubjectName='"+subjectChoice.getSelectedItem()+"'";
		            //deleting the subject from the database using the sql query for delete
		            connectDatabase();//function to connect to the database
		            st = conn.prepareStatement(sql);//preparing the query at 'sql' which is to insert the inputted subject into the database
		            st.executeUpdate();//executing the query and deleting the subject from the database
		            JOptionPane.showMessageDialog(null, "deleted successfully");//message to show when data has been deleted
		            subjectChoice.removeItem(subjectChoice.getSelectedItem());
		        } catch (SQLException | HeadlessException ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }  
		        
			}
		});
		btnNewButton_1.setBounds(146, 96, 117, 29);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Edit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try{ 
		            String sql =  "UPDATE Subject SET SubjectName='"+subjectText.getText()+"' WHERE SubjectName='"+subjectChoice.getSelectedItem()+"'";
		            //updating the subject from the database using the sql query for delete
		            connectDatabase();//function to connect to the database
		            st = conn.prepareStatement(sql);//preparing the query at 'sql' which is to insert the inputted subject into the database
		            st.executeUpdate();//executing the query and updating the subject on the database
		            JOptionPane.showMessageDialog(null, "updated successfully");//message to show when data has been updated
		    		try{
		    			subjectChoice.removeAllItems();
		                conn = connectDatabase();//function to connect to the database
		                //query from the database to select the column data of subjectName from the table SubjectArea
		                statement = conn.createStatement();//preparing to execute the sql query
		                String sql2 =  "SELECT SubjectName FROM Subject";
		                set = statement.executeQuery(sql2);//executing the query and retrieving the data from the database
		                while(set.next()) {//while loop that is continuously running if there is a next data in the database
		                	String course = set.getString(1);//getting the data from the database
		                	subjectChoice.addItem(course);//adding the data from the database into the combobox
		                }
		            } catch (Exception ex) {
		           JOptionPane.showMessageDialog(null, ex);
		            }
		        } catch (SQLException | HeadlessException x) {
		            JOptionPane.showMessageDialog(null, x);
		        }  
			}
		});
		btnNewButton_2.setBounds(268, 96, 117, 29);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

		        settings set = new settings();
		        set.frame.setVisible(true);
		        frame.dispose();
			}
		});
		btnNewButton_3.setBounds(99, 182, 188, 29);
		panel_1.add(btnNewButton_3);
	}
	


}
