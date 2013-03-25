import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.xml.internal.ws.util.StringUtils;

import java.awt.Window.Type;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;


public class RulesWindow {

	private JFrame frmHowToPlay;
	private JLabel htmlRules;
	private String ruleString;

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
		textArea.setText("Basic Ideas\n\nFanorona is a board game for two players. \nIt is played with black and white pieces placed on a board. \nThe board is a rectangular grid of nine lines by five, with some diagonal lines marked. \nThe two players each have 22 pieces of their colour on the board. \nOne player has the black pieces and the other has white. \nWhite moves first, then Maroon, and so on with the players taking turns. \nTo win the game, you have to take all your opponent's pieces, \nor to leave a position where they can't move. \nIf neither player manages to win, then the game is a draw.");
		textArea.setEditable(false);
		

		//FileReader fr=new FileReader("path of the html file");
		//BufferedReader br= new BufferedReader(fr);
		//StringBuilder content=new StringBuilder(1024);
		//while((ruleString=br.readLine())!=null)
		//    {
		//    content.append(ruleString);
		//   }
		
//		htmlRules = new JLabel();
//		htmlRules.setAutoscrolls(true);
//		htmlRules.setAlignmentY(Component.BOTTOM_ALIGNMENT);
//		htmlRules.setVerifyInputWhenFocusTarget(false);
//		//StringUtils.replaceEach(ruleString, new String[]{"&", "\"", "<", ">"}, new String[]{"&amp;", "&quot;", "&lt;", "&gt;"});
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					htmlRules.setText("Basic Ideas Fanorona is a board game for two players. It is played with black and white pieces placed on a board. The board is a rectangular grid of nine lines by five, with some diagonal lines marked. The picture shows the board at the start of the game. The two players each have 22 pieces of their colour on the board. The pieces are placed where the gridlines cross, as shown. Ready to Start! One player has the black pieces and the other has white. White moves first, then Black, and so on with the players taking turns. To win the game, you have to take all your opponent's pieces, or to leave a position where they can't move. If neither player manages to win, then the game is a draw.");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		htmlRules.setBounds(0, 0, 584, 361);
//		frmHowToPlay.getContentPane().add(htmlRules);
	}
}
