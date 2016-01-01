package DynamicObjectModule;

import java.util.ArrayList;

import Common.Interfaces.IDynamicObjectModule;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Entities.VirtualItem;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Updaters.VirtualCharacterUpdater;
import Libraries.JSON.JSONObject;
import DynamicObjectModule.Updaters.SpriteUpdater;

public class DynamicObjectModule implements IDynamicObjectModule {
	private int _countDown;
	private ArrayList<SpriteUpdater<?>> _updaters;
	private ArrayList<Sprite> _sprites;

	public DynamicObjectModule() {
		
		_countDown = 0;

		_updaters = new ArrayList<SpriteUpdater<?>>();
		_sprites = new ArrayList<Sprite>();
	}

	@Override
	public void addItem(String name, int index, boolean shared, int x, int y) {
		assert (name != null && !name.isEmpty());
		assert (index >= 0);

		for (Sprite item : _sprites) {
			assert (index != item.getId());
		}

		VirtualItem virtualItem = new VirtualItem(name, index, shared, x, y);
		_sprites.add(virtualItem);
	}

	@Override
	public void addVirtualCharacter(int clientNumber, JSONObject data) {
		assert (clientNumber >= 0);
		assert (findSprite(clientNumber) == null);

		for (Sprite character : _sprites) {
			assert (clientNumber != character.getId());
		}

		VirtualCharacter virtualCharacter = new VirtualCharacter(clientNumber, Sprite.DEFAULT_X, Sprite.DEFAULT_Y, VirtualCharacter.DEFAULT_DIRECTION, VirtualCharacter.DEFAULT_SPEED);
		_sprites.add(virtualCharacter);
		_updaters.add(new VirtualCharacterUpdater(virtualCharacter));
	}
	
	@Override
	public Sprite findSprite(int id) {
		for (Sprite sprite : _sprites) {
			if (sprite.getId() == id) {
				return sprite;
			}
		}
		
		return null;
	}

	@Override
	public Sprite[] getAllDynamicObjects() {
		Sprite[] result = new Sprite[_sprites.size()];
		_sprites.toArray(result);
		
		return result;
	}
	
	@Override
	public int getCountDown() {
		return _countDown;
	}
	
	@Override
	public void setCountDown(int number) {
		assert (number >= 0);
		
		_countDown = number;
	}

	@Override
	public void updateSprite(int index, JSONObject data) {
		SpriteUpdater<?> updater = findUpdater(index);

		if(updater == null) {
			addVirtualCharacter(index, data);
		}
		else {
			updater.update(data);
		}
	}
	
	private SpriteUpdater<?> findUpdater(int id) {
		for (SpriteUpdater<?> updater : _updaters) {
			if (updater.getSpriteId() == id) {
				return updater;
			}
		}
		
		return null;
	}
}
