package Entity;

import Enum.SpriteType;

public class Item extends Sprite {

	private int itemIndex;
	private Player owner = null;

	public Item(int itemId,int itemIndex,int locationX,int locationY,Player ownPlayer) {
		id = itemId;
		this.itemIndex = itemIndex;
		x = locationX;
		y = locationY;
		owner = ownPlayer;
		
	}
	
	public void setOwner(Player player) {
		owner = player;
	}
	
	public boolean getIsShared() {
		if(owner == null) {
			return true;
		}
		return false;
	}
	
	public int getOwnerId() {
		if(getIsShared()) {
			return -1;
		}
		return owner.getId();
	}
	
	@Override
	public SpriteType getType() {
		// TODO Auto-generated method stub
		return SpriteType.ITEM;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 
				getType().toString() + " " + 
				String.valueOf(id) + " " + 
				String.valueOf(itemIndex) + " " + 
				String.valueOf(x) + " " +
				String.valueOf(y) + " " + 
				String.valueOf(getIsShared())+ " "+
				String.valueOf(getOwnerId());
	}
	

}
