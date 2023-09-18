package computerScience;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class StatisticsShow extends DatabaseConnect{
	
    ResultSet set;
    Statement statement;
    PreparedStatement st;

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsShow window = new StatisticsShow();
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
	public StatisticsShow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 571, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 248, 220));
		panel_1.setBounds(0, 0, 571, 379);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(250, 250, 210));
		tabbedPane.setBounds(21, 33, 525, 328);
		panel_1.add(tabbedPane);
		
		JPanel SubjectArea = new JPanel();
		SubjectArea.setBackground(new Color(240, 255, 240));
		tabbedPane.addTab("Subject Area", null, SubjectArea, null);
		SubjectArea.setLayout(null);
		
		JLabel lblResult = new JLabel("");
		lblResult.setBounds(235, 121, 218, 16);
		SubjectArea.add(lblResult);
		
		JComboBox comboBox = new JComboBox();
        try{
            conn = connectDatabase();//function to connect to the database
            //query from the database to select the column data of subjectName from the table SubjectArea
            statement = conn.createStatement();//preparing to execute the sql query
            String sql2 =  "SELECT SubjectName FROM Subject";
            set = statement.executeQuery(sql2);//executing the query and retrieving the data from the database
            while(set.next()) {//while loop that is continuously running if there is a next data in the database
            	String course = set.getString(1);//getting the data from the database
            	comboBox.addItem(course);//adding the data from the database into the combobox
            }
        } catch (Exception ex) {
       JOptionPane.showMessageDialog(null, ex);
        }
        //number of experiments done within a subject area
            try{
                conn = connectDatabase();//function to connect to the database
                //query from the database to select the column data of subjectName from the table SubjectArea
                statement = conn.createStatement();//preparing to execute the sql query
                String sql2 =  "SELECT COUNT(ExperimentName) FROM InputLab WHERE SubjectArea='"+(String)comboBox.getSelectedItem()+"'";
                set = statement.executeQuery(sql2);//executing the query and retrieving the data from the database
                while(set.next()) {
                	int ans = set.getInt(1);
                	lblResult.setText(String.valueOf(ans));	
                }
                
            } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
            }

            
        
		comboBox.setBounds(40, 57, 212, 27);
		SubjectArea.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("View Labs");
		btnNewButton_1.setBounds(40, 201, 117, 29);
		SubjectArea.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Number of Experiments Done:");
		lblNewLabel.setBounds(40, 116, 199, 27);
		SubjectArea.add(lblNewLabel);
		
		
		JPanel partners = new JPanel();
		partners.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Partners", null, partners, null);
		
		JPanel CompletedLabs = new JPanel();
		CompletedLabs.setBackground(new Color(224, 255, 255));
		tabbedPane.addTab("Completed Labs", null, CompletedLabs, null);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistics stat = new Statistics();
				stat.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(6, 6, 117, 29);
		panel_1.add(btnNewButton);
	}
}
