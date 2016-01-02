package DynamicObjectModule.Updaters;

import DynamicObjectModule.Entities.Sprite;
import Libraries.JSON.JSONObject;

public abstract class SpriteUpdater<SpriteType extends Sprite> {
	protected SpriteType _sprite;
	
	protected SpriteUpdater(SpriteType sprite) {
		_sprite = sprite;
	}
	
	public int getSpriteId() {
		return _sprite.getId();
	}
	
	public abstract void update(JSONObject data);
}
