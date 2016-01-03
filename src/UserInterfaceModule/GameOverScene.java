package UserInterfaceModule;
import javax.swing.JPanel;

import Resources.Resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameOverScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameOverScene() {
		this.setSize(1450, 850);
		this.setLayout(null);
		JLabel lblGameOver = new JLabel("GAMEOVER ! Winner is Player "+GameManager.getInstance().getWinnerId());
		lblGameOver.setBounds((this.getWidth()-800)/2, 300, 800, 100);
		lblGameOver.setFont(new Font("Serif", Font.PLAIN, 50));
		lblGameOver.setForeground(Color.white);
		add(lblGameOver);
	}
	public void paintComponent(Graphics g){
		 
		 try {
			ImageIcon icon=new ImageIcon(ImageIO.read(Resources.getResourceStream("Scene/menu.jpg")));
			 g.drawImage(icon.getImage(),0,0,getSize().width,getSize().height,this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	 }
}
