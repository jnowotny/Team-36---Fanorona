import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class Fanorona extends JFrame {

	private static final long serialVersionUID = 3335293785778663915L;
	private JPanel contentPane;
	private Timer countdown;
	private Timer gameLoop;
	private Label label;
	private Label label_1;
	private JLabel p1Score;
	private JLabel p2Score;
	private int timeRemaining = 25;
	private int curP1Score;
	private int curP2Score;
	protected Board board = new Board();
	private PauseMenu pause = new PauseMenu(this);
	private SwitchPlayers swap = new SwitchPlayers(this);
	private EndMenu endMenu = new EndMenu(this);

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
        	if ((!pause.isVisible()) && (!swap.isVisible()) && (!endMenu.isVisible())) {
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
	
	//creates the handler for updating the timer!
	class GameLoopListener implements ActionListener {
		private Fanorona fan;
        public GameLoopListener(Fanorona f) {
        	fan = f;
        }

		public void actionPerformed(ActionEvent e) {
        	final int maxTurn = 50;
        	
    		curP1Score = fan.board.getP1Score();
    		curP2Score = fan.board.getP2Score();
    		
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					p2Score.setText("Maroon: " + Integer.toString(curP2Score)); 
    					p1Score.setText("White: " + Integer.toString(curP1Score));
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
        	
        	//Any other Looping actions we need done here
        	if ((fan.board.getP1Score() == 0) || (fan.board.getP2Score() == 0)) {
        		endMenu.setVisible(true);
        		dispose();
        	}
        	if (fan.board.getTurnCount() == maxTurn) {
        		endMenu.setVisible(true);
        		dispose();
        	}
        }
    }
	
	/**
	 * Create the frame.
	 */
	public Fanorona() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = 800;
	    int height = 600;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screen.width - width) / 2;
	    int y = (screen.height - height) / 2;
	    setBounds(x, y, width, height);
		setTitle("Fanorona");
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(board);

		
		Button btnMainMenu = new Button("Main Menu");
		btnMainMenu.setBounds(311, 23, 112, 29);
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_Menu win = new Welcome_Menu();
				win.setVisible(true);
				dispose();
			}
		});
		board.setLayout(null);
		board.add(btnMainMenu);
		
		p1Score = new JLabel();
		p1Score.setBackground(Color.LIGHT_GRAY);
		int p1ScoreX = ((width/4)-(64/2));
		p1Score.setBounds(p1ScoreX, 428, 64, 14);
		board.add(p1Score);
		
		p2Score = new JLabel();
		p2Score.setBackground(Color.LIGHT_GRAY);
		int p2ScoreX = (3*(width/4)-(64/2));
		p2Score.setBounds(p2ScoreX, 428, 64, 14);
		board.add(p2Score);
		
		
		label = new Label("Time Remaining: " + String.valueOf(timeRemaining));
		label.setBackground(Color.LIGHT_GRAY);
		label.setBounds(428, 29, 130, 17);
		board.add(label);
		
		Button btnPause = new Button("Pause");
		btnPause.setBounds(563, 23, 80, 29);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stop clock and pause game!
				pause.makeVisible();
			}
		});
		board.add(btnPause);
		
		//This was just for testing, couldn't wait for all the turns to time out.
		Button skipbutton = new Button("Skip Turn");
		skipbutton.setBounds(157, 23, 112, 29);
		skipbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.nextTurn();
			}
		});
		board.add(skipbutton);

		label_1 = new Label();
		label_1.setFont(new Font("Arial", Font.PLAIN, 16));
		label_1.setText("NO!");
		label_1.setBackground(Color.RED);
		label_1.setForeground(Color.BLACK);
		int labelx = ((width/2)-(170/2));
		label_1.setBounds(labelx, 475, 170, 90);
		board.add(label_1);
		
		countdown = new Timer(1000, new CountdownTimerListener());
		countdown.start();
		
		gameLoop = new Timer(100, new GameLoopListener(this));
		gameLoop.start();

		swap.setVisible(false);
		
		//TODO some kind of gameLogicLoop for the game's moves/turns
		//TODO reset timer if another move is allowed in a given turn as a result of capturing 
		
		//nextTurn should be called by the gameLogicLoop at the start 
		board.nextTurn();
		

	}
}