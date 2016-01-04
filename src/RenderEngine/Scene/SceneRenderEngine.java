package RenderEngine.Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import Common.Constants;
import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;
import SceneDataModule.SceneDataModule;
import UserInterfaceModule.GameManager;

public class SceneRenderEngine {
	private SceneDataModule _dataModule;
	
	public SceneRenderEngine(SceneDataModule dataModule) {
		assert (dataModule != null);
		
		_dataModule = dataModule;
	}

	public void render(Graphics g) {
		Image background = _dataModule.getImage();
		int playerId = GameManager.getInstance().getClientId();
		DynamicObjectModule dom = GameManager.getInstance().getDOM();
		
		Sprite player = dom.findSprite(playerId);
		
		g.clearRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		g.drawImage(background, 250 - player.getX(), 250 - player.getY(), null);
	}

}
