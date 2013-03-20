import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.TextArea;
import java.awt.Font;


public class RulesWindow {

	private JFrame frmHowToPlay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RulesWindow window = new RulesWindow();
					window.frmHowToPlay.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RulesWindow() {
		initialize();
	}
	
	public void makeVisible() {
		frmHowToPlay.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHowToPlay = new JFrame();
		frmHowToPlay.setType(Type.POPUP);
		frmHowToPlay.setTitle("-How To Play Fanorona-");
		frmHowToPlay.setBounds(100, 100, 600, 400);
		frmHowToPlay.setLocationRelativeTo(null);
		frmHowToPlay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHowToPlay.getContentPane().setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textArea.setBounds(0, 0, 584, 359);
		frmHowToPlay.getContentPane().add(textArea);
		textArea.setText("Rules Go Here - Fanarona Rules - ");
		textArea.setEditable(false);
	}
}
