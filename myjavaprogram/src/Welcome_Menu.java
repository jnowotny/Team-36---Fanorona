import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Welcome_Menu extends JFrame {

	private JPanel contentPane;
	private final JButton btnRules = new JButton("Rules");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome_Menu frame = new Welcome_Menu();
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
	public Welcome_Menu() {
		setTitle("Fanorona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnStartANew = new JButton("Start a New Game");
		btnStartANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGameWin win = new NewGameWin();
				win.setVisible(true);
				dispose();
			}
		});
		btnStartANew.setBounds(6, 140, 440, 29);
		contentPane.add(btnStartANew);
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RulesWindow win = new RulesWindow();
				win.makeVisible();
			}
		});
		btnRules.setBounds(6, 174, 440, 29);
		contentPane.add(btnRules);
		
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutWin win = new AboutWin();
				win.setVisible(true);
			}
		});
		btnAbout.setBounds(6, 209, 440, 29);
		contentPane.add(btnAbout);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(6, 243, 440, 29);
		contentPane.add(btnQuit);
		
		JTextPane txtpnFanorona = new JTextPane();
		txtpnFanorona.setToolTipText("");
		txtpnFanorona.setFont(new Font("Lucida Grande", Font.BOLD, 44));
		txtpnFanorona.setText("Fanorona!");
		txtpnFanorona.setEditable(false);
		txtpnFanorona.setBounds(6, 6, 438, 124);
		contentPane.add(txtpnFanorona);
	}
}