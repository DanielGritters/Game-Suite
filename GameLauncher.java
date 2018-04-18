import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameLauncher implements ActionListener{
	JLabel gameSuiteLabel = new JLabel("Game Suite:");
	JLabel simonSaysLabel = new JLabel("Simon Says");
	JLabel battleShipLabel = new JLabel("Battleship");
	JLabel mineSweeperLabel = new JLabel("Mine Sweeper");
	
	JButton simonSays = new JButton("Launch Simon Says");
	JButton battleShip = new JButton("Launch Battleship");
	JButton mineSweeper = new JButton("Launch Mine Sweeper");
	
	public static void main(String[] args) {
		
		new GameLauncher();

	}
	
	public GameLauncher() {
		JFrame gameLauncherFrame = new JFrame("Game Suite Launcher");
		gameLauncherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel gameLauncherPanel = new JPanel();
		gameLauncherPanel.setLayout(new BoxLayout(gameLauncherPanel, BoxLayout.Y_AXIS));
		
		gameSuiteLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		gameSuiteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		simonSaysLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		simonSaysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		battleShipLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		battleShipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mineSweeperLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		mineSweeperLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		simonSays.addActionListener(this);
		simonSays.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		battleShip.addActionListener(this);
		battleShip.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mineSweeper.addActionListener(this);
		mineSweeper.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		gameLauncherPanel.add(gameSuiteLabel);
		
		gameLauncherPanel.add(simonSaysLabel);
		gameLauncherPanel.add(simonSays);
		
		gameLauncherPanel.add(battleShipLabel);
		gameLauncherPanel.add(battleShip);
		
		gameLauncherPanel.add(mineSweeperLabel);
		gameLauncherPanel.add(mineSweeper);
		
		gameLauncherFrame.add(gameLauncherPanel);
		gameLauncherFrame.setSize(400, 300);
		
		gameLauncherPanel.setVisible(true);
		gameLauncherFrame.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String[] args = {};
		if(e.getSource() == simonSays) {
			new SimonSaysGUI();
		} else if (e.getSource() == battleShip) {
			Battleship.main(args);
		} else if (e.getSource() == mineSweeper) {
			MineSweeper.main(args);
		}
		
	}

}
