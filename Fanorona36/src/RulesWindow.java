import java.awt.EventQueue;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Window.Type;
import java.io.IOException;


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
	
    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL rulesURL = RulesWindow.class.getResource("/Rules/fanorona_rules.html");
        if (rulesURL != null) {
            try {
                editorPane.setPage(rulesURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + rulesURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSampleDemoHelp.html");
        }
 
        return editorPane;
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
		frmHowToPlay.setBounds(0, 0, 720, 480);
		frmHowToPlay.setLocationRelativeTo(null);
		frmHowToPlay.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmHowToPlay.getContentPane().setLayout(null);
		
		 //Create an editor pane.
        JEditorPane editorPane = createEditorPane();
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		editorScrollPane.setBounds(0, 0, 704, 441);
        frmHowToPlay.getContentPane().add(editorScrollPane);
        
	}
}
