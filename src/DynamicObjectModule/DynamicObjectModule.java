package DynamicObjectModule;

import java.util.ArrayList;

import Common.Interfaces.IDynamicObjectModule;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Entities.VirtualOrgan;
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

	// For test update only.
//	public static void main(String[] args) {
//		DynamicObjectModule dom = new DynamicObjectModule();
//		dom.addVirtualCharacter(1, new JSONObject("{\"organs\":[{\"name\":\"heart\",\"HP\":100},{\"name\":\"liver\",\"HP\":100},{\"name\":\"lung\",\"HP\":100},{\"name\":\"pancreas\",\"HP\":100},{\"name\":\"kidney\",\"HP\":100},{\"name\":\"small intestine\",\"HP\":100},{\"name\":\"large intestine\",\"HP\":100}],\"x\":0,\"health\":500,\"y\":0,\"state\":\"Idle state\",\"id\":1,\"dir\":39,\"speed\":5,\"energy\":700}"));
//		Sprite[] sprites = dom.getAllDynamicObjects();
//		System.out.println("Hi");
//	}

	@Override
	public void addSprite(Sprite sprite) {
		assert (findSprite(sprite.getId()) == null);

		_sprites.add(sprite);
	}

	@Override
	public void addVirtualCharacter(int clientNumber, JSONObject data) {
		assert (clientNumber >= 0);
		assert (findSprite(clientNumber) == null);

		for (Sprite character : _sprites) {
			assert (clientNumber != character.getId());
		}

		VirtualCharacter character = new VirtualCharacter(clientNumber, Sprite.DEFAULT_X, Sprite.DEFAULT_Y,
				VirtualCharacter.DEFAULT_DIRECTION, VirtualCharacter.DEFAULT_SPEED);
		VirtualCharacterUpdater updater = new VirtualCharacterUpdater(character);

		updater.update(data);

		_sprites.add(character);
		_updaters.add(updater);
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
	public void updateVirtualCharacter(int index, JSONObject data) {
		SpriteUpdater<?> updater = findUpdater(index);

		if (updater == null) {
			addVirtualCharacter(index, data);
		} else {
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
