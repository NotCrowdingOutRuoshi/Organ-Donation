package RenderEngine.Scene;

import java.awt.Graphics;
import java.awt.Image;

import SceneDataModule.SceneDataModule;

public class SceneRenderEngine {
	private SceneDataModule _dataModule;
	
	public SceneRenderEngine(SceneDataModule dataModule) {
		assert (dataModule != null);
		
		_dataModule = dataModule;
	}

	public void render(Graphics g) {
		Image background = _dataModule.getImage();
		g.drawImage(background, 0, 0, null);
	}

}
