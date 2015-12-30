package DynamicObjectModule.Updaters;

import org.json.JSONObject;

import DynamicObjectModule.Entities.Sprite;

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
