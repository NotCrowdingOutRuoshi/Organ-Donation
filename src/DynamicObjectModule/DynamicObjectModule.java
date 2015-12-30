package DynamicObjectModule;

import java.awt.Point;
import java.util.ArrayList;

import DynamicObjectModule.Entities.Character;
import DynamicObjectModule.Entities.Item;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Entities.Character.DIRECTIONS;
import TCP.Client.TCPClient;

public class DynamicObjectModule {
	private ArrayList<Item> _items;
	private ArrayList<Character> _characters;
	private TCPClient _tcpClientModule;

	public DynamicObjectModule(TCPClient tcpClientModule) {
		assert (tcpClientModule != null);

		_items = new ArrayList<Item>();
		_characters = new ArrayList<Character>();
		_tcpClientModule = tcpClientModule;
	}

	public void addItem(String name, int index, boolean shared, int x, int y) {
		assert (name != null && !name.isEmpty());
		assert (index >= 0);

		for (Item item : _items) {
			assert (index != item.getId());
		}

		Item item = new Item(name, index, shared, x, y);
		_items.add(item);
	}

	public void addVirtualCharacter(int clientNumber) {
		assert (clientNumber >= 0);
		assert (findVirtualCharacter(clientNumber) == null);

		for (Character character : _characters) {
			assert (clientNumber != character.getId());
		}

		Character character = new Character(clientNumber, Character.DEFAULT_X, Character.DEFAULT_Y, Character.DEFAULT_DIRECTION, Character.DEFAULT_SPEED);
		_characters.add(character);
	}

	public Item findItem(int index) {
		assert (index >= 0);

		for (Item item : _items) {
			if (item.getId() == index) {
				return item;
			}
		}

		return null;
	}

	public Character findVirtualCharacter(int id) {
		for (Character character : _characters) {
			if (character.getId() == id) {
				return character;
			}
		}

		return null;
	}

	public Sprite[] getAllDynamicObjects() {
		Character[] characters = new Character[_characters.size()];
		_characters.toArray(characters);

		Item[] items = new Item[_items.size()];
		_items.toArray(items);

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		sprites.addAll(_characters);
		sprites.addAll(_items);

		Sprite[] result = new Sprite[sprites.size()];
		sprites.toArray(result);
		
		return result;
	}

	public Point getVirtualCharacterPosition(int clientNumber) {
		Character character = findVirtualCharacter(clientNumber);

		assert (character != null);

		return new Point(character.getX(), character.getY());
	}

	public boolean keyGETPressed(int id) {
		Character character = findVirtualCharacter(id);
		
		assert (character != null);
		
		for (Item item : _items) {
			if (item.getX() == character.getX() && item.getY() == character.getY()) {
				// Temporary comment out.
				//_tcpClientModule.inputMoves(MoveCodes.GET);
				return true;
			}
		}
		
		return false;
	}

	public void updateItem(int index, boolean shared, int ownerId, int x, int y) {
		Item item = findItem(index);
		Character owner = findVirtualCharacter(ownerId); 

		assert (item != null);
		assert owner != null || ownerId == Item.EMPTY_OWNER;

		item.setShared(shared);		
		item.setOwner(ownerId);
		
		item.setX(x);
		item.setY(y);
	}

	public void updateVirtualCharacter(int clientNumber, DIRECTIONS direction, int speed, int x, int y) {
		Character character = findVirtualCharacter(clientNumber);

		assert (character != null);

		character.setDirection(direction);
		character.setSpeed(speed);
		character.setX(x);
		character.setY(y);
	}
}
