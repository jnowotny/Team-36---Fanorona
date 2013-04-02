import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;


public class EndMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5569512002788537335L;
	private JPanel contentPane;
	private JLabel lblScoreLabel;
	private JLabel lblWinnerLabel;

	
	public EndMenu(final Fanorona f, final boolean timeOut) {
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
				f.dispose();
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
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblGameOver.setBounds(163, 19, 124, 29);
		contentPane.add(lblGameOver);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		
		lblScoreLabel = new JLabel();
		lblScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreLabel.setBounds(166, 78, 140, 16);
		lblScoreLabel.setVerifyInputWhenFocusTarget(false);
		final int curP1Score = f.board.getP1Score();
		final int curP2Score = f.board.getP2Score();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					lblScoreLabel.setText("Maroon: " + Integer.toString(curP2Score) + " White: " + Integer.toString(curP1Score));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel.setLayout(null);
		panel.add(lblScoreLabel);
		
		lblWinnerLabel = new JLabel();
		lblWinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (f.getNumRows() == 1 && f.getNumRows() == 1)
						lblWinnerLabel.setText("Draw!");
					else if (timeOut)
						lblWinnerLabel.setText("Player " + f.board.getBoardState().getNextPlayer() + " is the winnner!");
					else {
						if (curP1Score != 0 && curP2Score != 0)
							lblWinnerLabel.setText("Draw!");
						else if (curP1Score == 0)
							lblWinnerLabel.setText("Player 2 is the winner!");
						else if (curP2Score == 0)
							lblWinnerLabel.setText("Player 1 is the winner!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		lblWinnerLabel.setBounds(151, 50, 150, 16);
		panel.add(lblWinnerLabel);
		
	}
}
