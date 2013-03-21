import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Fanorona extends JFrame {

	private static final long serialVersionUID = 3335293785778663915L;
	private JPanel contentPane;
	private Timer countdown;
	private Label label;
	private int timeRemaining = 25;
	private PauseMenu pause = new PauseMenu();
	private SwitchPlayers swap = new SwitchPlayers();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fanorona frame = new Fanorona();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//creates the handler for updating the timer!
	class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	if (!pause.isVisible() && !swap.isVisible()) {
        		if (--timeRemaining > 0) 
	                label.setText("Time Remaining: " + String.valueOf(timeRemaining));
	            else {
	                label.setText("Time's up!");
	                swap.setVisible(true);
	                timeRemaining = 26;
	            }
        	}
        }
    }
	
	/**
	 * Create the frame.
	 */
	public Fanorona() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 800, 600);
		setTitle("Fanorona");
		

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Board board = new Board();
		contentPane.add(board);

		Button btnMainMenu = new Button("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_Menu win = new Welcome_Menu();
				win.setVisible(true);
				dispose();
			}
		});
		board.add(btnMainMenu);
		
		label = new Label("Time Remaining: " + String.valueOf(timeRemaining));
		board.add(label);
		
		Button btnPause = new Button("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stop clock and pause game!
				pause.makeVisible();
			}
		});
		board.add(btnPause);
		
		countdown = new Timer(1000, new CountdownTimerListener());
		//countdown.addWindowListener(new isPauseMenuOpen());
		setVisible(true);
		countdown.start();
		
		swap.setVisible(false);
		add(swap);
		
	}

}