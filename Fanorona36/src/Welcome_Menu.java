import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Welcome_Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6917194983908145199L;
	private JPanel contentPane;
	private final JButton btnRules = new JButton("Rules");

	/**
	 * Create the frame.
	 */
	public Welcome_Menu() {
		setTitle("Fanorona");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//quits application on window X
		int width = 470;
	    int height = 330;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screen.width - width) / 2;
	    int y = (screen.height - height) / 2;
	    setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//New game button
		JButton btnStartANew = new JButton("Start a New Game");
		btnStartANew.addActionListener(new ActionListener() { //opens newGame window and closes main menu
			public void actionPerformed(ActionEvent e) {
				NewGameWin win = new NewGameWin();
				win.setVisible(true);
				dispose();
			}
		});
		btnStartANew.setBounds(6, 140, 440, 29);
		contentPane.add(btnStartANew);
		
		//Rules button
		btnRules.addActionListener(new ActionListener() { //opens rules window
			public void actionPerformed(ActionEvent arg0) {
				RulesWindow win = new RulesWindow();
				win.makeVisible();
			}
		});
		btnRules.setBounds(6, 174, 440, 29);
		contentPane.add(btnRules);
		
		//About button
		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() { //opens about window
			public void actionPerformed(ActionEvent arg0) {
				AboutWin win = new AboutWin();
				win.setVisible(true);
			}
		});
		btnAbout.setBounds(6, 209, 440, 29);
		contentPane.add(btnAbout);
		
		//Quit button
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() { //quits applications
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(6, 243, 440, 29);
		contentPane.add(btnQuit);
		
		//Fanorona text
		JTextPane txtpnFanorona = new JTextPane();
		txtpnFanorona.setToolTipText("");
		txtpnFanorona.setFont(new Font("Lucida Grande", Font.BOLD, 44));
		txtpnFanorona.setText("Fanorona!");
		txtpnFanorona.setEditable(false);
		txtpnFanorona.setBounds(6, 6, 438, 124);
		contentPane.add(txtpnFanorona);
	}
}
