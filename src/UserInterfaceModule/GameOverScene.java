package UserInterfaceModule;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JLabel;

public class GameOverScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameOverScene() {
		this.setSize(1450, 850);
		
		JLabel lblGameOver = new JLabel("GAMEOVER ! Winner is Player "+GameManager.getInstance().getWinnerId());
		lblGameOver.setBounds((this.getWidth()-100)/2, 20, 100, 100);
		lblGameOver.setFont(new Font("Serif", Font.PLAIN, 50));
		add(lblGameOver);
	}

}
