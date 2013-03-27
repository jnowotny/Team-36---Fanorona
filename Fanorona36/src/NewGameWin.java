//import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
//import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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
	private final JButton btnExit = new JButton("Exit");
	private JComboBox<String> comboBox;
	private JSpinner spinnerRows;
	private JSpinner spinnerCols;
	private JTextField textField;
	private JLabel lblSetTime;
	private JLabel lblMilliseconds;
	private boolean timerButtonSelected = false;

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
		int width = 475;
	    int height = 330;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screen.width - width) / 2;
	    int y = (screen.height - height) / 2;
	    setBounds(x, y, width, height);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnExit.setBounds(16, 35, 428, 29);
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
		btnNewButton_2.setBounds(16, 6, 428, 29);
		contentPane.add(btnNewButton_2);
		
		String[] gameTypes = new String[] {"Player 1 vs. Player 2", "Player vs. CPU", "CPU vs. CPU"};
		comboBox = new JComboBox<String>(gameTypes);
		comboBox.setToolTipText("");
		comboBox.setBounds(107, 74, 175, 27);
		contentPane.add(comboBox);
		
		JLabel lblGameType = new JLabel("Game Type");
		lblGameType.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblGameType.setBounds(26, 78, 79, 16);
		contentPane.add(lblGameType);
		
		JLabel lblNewLabel = new JLabel("Board Size");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(26, 122, 79, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblRows = new JLabel("Rows");
		lblRows.setBounds(117, 122, 39, 16);
		contentPane.add(lblRows);
		
		JLabel lblColumns = new JLabel("Columns");
		lblColumns.setBounds(218, 122, 60, 16);
		contentPane.add(lblColumns);
		
		SpinnerNumberModel modelRows = new SpinnerNumberModel(1,1,14,2);
		spinnerRows = new JSpinner(modelRows);
		spinnerRows.setBounds(157, 116, 53, 28);
		contentPane.add(spinnerRows);
		
		SpinnerNumberModel modelCols = new SpinnerNumberModel(1,1,14,2);
		spinnerCols = new JSpinner(modelCols);
		spinnerCols.setBounds(279, 116, 53, 28);
		contentPane.add(spinnerCols);
		
		JLabel lblTimer = new JLabel("Timer?");
		lblTimer.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTimer.setBounds(26, 171, 61, 16);
		contentPane.add(lblTimer);
		
		
		lblSetTime = new JLabel("Set time:");
		lblSetTime.setBounds(157, 202, 61, 16);
		contentPane.add(lblSetTime);
		lblSetTime.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(218, 196, 85, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		lblMilliseconds = new JLabel("milliseconds");
		lblMilliseconds.setBounds(305, 202, 85, 16);
		contentPane.add(lblMilliseconds);
		lblMilliseconds.setVisible(false);
		
		JRadioButton noTimerButton = new JRadioButton("No");
		noTimerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSetTime.setVisible(false);
				textField.setVisible(false);
				lblMilliseconds.setVisible(false);
				timerButtonSelected = true;
			}
		});
		noTimerButton.setBounds(84, 167, 60, 23);
		contentPane.add(noTimerButton);
		
		
		JRadioButton yesTimerButton = new JRadioButton("Yes");
		yesTimerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSetTime.setVisible(true);
				textField.setVisible(true);
				lblMilliseconds.setVisible(true);
				timerButtonSelected = true;
			}
		});
		yesTimerButton.setBounds(157, 167, 60, 23);
		contentPane.add(yesTimerButton);
		
		ButtonGroup timerSelection = new ButtonGroup();
		timerSelection.add(noTimerButton);
		timerSelection.add(yesTimerButton);
		
		JButton btnCreateNewGame = new JButton("Create New Game");
		btnCreateNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (timerButtonSelected) {
					int game = comboBox.getSelectedIndex(); //0 - PvP; 1 - PvC; 2 - CvC
					int numRows = (int) spinnerRows.getValue();
					int numCols = (int) spinnerCols.getValue();
					int timerLength = -1;
					if (textField.isVisible() && textField.getText() != "")
						timerLength = Integer.parseInt(textField.getText());
					Fanorona newGame = new Fanorona(game, numRows, numCols, timerLength);
					newGame.setVisible(true);
				}
				
			}
		});
		btnCreateNewGame.setBounds(71, 246, 319, 56);
		contentPane.add(btnCreateNewGame);
		
	}
}
