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

public class Fanorona extends JFrame {
//Data members
	private static final long serialVersionUID = 3335293785778663915L;
	private JPanel contentPane;
	
	private Timer countdown;
	private Timer gameLoop;
	
	private Label timeLabel;
	private JLabel p1Score;
	private JLabel p2Score;
	
	private Button skipbutton;
	private Button btnMainMenu;
	private Button btnPause;
	
	protected Board board;
	private PauseMenu pause;
	private SwitchPlayers swap;
	private EndMenu endMenu;
	private Color maroon = new Color(80,0, 30);
	
	private int timeRemaining = 25;//TODO make this value correspond to the timer value input by user in new game menu
	private int curP1Score;
	private int curP2Score;
	private static int gameType;
	private static int numRows;
	private static int numCols;
	private static int timerLength;
	private static int port;
	private static String host;
	
	Server fanoServer = new Server(this, port);
	Client fanoClient = new Client(this, host, port);

//Internal Classes	
	
	//TODO reset timer when nextTurn() is called in board
	
	/*TODO add game condition: 
	 * 	if( (unable to make a move) && (board.getVisited().isEmpty) ){
	 * 		currentPlayer LOSES!
	 * 	}
	 */
	
	//creates the handler for updating the timer!
	class CountdownTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	if ((!pause.isVisible()) && (!swap.isVisible()) && (!endMenu.isVisible())) {
        		if (--timeRemaining > 0) 
	                timeLabel.setText("Time Remaining: " + String.valueOf(timeRemaining));
	            else {
	                timeLabel.setText("Time's up!");
	                swap.setVisible(true);
	                timeRemaining = 26;//TODO make this value correspond to the timer value input by user in new game menu
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
        	
        	fan.board.updateScores();
    		curP1Score = fan.board.getP1Score();
    		curP2Score = fan.board.getP2Score();
    		board.updateRemoveAvailable();
    		
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					p2Score.setText(" Maroon: " + Integer.toString(curP2Score)); 
    					p1Score.setText("   White: " + Integer.toString(curP1Score));
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
        	
        	//Enable player to end turn early with skipbutton if a capture has been made
        	if (board.getCapturedThisTurn() > 0){
        		skipbutton.setVisible(true);
        	}
        	else{
        		skipbutton.setVisible(false);
        	}
        	
        	//If a piece is selected, show destinations to move to...if there aren't any remove choices that need to be made
        	if(board.getSelected() != null){
	        	if( !(board.isRemoveAvailable()) ){
        			if(board.getBoardState().hasCaptureDestinations(board.getSelected())){
	        			board.highlightCaptureDestinations(board.getSelected());
	        		}
	        		else if(board.getBoardState().hasDestinations(board.getSelected())){
	        			board.highlightDestinations(board.getSelected());
	        		}
        		}
        	}
        	//Else show pieces that can be moved
        	else{
        		//If capture moves are available, show pieces that can make capture moves
        		if(board.getBoardState().hasCaptureMoves()){
        			board.highlightCaptureMovable();
        		}
        		//Else show pieces that can make paika moves, if any
        		else{
        			board.highlightPaikaMovable();
        		}
			}	
        }
    }
	
	/**
	 * Create the frame.
	 */
//Constructor
	public Fanorona(int game, int rows, int columns, int timer) {
	///////////////
		gameType = game;
		numRows = rows;
		numCols = columns;
		timerLength = timer;
	//////////////
		board = new Board(numRows, numCols);
		pause = new PauseMenu(this);
		swap = new SwitchPlayers(this);
		endMenu = new EndMenu(this);
	//////////////
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = 800;
	    int height = 150 + numRows*50;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screen.width - width) / 2;
	    int y = (screen.height - height) / 2;
	    setBounds(x, y, width, height);
		setTitle("Fanorona");
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(board);
		
		btnMainMenu = new Button("Main Menu");
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
		p1Score.setBackground(Color.white);
		p1Score.setForeground(maroon);
		p1Score.setBounds(50, 23, 70, 29);
		p1Score.setOpaque(true);
		board.add(p1Score);
		
		p2Score = new JLabel();
		p2Score.setBackground(maroon);
		p2Score.setForeground(Color.white);
		p2Score.setBounds(686, 23, 70, 29);
		p2Score.setOpaque(true);
		board.add(p2Score);
		
		timeLabel = new Label("Time Remaining: " + String.valueOf(timeRemaining));
		timeLabel.setBackground(Color.LIGHT_GRAY);
		timeLabel.setBounds(428, 29, 130, 17);
		board.add(timeLabel);
		
		btnPause = new Button("Pause");
		btnPause.setBounds(563, 23, 80, 29);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stop clock and pause game!
				pause.makeVisible();
			}
		});
		board.add(btnPause);
		
		/*	TODO make SkipTurn available if( !(board.getCapturedThisTurn > 0 )
		 * 		Player should be able to end turn early if they have made a capture.
		 * 
		 * 	SO KEEP THIS BUTTON ==> //This was just for testing, couldn't wait for all the turns to time out.
		 */
		skipbutton = new Button("Skip Turn");
		skipbutton.setBounds(157, 23, 112, 29);
		skipbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.nextTurn();
			}
		});
		skipbutton.setVisible(false);
		board.add(skipbutton);
		
		countdown = new Timer(1000, new CountdownTimerListener());
		countdown.start();
		
		gameLoop = new Timer(100, new GameLoopListener(this));
		gameLoop.start();

		swap.setVisible(false);
		
		board.nextTurn();
	}
}