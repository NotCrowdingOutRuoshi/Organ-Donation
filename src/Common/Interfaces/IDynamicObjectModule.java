package Common.Interfaces;

import DynamicObjectModule.Entities.Sprite;
import Libraries.JSON.JSONObject;

public interface IDynamicObjectModule {
	public void addSprite(Sprite sprite);
	public void addVirtualCharacter(int clientNumber, JSONObject data);
	public Sprite findSprite(int id);
	public Sprite[] getAllDynamicObjects();
	public void updateVirtualCharacter(int index, JSONObject data);
	public int getCountDown();
	public void setCountDown(int number);
}
