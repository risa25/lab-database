package computerScience;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.proteanit.sql.DbUtils;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class jTable extends DatabaseConnect{
    ResultSet set;
    Statement statement;
    PreparedStatement st;

	protected JFrame frame;
	private JTextField textField;
	protected JTable jtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jTable window = new jTable();
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
	public jTable() {
		initialize();
		connectDatabase();
	}
	
	public void displayTable() {
		DefaultTableModel dtm = new DefaultTableModel();
        try{
            conn = connectDatabase();//firstly making the connection to the database
            statement = conn.createStatement();
            String sql =  "SELECT * FROM InputLab";
            //this is the sql query for selecting these areas into the jtable
            set = statement.executeQuery(sql);
            while(set.next()) {
            	dtm.addRow(new Object[] {
            			set.getString("ExperimentNo"),
            			set.getString("ExperimentName"),
            			set.getString("Date"),
            			set.getString("SubjectArea"),
            			set.getString("SectionNo"),
            			set.getString("LabPartner"),
            			set.getString("DeskNo"),
            			set.getString("checkLab")
            	});
            }

        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
	}
	


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 863, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Object[] columns = {"ExperimentNo", "ExperimentName", "Date", "SubjectArea", "SectionNo", "LabPartner", "DeskNo", "checkLab", "Comment"};
		final DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columns);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 863, 514);
		panel.setBackground(new Color(255, 240, 245));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JTable jtable = new JTable();
		try{
            conn = connectDatabase();//firstly making the connection to the database
            statement = conn.createStatement();
            String sql =  "SELECT * FROM InputLab";
            //this is the sql query for selecting these areas into the jtable
            set = statement.executeQuery(sql);
            while(set.next()) {
            	dtm.addRow(new Object[] {
            			set.getString("ExperimentNo"),
            			set.getString("ExperimentName"),
            			set.getString("Date"),
            			set.getString("SubjectArea"),
            			set.getString("SectionNo"),
            			set.getString("LabPartner"),
            			set.getString("DeskNo"),
            			set.getString("checkLab"),
            			set.getString("Comment")
            	});
            }

        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
		jtable.setModel(dtm);
		jtable.setBackground(Color.white);
		jtable.setForeground(null);
		jtable.setSelectionBackground(Color.blue);
		jtable.setGridColor(Color.blue);
		jtable.setSelectionForeground(Color.white);
		//jtable.setRowHeight(30);
		jtable.setAutoCreateRowSorter(true);
		
		
		JScrollPane pane = new JScrollPane(jtable);
		pane.setBounds(6, 58, 851, 404);
		panel.add(pane);
		pane.setBackground(Color.white);
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Experiment Name", "Course", "Section No.", "Lab Partner", "Desk No."}));
		comboBox.setBounds(139, 21, 167, 27);
		panel.add(comboBox);
		
		textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
				    
			        String ft = (String) comboBox.getSelectedItem();
			        String tx = textField.getText();
			        switch (ft) {
			            case "Experiment Name":
			                         
			                            try{
			                                connectDatabase();
			                                String sql2 = "SELECT ExperimentNo, ExperimentName, Date, SubjectArea, SectionNo, LabPartner, DeskNo," +
			                                               " checkLab, Comment FROM InputLab WHERE ExperimentName = '" + tx+ "'";
			                                st=conn.prepareStatement(sql2);
			                                set=st.executeQuery();
			                                jtable.setModel(DbUtils.resultSetToTableModel(set));
			 
			                            } catch (Exception ex) {
			                                   JOptionPane.showMessageDialog(null, e);

			                                                 }
			                            break;
			                             

			            case "Course":
			                            try{
			                                connectDatabase();
			                                String sql4 = "SELECT ExperimentNo, ExperimentName, Date, SubjectArea, SectionNo, LabPartner, DeskNo," +
			                                               " checkLab, Comment FROM InputLab WHERE SubjectArea = '" + tx+ "'";
			                                st=conn.prepareStatement(sql4);
			                                set=st.executeQuery();
			                                jtable.setModel(DbUtils.resultSetToTableModel(set));
			 
			                            } catch (Exception ec) {
			                                   JOptionPane.showMessageDialog(null, e);

			                                                 }
			                            break;
			                            
			            case "Section No.":
			                            try{
			                                connectDatabase();
			                                String sql5 = "SELECT ExperimentNo, ExperimentName, Date, SubjectArea, SectionNo, LabPartner, DeskNo," +
			                                               " checkLab, Comment FROM InputLab WHERE SectionNo = '" + tx+ "'";
			                                st=conn.prepareStatement(sql5);
			                                set=st.executeQuery();
			                                jtable.setModel(DbUtils.resultSetToTableModel(set));
			 
			                            } catch (Exception ec) {
			                                   JOptionPane.showMessageDialog(null, e);

			                                                 }
			                            break;
			                            
			            case "Lab Partner":
			                            try{
			                                connectDatabase();
			                                String sql6 = "SELECT ExperimentNo, ExperimentName, Date, SubjectArea, SectionNo, LabPartner, DeskNo," +
			                                               " checkLab, Comment FROM InputLab WHERE LabPartner = '" + tx+ "'";
			                                st=conn.prepareStatement(sql6);
			                                set=st.executeQuery();
			                                jtable.setModel(DbUtils.resultSetToTableModel(set));
			 
			                            } catch (Exception ec) {
			                                   JOptionPane.showMessageDialog(null, e);

			                                                 }
			                            break;
			                            
			            case "Desk No.":
			                            try{
			                                connectDatabase();
			                                String sql6 = "SELECT ExperimentNo, ExperimentName, Date, SubjectArea, SectionNo, LabPartner, DeskNo," +
			                                               " checkLab, Comment FROM InputLab WHERE DeskNo = '" + tx+ "'";
			                                st=conn.prepareStatement(sql6);
			                                set=st.executeQuery();
			                                jtable.setModel(DbUtils.resultSetToTableModel(set));
			 
			                            } catch (Exception ex) {
			                                   JOptionPane.showMessageDialog(null, e);

			                                                 }
			                            break;                            
			                            
			                         
			               
			                 
			        }
			        

				}
			});
		

		textField.setBounds(318, 20, 199, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnback = new JButton("Back to Menu");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainframe().setVisible(true);
				frame.dispose();
			}
		});
		btnback.setBounds(6, 474, 117, 34);
		panel.add(btnback);
		
		JButton btnEdit = new JButton("Edit Data");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = jtable.getSelectedRow();
				String cell = jtable.getModel().getValueAt(row, 0).toString();
				
		      /*(  try{ 
		            String sql =  "UPDATE InputLab SET SubjectName='"+subjectText.getText()+"' WHERE SubjectName='"+subjectChoice.getSelectedItem()+"'";
		            //updatig the subject from the database using the sql query for delete
		            connectDatabase();//function to connect to the database
		            st = conn.prepareStatement(sql);//preparing the query at 'sql' which is to insert the inputted subject into the database
		            st.executeUpdate();//executing the query and updating the subject on the database
		            JOptionPane.showMessageDialog(null, "updated successfully");//message to show when data has been updated
		            displayCourse();
		        } catch (SQLException | HeadlessException e) {
		            JOptionPane.showMessageDialog(null, e);
		        }  */
			}
		});
		
		btnEdit.setBounds(491, 474, 117, 34);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete Data");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int row = jtable.getSelectedRow();
		        String cell = jtable.getModel().getValueAt(row,0).toString();   
		        String sql =  "DELETE FROM InputLab WHERE ExperimentNo =" + cell;
		        
		        try{
		            st = conn.prepareStatement(sql);
		            st.executeUpdate();
		            JOptionPane.showMessageDialog(null, "deleted successfully");

		        } catch (SQLException | HeadlessException ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});

		btnDelete.setBounds(609, 474, 117, 34);
		panel.add(btnDelete);
		
		JButton btnRefresh = new JButton("Refresh Table");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dtm.setRowCount(0);
					try{
			            conn = connectDatabase();//firstly making the connection to the database
			            statement = conn.createStatement();
			            String sql =  "SELECT * FROM InputLab";
			            //this is the sql query for selecting these areas into the jtable
			            set = statement.executeQuery(sql);
			            while(set.next()) {
			            	dtm.addRow(new Object[] {
			            			set.getString("ExperimentNo"),
			            			set.getString("ExperimentName"),
			            			set.getString("Date"),
			            			set.getString("SubjectArea"),
			            			set.getString("SectionNo"),
			            			set.getString("LabPartner"),
			            			set.getString("DeskNo"),
			            			set.getString("checkLab"),
			            			set.getString("Comment")
			            	});
			            }

			        } catch (Exception ex) {
			           JOptionPane.showMessageDialog(null, ex);
			        }

					
				}

			
		});
		
		btnRefresh.setBounds(232, 474, 117, 34);
		panel.add(btnRefresh);
		
		Object[] row = new Object[8];
		
		

		
	}


	}

