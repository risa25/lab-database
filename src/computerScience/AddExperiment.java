package computerScience;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.BorderLayout;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;


public class AddExperiment extends DatabaseConnect {
    Connection conn;
    ResultSet set;
    Statement statement;
    PreparedStatement st;
    
    protected String labReport;
	protected JFrame frame;
	protected JTextField txtExperiment;
	public com.toedter.calendar.JDateChooser jDateChooser1;
	protected JTextField txtLabPartner;
	protected JTextField txtDesk;
	protected JLabel lblprocedure;
	protected JLabel lblNewLabel_4;
	protected JLabel lblresult;
	protected JLabel lblNewLabel_5;
	protected JLabel displayCourse;
	protected JCheckBox labcheck;
	protected JComboBox<String> comboBox;
	protected JComboBox<String> subjectbox;
	protected JTextField commentText;
	protected JTextArea textArea;
	protected JTextArea textArea_1;
	protected JTextArea textArea_2;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	protected JCheckBox labCheck;
	private JTextField expNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddExperiment window = new AddExperiment();
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
	public AddExperiment() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 496, 796);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 509, 796);
        frame.getContentPane().add(panel);
        panel.setBackground(UIManager.getColor("textHighlight"));
        panel.setLayout(null);
        
        JButton btnback = new JButton("Back");
        btnback.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        	}
        });
        btnback.setBounds(6, 761, 117, 29);
        panel.add(btnback);
        
        JButton btnNewButton = new JButton("Submit");
        btnNewButton.setBounds(373, 761, 117, 29);
        panel.add(btnNewButton);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new CompoundBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Input Experiment", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)), null)));
        panel_1.setBounds(16, 6, 456, 743);
        panel.add(panel_1);
        panel_1.setBackground(new Color(255, 255, 224));
        panel_1.setLayout(null);
        
        txtExperiment = new JTextField();
        txtExperiment.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		txtExperiment.setText("");
        	}
        });
        txtExperiment.setBounds(46, 25, 220, 26);
        panel_1.add(txtExperiment);
        txtExperiment.setText("Experiment Name");
        txtExperiment.setColumns(10);
        
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser1.setBounds(278, 25, 158, 26);
        panel_1.add(jDateChooser1);
        jDateChooser1.setBackground(new java.awt.Color(255, 255, 224));
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        
        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
        comboBox.setMaximumRowCount(10);
        comboBox.setBounds(137, 54, 64, 27);
        panel_1.add(comboBox);
        
        txtLabPartner = new JTextField();
        txtLabPartner.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		txtLabPartner.setText("");
        	}
        });
        txtLabPartner.setText("Lab Partner");
        txtLabPartner.setBounds(213, 53, 159, 26);
        panel_1.add(txtLabPartner);
        txtLabPartner.setColumns(10);
        
        txtDesk = new JTextField();
        txtDesk.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		txtDesk.setText("");
        	}
        });
        txtDesk.setText("Desk");
        txtDesk.setBounds(384, 53, 52, 26);
        panel_1.add(txtDesk);
        txtDesk.setColumns(10);
        
        lblNewLabel_1 = new JLabel("Abstract");
        lblNewLabel_1.setBounds(18, 101, 61, 16);
        panel_1.add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("Introduction");
        lblNewLabel_2.setBounds(18, 267, 94, 16);
        panel_1.add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("Discussion");
        lblNewLabel_3.setBounds(18, 430, 94, 16);
        panel_1.add(lblNewLabel_3);
        
        lblprocedure = new JLabel("");
        lblprocedure.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Procedure pr = new Procedure();
        		pr.frame.setVisible(true);
        	}
        });
		Image img = new ImageIcon(this.getClass().getResource("/planning copy.png")).getImage();
		lblprocedure.setIcon(new ImageIcon(img));
        lblprocedure.setBounds(28, 578, 52, 74);
        panel_1.add(lblprocedure);
        
        lblNewLabel_4 = new JLabel("Procedures");
        lblNewLabel_4.setBounds(18, 649, 94, 16);
        panel_1.add(lblNewLabel_4);
        
        lblresult = new JLabel("");
        lblresult.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Result kl = new Result();
        		kl.frame.setVisible(true);
        	}
        });
		Image img2 = new ImageIcon(this.getClass().getResource("/stadistics copy.png")).getImage();
		lblresult.setIcon(new ImageIcon(img2));
        lblresult.setBounds(140, 590, 61, 56);
        panel_1.add(lblresult);
        
        lblNewLabel_5 = new JLabel("Results");
        lblNewLabel_5.setBounds(150, 649, 70, 16);
        panel_1.add(lblNewLabel_5);
        
        JCheckBox labcheck = new JCheckBox("Lab Report Completed");
        if(labcheck.isEnabled()) {
        	labReport = "Yes";
        }else {
        	labReport = "No";
        }
        labcheck.setBounds(248, 617, 172, 23);
        panel_1.add(labcheck);
        
        subjectbox = new JComboBox<String>();
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/ComputerScience","root","root"); ;//function to connect to the database
            //query from the database to select the column data of subjectName from the table SubjectArea
            statement = conn.createStatement();//preparing to execute the sql query
            String sql2 =  "SELECT SubjectName FROM Subject";
            set = statement.executeQuery(sql2);//executing the query and retrieving the data from the database
            while(set.next()) {//while loop that is continuously running if there is a next data in the database
            	String course = set.getString(1);//getting the data from the database
            	subjectbox.addItem(course);//adding the data from the database into the combobox
            }
        } catch (Exception ex) {
       JOptionPane.showMessageDialog(null, ex);
        }
        subjectbox.setBounds(6, 54, 129, 27);
        panel_1.add(subjectbox);
        
        commentText = new JTextField();
        commentText.setBounds(18, 698, 418, 26);
        panel_1.add(commentText);
        commentText.setColumns(10);
        
        JLabel lblNewLabel_3_1 = new JLabel("Comments");
        lblNewLabel_3_1.setBounds(18, 684, 94, 16);
        panel_1.add(lblNewLabel_3_1);
        
        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setBounds(18, 119, 418, 133);
        panel_1.add(textArea);
        
        final JTextArea textArea_1 = new JTextArea();
        textArea_1.setLineWrap(true);
        textArea_1.setBounds(18, 285, 418, 133);
        panel_1.add(textArea_1);
        
        final JTextArea textArea_2_1 = new JTextArea();
        textArea_2_1.setLineWrap(true);
        textArea_2_1.setBounds(18, 445, 418, 133);
        panel_1.add(textArea_2_1);
        
        expNumber = new JTextField();
        expNumber.setBounds(6, 25, 37, 26);
        panel_1.add(expNumber);
        expNumber.setColumns(10);
        
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        			String num = expNumber.getText();
        	       String name = txtExperiment.getText();
        	       String date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        	        String subjectArea1 = (String) subjectbox.getSelectedItem();
        	        String sectionNo = (String) comboBox.getSelectedItem();
        	        String labPartner = txtLabPartner.getText();
        	        String deskNo = txtDesk.getText();
        	        String absText = textArea.getText();
        	        String intro = textArea_1.getText();
        	        String discussion = textArea_2_1.getText();
        	        String checkLab = labReport;
        	        String comment = commentText.getText();
        	       

        	        try{
        	            String sql =  "INSERT INTO InputLab ("+
        	            "	ExperimentNo, ExperimentName, Date, SubjectArea, SectionNo, LabPartner, DeskNo, absText, Intro, Discussion, checkLab, Comment)" +
        	            "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        	            //inserting the values that are inputed within this form into the database using the sql language
        	            connectDatabase(); //using the function to connect to the database
        	            st=conn.prepareStatement(sql);
        	            //the preparestatement function is used to execute the query so in this case it will be to insert the information into the lab
        	            st.setString(1, num);
        	            st.setString(2, name);
        	            st.setString(3, date);
        	            st.setString(4, subjectArea1);
        	            st.setString(5, sectionNo);
        	            st.setString(6, labPartner);
        	            st.setString(7, deskNo);
        	            st.setString(8, absText);
        	            st.setString(9, intro);
        	            st.setString(10, discussion);
        	            st.setString(11, checkLab);
        	            st.setString(12, comment);//setting the values to the correct column within the database table
        	            st.executeUpdate();
        	            JOptionPane.showMessageDialog(null, "inserted successfully");//message showing that the data has been inserted successfully
        	            new mainframe().setVisible(true);
        	            frame.dispose();//close the form without exiting the program
        	             //display the main menu page which has the jTable

        	        } catch (SQLException ex) {
        	            JOptionPane.showMessageDialog(null, ex);

        	        } 
        	}
        });
	}
}
