package RenderEngine;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import Common.Interfaces.IRenderEngine;
import DynamicObjectModule.DynamicObjectModule;
import RenderEngine.Scene.SceneRenderEngine;
import RenderEngine.Sprite.SpriteRenderEngine;
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
		_canvas.setBackground(Color.BLACK);
		_canvas.setDoubleBuffered(true);
		_isRendering = false;
		_renderThread = new Thread(new RenderThread());
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
