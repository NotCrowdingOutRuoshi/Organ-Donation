package RenderEngine.Sprite;

import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Sprite;

public class SpriteRenderEngine {
	private DynamicObjectModule _dynamicObjectModule;

	public SpriteRenderEngine(DynamicObjectModule dynamicObjectModule) {
		assert (dynamicObjectModule != null);
		_dynamicObjectModule = dynamicObjectModule;
	}

	public void renderSprites() {
		Sprite[] entities = _dynamicObjectModule.getAllDynamicObjects();
		
		for (Sprite sprite : entities) {
			//sprite.draw();
		}
	}
}
