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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Equipment extends DatabaseConnect {
	
	String selectedAbsoluteImagePath = "";
	String selectedImageName = "";


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
					Equipment window = new Equipment();
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
	public Equipment() {
		initialize();
		tableHeader();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void insertData() throws FileNotFoundException {
		String equiName= txtName.getText();
		InputStream imgIns = null;
		
        try{
        	imgIns = new FileInputStream(new File(selectedAbsoluteImagePath));
            String sql =  "INSERT INTO ExperimentEquipment (Equipment, Image) VALUES (?, ?);";
            //inserting the values that are inputed within this form into the database using the sql language
            connectDatabase(); //using the function to connect to the database
            st=conn.prepareStatement(sql);
            //the preparestatement function is used to execute the query so in this case it will be to insert the information into the lab
            st.setString(1, equiName);
            st.setBlob(2, imgIns);
//setting the values to the correct column within the database table
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "inserted successfully");//message showing that the data has been inserted successfully
            clearField();
            tableFill();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } 
	}
	
	private void clearField() {
		// TODO Auto-generated method stub
		txtName.setText("");

	}
	
	public ArrayList<EquipmentStuff> list() {
		ArrayList<EquipmentStuff> list = new ArrayList<EquipmentStuff>();
		EquipmentStuff equip;

		try {
			conn = connectDatabase();
			statement = conn.createStatement();
			String sql =  "SELECT * FROM ExperimentEquipment;";
			set = statement.executeQuery(sql);
			while(set.next()) {
				equip = new EquipmentStuff();
				equip.setName(set.getString("Equipment"));
				equip.setImage(set.getBytes("Image"));
				list.add(equip);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void tableFill() {
		ArrayList<EquipmentStuff> fillTable = list();
		Object[] obj = new Object[1];
		ml = (DefaultTableModel) jtable.getModel();
		ml.setRowCount(0);
		
		for(int i = 0; i < fillTable.size(); i++) {
			obj[0] = fillTable.get(i).getName();
			//obj[1] = fillTable.get(i).getTbImage();
			ml.addRow(obj);
		}
	}
	
	public void tableHeader() {
		ml = (DefaultTableModel) jtable.getModel();
		Object[] columnHead = new Object[] {"Experiment Name"};
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
		
		final JTable jtable = new JTable() {
				public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; //Disallow the editing of any cell
				}
		};
		tableFill();
		jtable.setModel(ml);
		jtable.setBackground(Color.white);
		jtable.setForeground(null);
		jtable.setSelectionBackground(Color.blue);
		jtable.setGridColor(Color.blue);
		jtable.setSelectionForeground(Color.white);
		//jtable.setRowHeight(30);
		jtable.setAutoCreateRowSorter(true);
		
		JScrollPane pane = new JScrollPane(jtable);


		pane.setBounds(6, 208, 415, 272);
		panel.add(pane);
		pane.setBackground(Color.white);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainframe().setVisible(true);
				frame.dispose();
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
		labelimg.setBounds(16, 78, 205, 125);
		panel.add(labelimg);
		
		jtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final int selectedRow = jtable.getSelectedRow();
				txtName.setText(list().get(selectedRow).getName());
				
				
				ImageIcon imag= new ImageIcon((list().get(selectedRow).getTbImage()));
				Image image = imag.getImage().getScaledInstance(labelimg.getWidth(), labelimg.getHeight(), Image.SCALE_SMOOTH);
				labelimg.setIcon((Icon) image.getScaledInstance(labelimg.getWidth(), labelimg.getHeight(), Image.SCALE_SMOOTH));
				
				
			}
		});
		
		JButton btnBrowse = new JButton("Browse Photo ://");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser browseImg = new JFileChooser();
				
				FileNameExtensionFilter ff = new FileNameExtensionFilter("IMAGE", "png", "jpg", "jpeg");
				browseImg.addChoosableFileFilter(ff);
				int showOpenDialog = browseImg.showOpenDialog(null);
				if(showOpenDialog == JFileChooser.APPROVE_OPTION) {
					File selectedImageFile = browseImg.getSelectedFile();
					selectedAbsoluteImagePath = selectedImageFile.getAbsolutePath();
					selectedImageName = selectedImageFile.getName();
					JOptionPane.showMessageDialog(null, selectedAbsoluteImagePath);
					
					ImageIcon imag= new ImageIcon(selectedAbsoluteImagePath);
					Image image = imag.getImage().getScaledInstance(labelimg.getWidth(), labelimg.getHeight(), Image.SCALE_SMOOTH);
					labelimg.setIcon(new ImageIcon(image));
					
				}
				
			}
		});
		btnBrowse.setBounds(6, 49, 179, 29);
		panel.add(btnBrowse);
		
		JButton btnNewButton_2 = new JButton("Add Equipment");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					insertData();
					labelimg.setIcon(null);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				/*name = txtName.getText();
				
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
				}*/
				

			}


		});
		btnNewButton_2.setBounds(281, 123, 140, 73);
		panel.add(btnNewButton_2);
		

	}



	
}



