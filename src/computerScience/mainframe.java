package computerScience;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;


public class mainframe extends JFrame {
	Connection conn;
    ResultSet set;
    Statement statement;
    PreparedStatement st;

	private JPanel contentPane;
	protected JLabel labelhello;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					mainframe frame = new mainframe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	


	/**
	 * Create the frame.
	 */
	public mainframe() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 648, 348);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel labeladdimage = new JLabel("");
		labeladdimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddExperiment vb = new AddExperiment();
				vb.frame.setVisible(true);
				new mainframe().dispose();
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/experiment copy.png")).getImage();
		labeladdimage.setIcon(new ImageIcon(img));
		labeladdimage.setBounds(61, 6, 122, 162);
		panel.add(labeladdimage);
		
		JLabel labelviewimage = new JLabel("");
		labelviewimage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTable tbl = new jTable();
				tbl.frame.setVisible(true);
				new mainframe().setVisible(false);
				
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/chemistry copy.png")).getImage();
		labelviewimage.setIcon(new ImageIcon(img1));
		labelviewimage.setBounds(273, 20, 116, 134);
		panel.add(labelviewimage);
		
		JLabel labeladdequip = new JLabel("");
		labeladdequip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Equipment eq = new Equipment();
				eq.frame.setVisible(true);
				new mainframe().dispose();
			}
		});
		Image img2 = new ImageIcon(this.getClass().getResource("/experiment (1) copy.png")).getImage();
		labeladdequip.setIcon(new ImageIcon(img2));
		labeladdequip.setBounds(464, 30, 122, 127);
		panel.add(labeladdequip);
		
		JLabel labelsetting = new JLabel("");
		labelsetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				settings set = new settings();
				set.frame.setVisible(true);
				new mainframe().dispose();
			}
		});
		Image img3 = new ImageIcon(this.getClass().getResource("/front-end copy 2.png")).getImage();
		labelsetting.setIcon(new ImageIcon(img3));
		labelsetting.setBounds(165, 203, 152, 98);
		panel.add(labelsetting);
		
		JLabel labelstats = new JLabel("");
		labelstats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Statistics stat = new Statistics();
				stat.frame.setVisible(true);
				new mainframe().dispose();
			}
		});
		Image img4 = new ImageIcon(this.getClass().getResource("/analytics copy.png")).getImage();
		labelstats.setIcon(new ImageIcon(img4));
		labelstats.setBounds(407, 196, 160, 105);
		panel.add(labelstats);
		
		JLabel lblNewLabel = new JLabel("Add Experiment");
		lblNewLabel.setBounds(68, 149, 172, 16);
		panel.add(lblNewLabel);
		
		JLabel lblViewExperiments = new JLabel("View Experiments");
		lblViewExperiments.setBounds(261, 149, 172, 16);
		panel.add(lblViewExperiments);
		
		JLabel lblAddEquipment = new JLabel("Add Equipment");
		lblAddEquipment.setBounds(464, 149, 172, 16);
		panel.add(lblAddEquipment);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setBounds(198, 302, 172, 16);
		panel.add(lblSettings);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setBounds(428, 302, 130, 16);
		panel.add(lblStatistics);
		
		JLabel labelhello = new JLabel("");
		labelhello.setBounds(487, 2, 149, 16);
		panel.add(labelhello);

	}

}
