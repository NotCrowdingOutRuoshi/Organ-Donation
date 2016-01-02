package UserInterfaceModule;
import javax.swing.JPanel;

import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Entities.VirtualCharacter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class WaitScene extends JPanel implements Runnable{

	
	private DynamicObjectModule dom;
	private VirtualCharacter player;
	/**
	 * Create the panel.
	 */
	public WaitScene(DynamicObjectModule dom) {
		this.setSize(1450, 850);
		this.setLayout(null);
		this.dom = dom;
		/* Temporary comment out*/
		JLabel lblWait = new JLabel("Waiting");
		lblWait.setBounds((this.getWidth()-100)/2, 20, 100, 30);
		lblWait.setFont(new Font("Serif", Font.PLAIN, 30));
		add(lblWait);

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		JLabel label = new JLabel("Your id : ");
		label.setBounds(150, (this.getHeight()-300)/2, 100, 30);
		label.setFont(new Font("Serif", Font.PLAIN, 25));
		add(label);
		
		if(player.getId()!=0){
			JLabel Player = new JLabel(""+player.getId());
			Player.setBounds(240, (this.getHeight()-300)/2, 100, 30);
			Player.setFont(new Font("Serif", Font.PLAIN, 25));
			add(Player);
		}
		String[] playerId = new String[3];
		Sprite otherplayer = null;
		int j=0;
		for(int i=0;i<dom.getAllDynamicObjects().length;i++){
			otherplayer = dom.getAllDynamicObjects()[i];
			if(otherplayer.getId()!=player.getId()){
				playerId[j] = "Player "+otherplayer.getId();
				j++;
			}
		}
		
		for(int i=0;i<3;i++){
			String s = "";
			if(playerId[i]!=null){
				s = playerId[i];
			}
			JLabel Player1 = new JLabel(s);
			Player1.setBounds(490+i*320, (this.getHeight()-300)/2, 100, 30);
			Player1.setFont(new Font("Serif", Font.PLAIN, 25));
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
