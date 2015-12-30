package RenderEngine;

import java.awt.Graphics;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Common.Interfaces.IRenderEngine;
import DynamicObjectModule.DynamicObjectModule;
import RenderEngine.Scene.SceneRenderEngine;
import RenderEngine.Sprite.SpriteRenderEngine;
import Resources.Resources;
import SceneDataModule.SceneDataModule;

public class RenderEngine implements IRenderEngine {
	private JPanel _canvas;
	private SpriteRenderEngine _spriteRenderEngine;
	private SceneRenderEngine _sceneRenderEngine;
	private Thread _renderThread;
	private boolean _isRendering;
	
	public RenderEngine(DynamicObjectModule dom, SceneDataModule sdm) {
		//assert (dom != null);
		assert (sdm != null);
		
		//_spriteRenderEngine = new SpriteRenderEngine(dom);
		_sceneRenderEngine = new SceneRenderEngine(sdm);
		_canvas = new JPanel() {
			public void paintComponent(Graphics g) {
				render(g);
			}
		};
		_canvas.setDoubleBuffered(true);
		_isRendering = false;
		_renderThread = new Thread(new RenderThread());
	}
	
	// For scene render engine demo.
	public static void main(String[] args) throws IOException, URISyntaxException {		
		SceneDataModule sceneDataModule = new SceneDataModule(ImageIO.read(Resources.getResource("Scene/Scene.jpg")));
		RenderEngine renderEngine = new RenderEngine(null, sceneDataModule);
		
		JFrame frame = new JFrame("Organ Donation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(renderEngine.getPanel());
		frame.setSize(1450, 850);
		frame.setVisible(true);
		
		renderEngine.startRendering();
		renderEngine.stopRendering();
	}
	
	public JPanel getPanel() {
		return _canvas;
	}

	@Override
	public void startRendering() {
		_isRendering = true;
		_renderThread.start();
	}

	@Override
	public void stopRendering() {
		_isRendering = false;
		_renderThread.interrupt();
	}
	
	public void render(Graphics g) {
		g.fillRect(0, 0, 100, 100);
		_sceneRenderEngine.render(g);
		//_spriteRenderEngine.render(g);
	}
	
	class RenderThread implements Runnable {

		@Override
		public void run() {
			while (_isRendering) {
				_canvas.repaint();
			}
		}
		
	}
}
