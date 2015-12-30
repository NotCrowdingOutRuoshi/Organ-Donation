package Common.Interfaces;

import org.json.JSONObject;

import DynamicObjectModule.Entities.Sprite;

public interface DynamicObjectModuleInterface {
	public void addItem(String name, int index, boolean shared, int x, int y);
	public void addVirtualCharacter(int clientNumber, int x, int y, String direction, int speed);
	public Sprite findSprite(int id);
	public Sprite[] getAllDynamicObjects();
	public void updateSprite(int index, JSONObject data);
	public int getCountDown();
	public void setCountDown(int number);
}
