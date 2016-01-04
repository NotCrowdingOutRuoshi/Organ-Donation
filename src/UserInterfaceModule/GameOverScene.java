package UserInterfaceModule;
import javax.swing.JPanel;

import Common.Constants;
import Resources.Resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class GameOverScene extends JPanel {

	/**
	 * Create the panel.
	 */
	public GameOverScene() {
		this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		this.setLayout(null);
		JLabel lblGameOver = new JLabel("GAMEOVER ! ");
		lblGameOver.setBounds((this.getWidth()-300)/2, 100, 500, 100);
		lblGameOver.setFont(new Font("Serif", Font.PLAIN, 36));
		lblGameOver.setForeground(Color.white);
		add(lblGameOver);
		
		JLabel lblWinnerInfo = new JLabel("Winner is Player "+GameManager.getInstance().getWinnerId());
		lblWinnerInfo.setBounds((this.getWidth()-250)/2, 200, 500, 100);
		lblWinnerInfo.setFont(new Font("Serif", Font.PLAIN, 24));
		lblWinnerInfo.setForeground(Color.WHITE);
		add(lblWinnerInfo);
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
