package computerScience;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JList;

public class Procedure {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Procedure window = new Procedure();
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
	public Procedure() {
		initialize();
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
		panel.setBounds(0, 0, 450, 278);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel sketchbtn = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/sketch copy.png")).getImage();
		sketchbtn.setIcon(new ImageIcon(img));
		sketchbtn.setBounds(18, 19, 131, 99);
		panel.add(sketchbtn);
		
		JButton btnNewButton = new JButton("Browse Procedure Photo");
		btnNewButton.setBounds(161, 19, 171, 99);
		panel.add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(18, 263, 414, -117);
		panel.add(list);
	}
}
