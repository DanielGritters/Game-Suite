/**
 * @author Seth Pefley
 * Battleship GUI
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Class that instantiates and updates the game board based on the game logic.
 * 
 * @author Seth Pefley
 *
 */
public class BattleshipGUI extends JFrame {

	static JPanel gameBoardPanel;
	BattleshipGameBoard game = new BattleshipGameBoard();
	private JTextField colTxt;
	private JTextField rowTxt;
	private int[][] labelVals = new int[5][5];
	private JLabel[][] gbLabels = new JLabel[5][5];

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BattleshipGUI frame = new BattleshipGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	};

	/**
	 * Create the frame.
	 */
	public BattleshipGUI() {
		setTitle("BATTLESHIP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 516);
		gameBoardPanel = new JPanel();
		gameBoardPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(gameBoardPanel);
		
		JPanel statsPanel = new JPanel();
		
		JLabel turnsLabel = new JLabel("Turns:");
		turnsLabel.setFont(new Font("Georgia", Font.BOLD, 20));
		
		JLabel numTurns = new JLabel(BattleshipGameBoard.numShots + "");
		numTurns.setFont(new Font("Georgia", Font.BOLD, 20));
		
		JLabel shipsLabel = new JLabel("Ships Remaining:");
		shipsLabel.setFont(new Font("Georgia", Font.BOLD, 20));
		
		JLabel numShips = new JLabel(BattleshipGameBoard.shipsRemaining + "");
		numShips.setFont(new Font("Georgia", Font.BOLD, 20));
		GroupLayout gl_statsPanel = new GroupLayout(statsPanel);
		gl_statsPanel.setHorizontalGroup(
			gl_statsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_statsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_statsPanel.createSequentialGroup()
							.addComponent(turnsLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(numTurns, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_statsPanel.createSequentialGroup()
							.addComponent(shipsLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(numShips, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(307, Short.MAX_VALUE))
		);
		gl_statsPanel.setVerticalGroup(
			gl_statsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_statsPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_statsPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(turnsLabel)
						.addComponent(numTurns))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_statsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(numShips, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(shipsLabel))
					.addGap(106))
		);
		statsPanel.setLayout(gl_statsPanel);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JLabel lblRow = new JLabel("Select Row");
		lblRow.setHorizontalAlignment(SwingConstants.CENTER);
		lblRow.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblRow.setFont(new Font("Georgia", Font.BOLD, 20));
		
		JLabel lblCol = new JLabel("Select Column:");
		lblCol.setHorizontalAlignment(SwingConstants.CENTER);
		lblCol.setFont(new Font("Georgia", Font.BOLD, 20));
		lblCol.setAlignmentX(0.5f);
		
		colTxt = new JTextField();
		colTxt.setHorizontalAlignment(SwingConstants.LEFT);
		colTxt.setColumns(10);
		
		rowTxt = new JTextField();
		rowTxt.setColumns(10);
		
		JButton btnShoot = new JButton("SHOOT");
		btnShoot.addActionListener(new ActionListener() {
			
			/**
			 * Throws exception and creates a dialog box if input data is not numeric.
			 */
			public void actionPerformed(final ActionEvent arg0) {
				
				try {
					BattleshipGameBoard.shoot(Integer.parseInt(rowTxt.getText()), Integer.parseInt(colTxt.getText()));
					getVals();
					
					for (int row = 0; row < BattleshipGameBoard.board.length; row++) {
						for (int col = 0; col < BattleshipGameBoard.board.length; col++) {
							lblstring(row, col);
						}
					}
					
					numShips.setText(BattleshipGameBoard.shipsRemaining + "");
					numTurns.setText(BattleshipGameBoard.numShots + "");
					
					if (BattleshipGameBoard.shipsRemaining == 0) {
						JOptionPane.showMessageDialog(gameBoardPanel, "YOU WIN!");
						System.exit(0);
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(BattleshipGUI.gameBoardPanel, "Invalid input! Try again!");
				}
			}
		});
		btnShoot.setFont(new Font("Georgia", Font.BOLD, 20));
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCol, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_inputPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblRow, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(colTxt, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
						.addComponent(rowTxt))
					.addGap(18)
					.addComponent(btnShoot)
					.addGap(34))
		);
		
		gl_inputPanel.setVerticalGroup(
			gl_inputPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblRow)
						.addComponent(rowTxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCol, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addComponent(colTxt))
					.addContainerGap())
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap(37, Short.MAX_VALUE)
					.addComponent(btnShoot)
					.addGap(30))
		);
		inputPanel.setLayout(gl_inputPanel);
		
		JPanel titlePanel = new JPanel();
		
		JPanel gbPanel = new JPanel();
		GroupLayout gl_gameBoardPanel = new GroupLayout(gameBoardPanel);
		gl_gameBoardPanel.setHorizontalGroup(
			gl_gameBoardPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_gameBoardPanel.createSequentialGroup()
					.addGroup(gl_gameBoardPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_gameBoardPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(statsPanel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
						.addGroup(gl_gameBoardPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
						.addGroup(gl_gameBoardPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
						.addGroup(gl_gameBoardPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(gbPanel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_gameBoardPanel.setVerticalGroup(
			gl_gameBoardPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_gameBoardPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(statsPanel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(gbPanel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gbPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel colPanel = new JPanel();
		gbPanel.add(colPanel);
		colPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel7 = new JPanel();
		colPanel.add(panel7);
		panel7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel8 = new JPanel();
		colPanel.add(panel8);
		panel8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label4 = new JLabel("1");
		label4.setHorizontalAlignment(SwingConstants.CENTER);
		label4.setFont(new Font("Georgia", Font.BOLD, 20));
		panel8.add(label4);
		
		JPanel panel9 = new JPanel();
		colPanel.add(panel9);
		panel9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label5 = new JLabel("2");
		label5.setHorizontalAlignment(SwingConstants.CENTER);
		label5.setFont(new Font("Georgia", Font.BOLD, 20));
		panel9.add(label5);
		
		JPanel panel10 = new JPanel();
		colPanel.add(panel10);
		panel10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label6 = new JLabel("3");
		label6.setHorizontalAlignment(SwingConstants.CENTER);
		label6.setFont(new Font("Georgia", Font.BOLD, 20));
		panel10.add(label6);
		
		JPanel panel11 = new JPanel();
		colPanel.add(panel11);
		panel11.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label7 = new JLabel("4");
		label7.setHorizontalAlignment(SwingConstants.CENTER);
		label7.setFont(new Font("Georgia", Font.BOLD, 20));
		panel11.add(label7);
		
		JPanel panel12 = new JPanel();
		colPanel.add(panel12);
		panel12.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label8 = new JLabel("5");
		label8.setHorizontalAlignment(SwingConstants.CENTER);
		label8.setFont(new Font("Georgia", Font.BOLD, 20));
		panel12.add(label8);
		
		JPanel row1 = new JPanel();
		gbPanel.add(row1);
		row1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel13 = new JPanel();
		row1.add(panel13);
		panel13.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lbl1 = new JLabel("1");
		lbl1.setFont(new Font("Georgia", Font.BOLD, 20));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		panel13.add(lbl1);
		
		JPanel panel14 = new JPanel();
		row1.add(panel14);
		panel14.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl00 = new JLabel("~");
		lbl00.setHorizontalAlignment(SwingConstants.CENTER);
		lbl00.setFont(new Font("Georgia", Font.BOLD, 20));
		panel14.add(lbl00);
		gbLabels[0][0] = lbl00;
		labelVals[0][0] = -1;
		
		JPanel panel15 = new JPanel();
		row1.add(panel15);
		panel15.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl01 = new JLabel("~");
		lbl01.setHorizontalAlignment(SwingConstants.CENTER);
		lbl01.setFont(new Font("Georgia", Font.BOLD, 20));
		panel15.add(lbl01);
		gbLabels[0][1] = lbl01;
		labelVals[0][1] = -1;
		
		JPanel panel16 = new JPanel();
		row1.add(panel16);
		panel16.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl02 = new JLabel("~");
		lbl02.setHorizontalAlignment(SwingConstants.CENTER);
		lbl02.setFont(new Font("Georgia", Font.BOLD, 20));
		panel16.add(lbl02);
		gbLabels[0][2] = lbl02;
		labelVals[0][2] = -1;
		
		JPanel panel17 = new JPanel();
		row1.add(panel17);
		panel17.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl03 = new JLabel("~");
		lbl03.setHorizontalAlignment(SwingConstants.CENTER);
		lbl03.setFont(new Font("Georgia", Font.BOLD, 20));
		panel17.add(lbl03);
		gbLabels[0][3] = lbl03;
		labelVals[0][3] = -1;
		
		JPanel panel18 = new JPanel();
		row1.add(panel18);
		panel18.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl04 = new JLabel("~");
		lbl04.setHorizontalAlignment(SwingConstants.CENTER);
		lbl04.setFont(new Font("Georgia", Font.BOLD, 20));
		panel18.add(lbl04);
		gbLabels[0][4] = lbl04;
		labelVals[0][4] = -1;
		
		JPanel row2 = new JPanel();
		gbPanel.add(row2);
		row2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel19 = new JPanel();
		row2.add(panel19);
		panel19.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl2 = new JLabel("2");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setFont(new Font("Georgia", Font.BOLD, 20));
		panel19.add(lbl2);
		
		JPanel panel20 = new JPanel();
		row2.add(panel20);
		panel20.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl10 = new JLabel("~");
		lbl10.setHorizontalAlignment(SwingConstants.CENTER);
		lbl10.setFont(new Font("Georgia", Font.BOLD, 20));
		panel20.add(lbl10);
		gbLabels[1][0] = lbl10;
		labelVals[1][0] = -1;
		
		JPanel panel21 = new JPanel();
		row2.add(panel21);
		panel21.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl11 = new JLabel("~");
		lbl11.setHorizontalAlignment(SwingConstants.CENTER);
		lbl11.setFont(new Font("Georgia", Font.BOLD, 20));
		panel21.add(lbl11);
		gbLabels[1][1] = lbl11;
		labelVals[1][1] = -1;
		
		JPanel panel22 = new JPanel();
		row2.add(panel22);
		panel22.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl12 = new JLabel("~");
		lbl12.setHorizontalAlignment(SwingConstants.CENTER);
		lbl12.setFont(new Font("Georgia", Font.BOLD, 20));
		panel22.add(lbl12);
		gbLabels[1][2] = lbl12;
		labelVals[1][2] = -1;
		
		JPanel panel23 = new JPanel();
		row2.add(panel23);
		panel23.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl13 = new JLabel("~");
		lbl13.setHorizontalAlignment(SwingConstants.CENTER);
		lbl13.setFont(new Font("Georgia", Font.BOLD, 20));
		panel23.add(lbl13);
		gbLabels[1][3] = lbl13;
		labelVals[1][3] = -1;
		
		JPanel panel24 = new JPanel();
		row2.add(panel24);
		panel24.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl14 = new JLabel("~");
		lbl14.setHorizontalAlignment(SwingConstants.CENTER);
		lbl14.setFont(new Font("Georgia", Font.BOLD, 20));
		panel24.add(lbl14);
		gbLabels[1][4] = lbl14;
		labelVals[1][4] = -1;
		
		JPanel row3 = new JPanel();
		gbPanel.add(row3);
		row3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel25 = new JPanel();
		row3.add(panel25);
		panel25.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl3 = new JLabel("3");
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setFont(new Font("Georgia", Font.BOLD, 20));
		panel25.add(lbl3);
		
		JPanel panel26 = new JPanel();
		row3.add(panel26);
		panel26.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl20 = new JLabel("~");
		lbl20.setHorizontalAlignment(SwingConstants.CENTER);
		lbl20.setFont(new Font("Georgia", Font.BOLD, 20));
		panel26.add(lbl20);
		gbLabels[2][0] = lbl20;
		labelVals[2][0] = -1;
		
		JPanel panel27 = new JPanel();
		row3.add(panel27);
		panel27.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl21 = new JLabel("~");
		lbl21.setHorizontalAlignment(SwingConstants.CENTER);
		lbl21.setFont(new Font("Georgia", Font.BOLD, 20));
		panel27.add(lbl21);
		gbLabels[2][1] = lbl21;
		labelVals[2][1] = -1;
		
		JPanel panel28 = new JPanel();
		row3.add(panel28);
		panel28.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl22 = new JLabel("~");
		lbl22.setHorizontalAlignment(SwingConstants.CENTER);
		lbl22.setFont(new Font("Georgia", Font.BOLD, 20));
		panel28.add(lbl22);
		gbLabels[2][2] = lbl22;
		labelVals[2][2] = -1;
		
		JPanel panel29 = new JPanel();
		row3.add(panel29);
		panel29.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl23 = new JLabel("~");
		lbl23.setHorizontalAlignment(SwingConstants.CENTER);
		lbl23.setFont(new Font("Georgia", Font.BOLD, 20));
		panel29.add(lbl23);
		gbLabels[2][3] = lbl23;
		labelVals[2][3] = -1;
		
		JPanel panel30 = new JPanel();
		row3.add(panel30);
		panel30.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl24 = new JLabel("~");
		lbl24.setHorizontalAlignment(SwingConstants.CENTER);
		lbl24.setFont(new Font("Georgia", Font.BOLD, 20));
		panel30.add(lbl24);
		gbLabels[2][4] = lbl24;
		labelVals[2][4] = -1;
		
		JPanel row4 = new JPanel();
		gbPanel.add(row4);
		row4.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel31 = new JPanel();
		row4.add(panel31);
		panel31.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl4 = new JLabel("4");
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setFont(new Font("Georgia", Font.BOLD, 20));
		panel31.add(lbl4);
		
		JPanel panel32 = new JPanel();
		row4.add(panel32);
		panel32.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl30 = new JLabel("~");
		lbl30.setHorizontalAlignment(SwingConstants.CENTER);
		lbl30.setFont(new Font("Georgia", Font.BOLD, 20));
		panel32.add(lbl30);
		gbLabels[3][0] = lbl30;
		labelVals[3][0] = -1;
		
		JPanel panel33 = new JPanel();
		row4.add(panel33);
		panel33.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl31 = new JLabel("~");
		lbl31.setHorizontalAlignment(SwingConstants.CENTER);
		lbl31.setFont(new Font("Georgia", Font.BOLD, 20));
		panel33.add(lbl31);
		gbLabels[3][1] = lbl31;
		labelVals[3][1] = -1;
		
		JPanel panel34 = new JPanel();
		row4.add(panel34);
		panel34.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl32 = new JLabel("~");
		lbl32.setHorizontalAlignment(SwingConstants.CENTER);
		lbl32.setFont(new Font("Georgia", Font.BOLD, 20));
		panel34.add(lbl32);
		gbLabels[3][2] = lbl32;
		labelVals[3][2] = -1;
		
		JPanel panel35 = new JPanel();
		row4.add(panel35);
		panel35.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl33 = new JLabel("~");
		lbl33.setHorizontalAlignment(SwingConstants.CENTER);
		lbl33.setFont(new Font("Georgia", Font.BOLD, 20));
		panel35.add(lbl33);
		gbLabels[3][3] = lbl33;
		labelVals[3][3] = -1;
		
		JPanel panel36 = new JPanel();
		row4.add(panel36);
		panel36.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl34 = new JLabel("~");
		lbl34.setHorizontalAlignment(SwingConstants.CENTER);
		lbl34.setFont(new Font("Georgia", Font.BOLD, 20));
		panel36.add(lbl34);
		gbLabels[3][4] = lbl34;
		labelVals[3][4] = -1;
		
		JPanel row5 = new JPanel();
		gbPanel.add(row5);
		row5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel37 = new JPanel();
		row5.add(panel37);
		panel37.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl5 = new JLabel("5");
		lbl5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl5.setFont(new Font("Georgia", Font.BOLD, 20));
		panel37.add(lbl5);
		
		JPanel panel38 = new JPanel();
		row5.add(panel38);
		panel38.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl40 = new JLabel("~");
		lbl40.setHorizontalAlignment(SwingConstants.CENTER);
		lbl40.setFont(new Font("Georgia", Font.BOLD, 20));
		panel38.add(lbl40);
		gbLabels[4][0] = lbl40;
		labelVals[4][0] = -1;
		
		JPanel panel39 = new JPanel();
		row5.add(panel39);
		panel39.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl41 = new JLabel("~");
		lbl41.setHorizontalAlignment(SwingConstants.CENTER);
		lbl41.setFont(new Font("Georgia", Font.BOLD, 20));
		panel39.add(lbl41);
		gbLabels[4][1] = lbl41;
		labelVals[4][1] = -1;
		
		JPanel panel40 = new JPanel();
		row5.add(panel40);
		panel40.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl42 = new JLabel("~");
		lbl42.setHorizontalAlignment(SwingConstants.CENTER);
		lbl42.setFont(new Font("Georgia", Font.BOLD, 20));
		panel40.add(lbl42);
		gbLabels[4][2] = lbl42;
		labelVals[4][2] = -1;
		
		JPanel panel41 = new JPanel();
		row5.add(panel41);
		panel41.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl43 = new JLabel("~");
		lbl43.setHorizontalAlignment(SwingConstants.CENTER);
		lbl43.setFont(new Font("Georgia", Font.BOLD, 20));
		panel41.add(lbl43);
		gbLabels[4][3] = lbl43;
		labelVals[4][3] = -1;
		
		JPanel panel42 = new JPanel();
		row5.add(panel42);
		panel42.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lbl44 = new JLabel("~");
		lbl44.setHorizontalAlignment(SwingConstants.CENTER);
		lbl44.setFont(new Font("Georgia", Font.BOLD, 20));
		panel42.add(lbl44);
		gbLabels[4][4] = lbl44;
		labelVals[4][4] = -1;
		
		JLabel lblBattleship = new JLabel("BATTLESHIP");
		lblBattleship.setHorizontalAlignment(SwingConstants.CENTER);
		lblBattleship.setFont(new Font("Georgia", Font.BOLD, 20));
		lblBattleship.setAlignmentX(0.5f);
		titlePanel.add(lblBattleship);
		gameBoardPanel.setLayout(gl_gameBoardPanel);
	}
	
	/**
	 * Retrieves the numerical value in the cells that determines the type
	 * of cell from the BattleshipGameBoard class.
	 */
	public void getVals() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				labelVals[i][j] = BattleshipGameBoard.board[i][j];	
			}
		}	
	}
	
	/**
	 * Updates the value of each label on the game board with a character
	 * based upon the value in the game board cell.
	 * 
	 * @param row
	 * @param col
	 */
	public void lblstring(final int row, final int col) {
		if (BattleshipGameBoard.board[row][col] == -2 || BattleshipGameBoard.board[row][col] == -1) {
			gbLabels[row][col].setText("~");
		} else if (BattleshipGameBoard.board[row][col] == 0) {
			gbLabels[row][col].setText("O");
		} else {
			gbLabels[row][col].setText("X");
		}
	}
}
