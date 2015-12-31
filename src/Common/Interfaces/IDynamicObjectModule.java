package Common.Interfaces;

import DynamicObjectModule.Entities.Sprite;
import Libraries.JSON.JSONObject;

public interface IDynamicObjectModule {
	public void addItem(String name, int index, boolean shared, int x, int y);
	public void addVirtualCharacter(int clientNumber, JSONObject data);
	public Sprite findSprite(int id);
	public Sprite[] getAllDynamicObjects();
	public void updateSprite(int index, JSONObject data);
	public int getCountDown();
	public void setCountDown(int number);
}
