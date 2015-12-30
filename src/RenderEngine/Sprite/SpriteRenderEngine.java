package RenderEngine.Sprite;

import java.awt.Graphics;

import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;

public class SpriteRenderEngine {
	private DynamicObjectModule _dynamicObjectModule;

	public SpriteRenderEngine(DynamicObjectModule dynamicObjectModule) {
		assert (dynamicObjectModule != null);
		
		_dynamicObjectModule = dynamicObjectModule;
	}

	public void render(Graphics g) {		
		Sprite[] entities = _dynamicObjectModule.getAllDynamicObjects();
		
		for (Sprite sprite : entities) {
			sprite.draw(g);
		}
	}
}
