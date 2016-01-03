package UserInterfaceModule;

import javax.swing.JPanel;

import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Entities.VirtualCharacter;
import Resources.Resources;
import Utility.Audio.AudioManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class WaitScene extends JPanel implements Runnable {

	private DynamicObjectModule dom;
	private VirtualCharacter player;

	/**
	 * Create the panel.
	 */
	public WaitScene(DynamicObjectModule dom) {
		this.setSize(1450, 850);
		this.setLayout(null);
		this.dom = dom;
		/* Temporary comment out */
		AudioManager.getInstance().stop();
		AudioManager.getInstance().addBackGroundMusic("Music/WaitScene.wav");
		AudioManager.getInstance().setLoop();
		AudioManager.getInstance().play();
		JLabel lblWait = new JLabel("Waiting");
		lblWait.setBounds((this.getWidth() - 200) / 2, 20, 200, 50);
		lblWait.setFont(new Font("Serif", Font.PLAIN, 50));
		lblWait.setForeground(Color.white);
		add(lblWait);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(Resources.getResourceStream("Scene/menu.jpg")));
			g.drawImage(icon.getImage(), 0, 0, getSize().width, getSize().height, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JLabel label = new JLabel("Your id : ");
		label.setBounds(150, (this.getHeight() - 300) / 2, 150, 35);
		label.setFont(new Font("Serif", Font.PLAIN, 35));
		label.setForeground(Color.white);
		add(label);

		if (GameManager.getInstance().getClientId() != 0) {
			JLabel Player = new JLabel("" + GameManager.getInstance().getClientId());
			Player.setBounds(280, (this.getHeight() - 300) / 2, 50, 30);
			Player.setFont(new Font("Serif", Font.PLAIN, 35));
			Player.setForeground(Color.white);
			add(Player);
		}
		String[] playerId = new String[3];
		Sprite otherplayer = null;
		int j = 0;
		for (int i = 0; i < dom.getAllDynamicObjects().length; i++) {
			otherplayer = dom.getAllDynamicObjects()[i];
			if (otherplayer.getId() != GameManager.getInstance().getClientId()) {
				playerId[j] = "Player " + otherplayer.getId();
				j++;
			}
		}

		for (int i = 0; i < 3; i++) {
			String s = "";
			if (playerId[i] != null) {
				s = playerId[i];
			}
			JLabel Player1 = new JLabel(s);
			Player1.setBounds(490 + i * 320, (this.getHeight() - 300) / 2, 150, 35);
			Player1.setFont(new Font("Serif", Font.PLAIN, 35));
			Player1.setForeground(Color.white);
			add(Player1);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
}
