import javax.swing.JFrame;
//import javax.swing.JInternalFrame;
import javax.swing.JButton;
//import java.awt.BorderLayout;
//import java.awt.GridLayout;
import javax.swing.JTextPane;
//import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SwitchPlayers extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6521832783486381602L;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public SwitchPlayers(final Fanorona f) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("OK!");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				f.board.nextTurn();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton.setBounds(145, 137, 117, 29);
		getContentPane().add(btnNewButton);
		
		JTextPane txtpnNextUsersTurn = new JTextPane();
		txtpnNextUsersTurn.setEditable(false);
		txtpnNextUsersTurn.setBackground(SystemColor.window);
		txtpnNextUsersTurn.setText("Next User's Turn");
		txtpnNextUsersTurn.setBounds(145, 80, 144, 16);
		getContentPane().add(txtpnNextUsersTurn);

	}
}
