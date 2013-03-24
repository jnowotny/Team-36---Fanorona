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

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Fanorona extends JFrame implements MouseListener {

	private static final long serialVersionUID = 3335293785778663915L;
	private JPanel contentPane;
	private Timer countdown;
	private Timer gameLoop;
	private Label label;
	private int timeRemaining = 25;
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
	
	//creates the handler for updating the timer!
	class GameLoopListener implements ActionListener {
		private Fanorona fan;
        public GameLoopListener(Fanorona f) {
        	fan = f;
        }

		public void actionPerformed(ActionEvent e) {
        	final int maxTurn = 50;
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
		super("MouseListener Test");
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
		
		label = new Label("Time Remaining: " + String.valueOf(timeRemaining));
		label.setBounds(428, 29, 130, 17);
		board.add(label);
		
		Button btnPause = new Button("Pause");
		btnPause.setBounds(563, 23, 80, 29);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stop clock and pause game!
				pause.makeVisible();
				//TESTING highlight on action
				(board.getBoardPieces())[2][4].setHighlight(true);
				board.repaint();
			}
		});
		board.add(btnPause);
		
		Button button = new Button("Skip Turn");
		button.setBounds(157, 23, 112, 29);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				board.nextTurn();
			}
		});
		board.add(button);
		
		
		
		countdown = new Timer(1000, new CountdownTimerListener());
		countdown.start();
		
		gameLoop = new Timer(100, new GameLoopListener(this));
		gameLoop.start();

		swap.setVisible(false);

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
}