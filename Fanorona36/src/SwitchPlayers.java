import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class SwitchPlayers extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwitchPlayers frame = new SwitchPlayers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SwitchPlayers() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("OK!");
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
