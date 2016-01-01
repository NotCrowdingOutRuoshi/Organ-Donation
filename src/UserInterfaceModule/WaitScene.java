package UserInterfaceModule;
import javax.swing.JPanel;

import CentralizedDataCenter.Entities.Player;
import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

public class WaitScene extends JPanel implements Runnable{

	
	private DynamicObjectModule dom;
	private Player player;
	/**
	 * Create the panel.
	 */
	public WaitScene(DynamicObjectModule dom,Player player) {
		this.setSize(1450, 850);
		this.setLayout(null);
		this.dom = dom;
		this.player = player;
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
			Player.setBounds(170, (this.getHeight()-300)/2, 100, 30);
			Player.setFont(new Font("Serif", Font.PLAIN, 25));
			add(Player);
		}
		
		
		for(int i=1;i<4;i++){
			String s = "waiting";
			Sprite player = null;
			if(i<dom.getAllDynamicObjects().length){
				player = dom.getAllDynamicObjects()[i];
				s = "Player "+player.getId();
			}
			JLabel Player1 = new JLabel(s);
			Player1.setBounds(170+i*320, (this.getHeight()-300)/2, 100, 30);
			Player1.setFont(new Font("Serif", Font.PLAIN, 25));
			add(Player1);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			repaint();
		}
	}
}
