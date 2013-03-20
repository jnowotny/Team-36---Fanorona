import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;


public class AboutWin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2763013922320706076L;
	private JPanel contentPane;
	private final JButton btnClose = new JButton("Close");
	private final JTextArea txtrFanoromaByTeam = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutWin frame = new AboutWin();
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
	public AboutWin() {
		initGUI();
	}
	private void initGUI() {
		setTitle("About Fanoroma");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setBounds(157, 243, 117, 29);
		
		contentPane.add(btnClose);
		txtrFanoromaByTeam.setEditable(false);
		txtrFanoromaByTeam.setText("                                    Fanoroma by Team 36\n                                            Version 1.0\n\n                         Elliott Fawcett - elliottcf@tamu.edu\n               Odair Fernandes - oda.fernandes10@gmail.com\n                   John Nowotny - john.nowotny@gmail.com\n                          Kodi Tapie - kstapie@yahoo.com\n\n");
		txtrFanoromaByTeam.setBounds(6, 6, 438, 225);
		
		contentPane.add(txtrFanoromaByTeam);
	}
}
