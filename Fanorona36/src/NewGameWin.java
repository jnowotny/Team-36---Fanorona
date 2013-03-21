//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.factories.FormFactory;
//import com.jgoodies.forms.layout.RowSpec;


public class NewGameWin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 137750314898293920L;
	private JPanel contentPane;
	private final JButton btnNewGame = new JButton("Player 1 vs CPU (TEST)");
	private final JButton btnNewButton = new JButton("Player 1 vs Player 2");
	private final JButton btnNewButton_1 = new JButton("CPU vs CPU");
	private final JButton btnExit = new JButton("Exit");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewGameWin frame = new NewGameWin();
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
	public NewGameWin() {
		initGUI();
	}
	private void initGUI() {
		setTitle("New Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		btnNewGame.setBounds(34, 93, 164, 29);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fanorona win = new Fanorona();
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.setLayout(null);
		
		contentPane.add(btnNewGame);
		btnNewButton.setBounds(251, 93, 164, 29);
		
		contentPane.add(btnNewButton);
		btnNewButton_1.setBounds(139, 161, 164, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		contentPane.add(btnNewButton_1);
		btnExit.setBounds(6, 243, 438, 29);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		contentPane.add(btnExit);
		
		JButton btnNewButton_2 = new JButton("Back to Main Menu");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Welcome_Menu win = new Welcome_Menu();
				win.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(16, 33, 428, 29);
		contentPane.add(btnNewButton_2);
	}
}
