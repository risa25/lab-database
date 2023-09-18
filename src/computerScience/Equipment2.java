package computerScience;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Equipment2 extends DatabaseConnect {


	ResultSet set;
    Statement statement;
    PreparedStatement st;

	protected JFrame frame;
	private JTextField txtName;
	protected JTable jtable = new JTable();
	protected JLabel label;

	DefaultTableModel ml = new DefaultTableModel();
	String name = "";
	String selectedImagePath = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Equipment2 window = new Equipment2();
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
	public Equipment2() {
		initialize();
		tableHeader();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void fillDBTable() {
		try{
            conn = connectDatabase();//firstly making the connection to the database
            statement = conn.createStatement();
            String sql =  "SELECT * FROM ExperimentEquipment";
            //this is the sql query for selecting these areas into the jtable
            set = statement.executeQuery(sql);
            while(set.next()) {
            	ml.addRow(new Object[] {
            			set.getString("Equipment"),
            			set.getBytes("Image")
            			
            	});
            	
            }

        } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex);
        }
	}
	
	public void tableHeader() {
		ml = (DefaultTableModel) jtable.getModel();
		Object[] columnHead = new Object[] {"Experiment Name", "Photo"};
		ml.setColumnIdentifiers(columnHead);
		
	//	jtable.getColumn("Photo").setCellRenderer(new myCellRenderer());
	}


	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 427, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tableHeader();
		frame.getContentPane().setLayout(null);

		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 427, 516);
		panel.setBackground(new Color(255, 240, 245));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JTable jtable = new JTable();
		fillDBTable();
		jtable.setModel(ml);
		jtable.setBackground(Color.white);
		jtable.setForeground(null);
		jtable.setSelectionBackground(Color.blue);
		jtable.setGridColor(Color.blue);
		jtable.setSelectionForeground(Color.white);
		//jtable.setRowHeight(30);
		jtable.setAutoCreateRowSorter(true);
		
		JScrollPane pane = new JScrollPane(jtable);
		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = jtable.getSelectedRow();
				txtName.setText(ml.getValueAt(selectedRow, 0).toString());
			}
		});
		pane.setBounds(6, 208, 415, 272);
		panel.add(pane);
		pane.setBackground(Color.white);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainframe().setVisible(true);
			}
		});
		btnNewButton.setBounds(6, 481, 117, 29);
		panel.add(btnNewButton);
		
		txtName = new JTextField();
		txtName.setText("Name");
		txtName.setBounds(6, 16, 130, 26);
		panel.add(txtName);
		txtName.setColumns(10);
		
		final JLabel labelimg = new JLabel("");
		labelimg.setBounds(16, 78, 124, 118);
		panel.add(labelimg);
		
		JButton btnBrowse = new JButton("Browse Photo ://");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser browseImg = new JFileChooser();
				browseImg.showOpenDialog(null);
				File file = browseImg.getSelectedFile();
				String fname = file.getAbsolutePath();
				ImageIcon imag = new ImageIcon(new ImageIcon(fname).getImage().getScaledInstance(labelimg.getWidth(), labelimg.getHeight(), Image.SCALE_SMOOTH));
				labelimg.setIcon(imag);
				
				
				FileNameExtensionFilter ff = new FileNameExtensionFilter("IMAGE", "png", "jpg", "jpeg");
				browseImg.addChoosableFileFilter(ff);
				
				try {
					File img = new File(fname);
					FileInputStream fis = new FileInputStream(img);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					byte[] num = new byte[1024];
					for(int readNum; (readNum = fis.read(num))!=-1;) {
						baos.write(num, 0, readNum);
						
					}
					byte[] imageperson = baos.toByteArray();
					
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
					
				}
				
			
		});
		btnBrowse.setBounds(6, 49, 179, 29);
		panel.add(btnBrowse);
		
		JButton btnNewButton_2 = new JButton("Add Equipment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = txtName.getText();
				
				JLabel photoTable = new JLabel();
				ImageIcon img = new ImageIcon(selectedImagePath);
				Image mg = img.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				photoTable.setIcon(new ImageIcon(mg));
				
				
				if (name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Field Empty");
				} else {
					ml.addRow(new Object[] {name, photoTable});
					JOptionPane.showMessageDialog(null, "Data Inserted");
					clearField();
				}
				

			}

			private void clearField() {
				// TODO Auto-generated method stub
				txtName.setText("");
			}
		});
		btnNewButton_2.setBounds(281, 123, 140, 73);
		panel.add(btnNewButton_2);
		

	}



	
}



