package computerScience;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Statistics {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics window = new Statistics();
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
	public Statistics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 542, 382);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel labltodo = new JLabel("");
		labltodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ToDo td = new ToDo();
				td.frame.setVisible(true);
			}
		});
		labltodo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				ToDo td = new ToDo();
				td.frame.setVisible(true);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/todolist copy.png")).getImage();
		labltodo.setIcon(new ImageIcon(img));
		labltodo.setBounds(65, 101, 139, 132);
		panel.add(labltodo);
		
		JLabel labelnumber = new JLabel("");
		labelnumber.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StatisticsShow ss = new StatisticsShow();
				ss.frame.setVisible(true);
				frame.dispose();
			}
		});
		Image img2 = new ImageIcon(this.getClass().getResource("/graph copy.png")).getImage();
		labelnumber.setIcon(new ImageIcon(img2));
		labelnumber.setBounds(325, 86, 106, 156);
		panel.add(labelnumber);
		
		JLabel lblNewLabel = new JLabel("To Do List");
		lblNewLabel.setBounds(90, 245, 92, 16);
		panel.add(lblNewLabel);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setBounds(335, 245, 92, 16);
		panel.add(lblStatistics);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainframe().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(193, 323, 117, 29);
		panel.add(btnNewButton);
	}
}
