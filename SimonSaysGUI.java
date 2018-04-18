import java.awt.BorderLayout;
import javax.swing.JFrame;


/**
 * Creates the window and adds a gamePanel to it.
 * 
 *@author Kevin Holkeboer
 *@version 1.0
 *@since 1.0
 */
public class SimonSaysGUI extends JFrame {
	/**
	 * Constructs a window with default values.
	 */
	public SimonSaysGUI() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setLayout(new BorderLayout());
		
		SimonSaysPanel gamePanel = new SimonSaysPanel();
		add(gamePanel);
			
		setSize(800,800);
		setTitle("Simon Says");
		setResizable(true);
		setVisible(true);
	}
}
