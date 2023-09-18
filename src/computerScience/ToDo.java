package computerScience;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ToDo extends DatabaseConnect{
	
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
					ToDo window = new ToDo();
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
	public ToDo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 863, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		Object[] columns = {"ExperimentNo", "ExperimentName", "Date", "SubjectArea", "SectionNo", "LabPartner", "DeskNo", "Comment"};
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(columns);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 863, 514);
		panel.setBackground(new Color(255, 240, 245));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTable jtable = new JTable();
		try{
            conn = connectDatabase();//firstly making the connection to the database
            statement = conn.createStatement();
            String sql =  "SELECT * FROM InputLab WHERE checkLab='No'";
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
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(6, 18, 117, 29);
		panel.add(btnNewButton);
	}
}
