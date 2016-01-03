package UserInterfaceModule;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;
import RenderEngine.RenderEngine;
import Resources.Resources;
import SceneDataModule.SceneDataModule;
import Utility.Audio.AudioManager;

public class GameScene extends JPanel implements KeyListener {
	private Controller controller;
	private Sprite player;
	private JPanel gamePanel;
	private DynamicObjectModule dom;
	
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public GameScene(DynamicObjectModule dom) throws IOException {
		this.setSize(1450, 850);
		this.setLayout(null);
		
		this.dom = dom;
		this.player = dom.findSprite(GameManager.getInstance().getClientId());
		AudioManager.getInstance().stop();
		AudioManager.getInstance().addBackGroundMusic("Music/GameScene.wav");
		AudioManager.getInstance().setLoop();
		AudioManager.getInstance().play();
		
		SceneDataModule sceneDataModule = new SceneDataModule(ImageIO.read(Resources.getResourceStream("Scene/Scene.png")));
		RenderEngine renderEngine = new RenderEngine(dom, sceneDataModule);
		gamePanel = renderEngine.getPanel();
		gamePanel.setSize(1450,850);
		add(gamePanel);
		renderEngine.startRendering();
//		renderEngine.stopRendering();

	}
	
	public void gameStart(){
		this.addKeyListener(this);
		this.player = dom.findSprite(GameManager.getInstance().getClientId());
		controller = new Controller(this.player);
	}
	
	public void setCountDown(int i){
		JLabel lblWait = new JLabel(i+"");
		lblWait.setBounds((this.getWidth()-100)/2, 20, 100, 30);
		gamePanel.add(lblWait);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(player == null) {
			player = dom.findSprite(GameManager.getInstance().getClientId());
			controller.setEntity(player);
			keyPressed(e);
		}
		else {
			controller.keyPressed(e);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(player == null) {
			player = dom.findSprite(GameManager.getInstance().getClientId());
			controller.setEntity(player);
			keyReleased(e);
		}
		else {
			controller.keyReleased(e);
		}
	}
	

}
