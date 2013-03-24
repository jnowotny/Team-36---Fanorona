import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EndMenu extends JFrame {

	private JPanel contentPane;
	private static Fanorona fan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndMenu frame = new EndMenu(fan);
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
	
	public EndMenu(Fanorona f) {
		fan = f;
		setTitle("End Game Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fan.dispose();
				NewGameWin win = new NewGameWin();
				win.setVisible(true);
				dispose();
			}
		});
		btnNewGame.setBounds(163, 98, 124, 29);
		contentPane.add(btnNewGame);
		
		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_Menu win = new Welcome_Menu();
				win.setVisible(true);
				dispose();
			}
		});
		btnMainMenu.setBounds(163, 139, 124, 29);
		contentPane.add(btnMainMenu);
		
		JButton btnNewButton = new JButton("Exit Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(163, 180, 124, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblGameOver = new JLabel("Game Over!");
		lblGameOver.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblGameOver.setBounds(163, 19, 124, 29);
		contentPane.add(lblGameOver);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(193, 60, 61, 16);
		contentPane.add(lblNewLabel);
	}
}
