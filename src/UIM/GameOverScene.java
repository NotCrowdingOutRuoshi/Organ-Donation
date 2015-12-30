package UIM;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GameOverScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameOverScene() {
		this.setSize(900, 600);
		
		JLabel lblGameOver = new JLabel("game over");
		add(lblGameOver);
	}

}
