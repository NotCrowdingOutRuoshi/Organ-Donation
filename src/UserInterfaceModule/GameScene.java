package UserInterfaceModule;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import CentralizedDataCenter.Entities.Player;
import DynamicObjectModule.DynamicObjectModule;

public class GameScene extends JPanel implements KeyListener {
	private Controller controller;
	private Player player;
	/**
	 * Create the panel.
	 */
	public GameScene(DynamicObjectModule dom,Player player) {
		this.setSize(900, 600);
		this.setLayout(null);
		this.player = player;
		this.addKeyListener(this);
		controller = new Controller(this.player);
		
		JPanel info = new JPanel();
		info.setBounds(20, 20, 840, 70);
		info.setBackground(Color.black);
		JPanel gamelayer = new JPanel();
		gamelayer.setBounds(20, 100, 840, 450);
		gamelayer.setBackground(Color.black);
		
		add(info);
		add(gamelayer);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		controller.keyPressed(e);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		controller.keyReleased(e);
	}

}
