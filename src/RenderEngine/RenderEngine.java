package RenderEngine;

import java.awt.Graphics;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Common.Direction;
import Common.StateType;
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
		assert (dom != null);
		assert (sdm != null);

		_spriteRenderEngine = new SpriteRenderEngine(dom);
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
	
	// For scene render engine demo only.
//	public static void main(String[] args) throws IOException, URISyntaxException {		
//		SceneDataModule sdm = new SceneDataModule(ImageIO.read(Resources.getResourceStream("Scene/Scene.jpg")));
//		DynamicObjectModule dom = new DynamicObjectModule();
//		dom.addVirtualCharacter(0, null);
//		dom.findSprite(0).setState(StateType.EXHAUST);
//		dom.findSprite(0).setDirection(Direction.UP);
//		
//		RenderEngine renderEngine = new RenderEngine(dom, sdm);
//		
//		JFrame frame = new JFrame("Organ Donation");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().add(renderEngine.getPanel());
//		frame.setSize(1450, 850);
//		frame.setVisible(true);
//		
//		renderEngine.startRendering();
//		//renderEngine.stopRendering();
//	}

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

	private void render(Graphics g) {
		g.fillRect(0, 0, 100, 100);
		_sceneRenderEngine.render(g);
		_spriteRenderEngine.render(g);
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
