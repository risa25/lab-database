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

public class settings {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					settings window = new settings();
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
	public settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 260, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 260, 394);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel labelsubj = new JLabel("");
		labelsubj.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Subject sub = new Subject();
				sub.frame.setVisible(true);
				frame.dispose();
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/atom copy.png")).getImage();
		labelsubj.setIcon(new ImageIcon(img));
		labelsubj.setBounds(66, 23, 138, 135);
		panel.add(labelsubj);
		
		JLabel labelSemester = new JLabel("");
		Image img2 = new ImageIcon(this.getClass().getResource("/calendar copy.png")).getImage();
		labelSemester.setIcon(new ImageIcon(img2));
		labelSemester.setBounds(66, 199, 152, 135);
		panel.add(labelSemester);
		
		JLabel lblNewLabel = new JLabel("Edit Subjects");
		lblNewLabel.setBounds(76, 156, 89, 16);
		panel.add(lblNewLabel);
		
		JLabel lblEditSemester = new JLabel("Edit Semester");
		lblEditSemester.setBounds(76, 332, 89, 16);
		panel.add(lblEditSemester);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new mainframe().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(137, 359, 117, 29);
		panel.add(btnNewButton);
	}

}
