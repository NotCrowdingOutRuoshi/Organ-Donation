package Entity;

import Enum.SpriteType;

public class Item extends Sprite {

	private Player owner = null;

	public void setOwner(Player player) {
		owner = player;
	}
	
	public boolean getIsShared() {
		if(owner == null) {
			return true;
		}
		return false;
	}
	@Override
	SpriteType getType() {
		// TODO Auto-generated method stub
		return SpriteType.ITEM;
	}
	

}
