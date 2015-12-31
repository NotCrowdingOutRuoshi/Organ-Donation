package UserInterfaceModule;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import CentralizedDataCenter.Entities.Player;
import DynamicObjectModule.DynamicObjectModule;
import RenderEngine.RenderEngine;
import Resources.Resources;
import SceneDataModule.SceneDataModule;

public class GameScene extends JPanel implements KeyListener {
	private Controller controller;
	private Player player;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public GameScene(DynamicObjectModule dom,Player player) throws IOException {
		this.setSize(1450, 850);
		this.setLayout(null);
		this.player = player;
		this.addKeyListener(this);
		controller = new Controller(this.player);
		
		SceneDataModule sceneDataModule = new SceneDataModule(ImageIO.read(Resources.getResource("Scene/Scene.jpg")));
		RenderEngine renderEngine = new RenderEngine(dom, sceneDataModule);
		JPanel p = renderEngine.getPanel();
		p.setSize(1450,850);
		add(p);
		
		renderEngine.startRendering();
		renderEngine.stopRendering();

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
