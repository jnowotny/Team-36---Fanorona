import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Window.Type;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Panel;
import java.awt.Color;


public class PauseMenu {
	private JFrame frmPause;
	private static Fanorona fan;
	private JLabel gameInfoText;
	protected int curTurn;
	protected int curP1Score;
	protected int curP2Score;
	private Timer updateText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PauseMenu window = new PauseMenu(fan);
					window.frmPause.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class UpdateTimerListener implements ActionListener {
		private Fanorona fan;
        public UpdateTimerListener(Fanorona f) {
        	fan = f;
        }
        public void actionPerformed(ActionEvent e) {
    		curTurn = fan.board.getTurnCount();
    		curP1Score = fan.board.getP1Score();
    		curP2Score = fan.board.getP2Score();
    		EventQueue.invokeLater(new Runnable() {
    			public void run() {
    				try {
    					gameInfoText.setText("Maroon: " + Integer.toString(curP2Score) + " White: " + Integer.toString(curP1Score) + " Moves Remaining: " + Integer.toString(50 - curTurn));
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    			}
    		});
        }
    }
	
	/**
	 * Create the application.
	 */
	public PauseMenu(Fanorona f) {
		fan = f;
		initialize(fan);
	}
	
	public void makeVisible() {
		frmPause.setVisible(true);
	}
	
	public boolean isVisible() {
		return frmPause.isVisible();
	}
	
	public void dipose() {
		frmPause.dispose();
	}
	
	public boolean isOpen() {
		return frmPause.isActive();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Fanorona f) {
		frmPause = new JFrame();
		frmPause.setTitle("-Pause-");
		frmPause.setType(Type.POPUP);
		frmPause.setResizable(false);
		frmPause.setBounds(100, 100, 450, 300);
		frmPause.setLocationRelativeTo(null);
		frmPause.getContentPane().setLayout(null);
		Button button_1 = new Button("Resume Game");
		button_1.setBounds(75, 69, 300, 33);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmPause.setVisible(false);
				//Resume Game
			}
		});
		
		Button button = new Button("Return To Menu");
		button.setBounds(75, 135, 300, 33);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Welcome_Menu win = new Welcome_Menu();
				win.setVisible(true);
				frmPause.dispose();
				f.dispose();
				//Launch Menu
			}
		});		
		Button button_2 = new Button("How To Play");
		button_2.setBounds(75, 102, 300, 33);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RulesWindow openRules = new RulesWindow();
				openRules.makeVisible();
			}
		});
		frmPause.getContentPane().add(button_2);
		frmPause.getContentPane().add(button);
		frmPause.getContentPane().add(button_1);
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 444, 271);
		frmPause.getContentPane().add(panel);
		
		gameInfoText = new JLabel();
		gameInfoText.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		gameInfoText.setVerifyInputWhenFocusTarget(false);
		curTurn = f.board.getTurnCount();
		curP1Score = f.board.getP1Score();
		curP2Score = f.board.getP2Score();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameInfoText.setText("Maroon: " + Integer.toString(curP2Score) + " White: " + Integer.toString(curP1Score) + " Moves Remaining: " + Integer.toString(50 - curTurn));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		updateText = new Timer(1000, new UpdateTimerListener(f));
		updateText.start();
		panel.add(gameInfoText);
	}




}
