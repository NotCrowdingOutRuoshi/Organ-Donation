package UserInterfaceModule;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameOverScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameOverScene() {
		this.setSize(1450, 850);
		
		JLabel lblGameOver = new JLabel("game over");
		add(lblGameOver);
	}

}
