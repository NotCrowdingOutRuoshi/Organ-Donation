package Entity;

import Enum.SpriteType;

public abstract class Sprite {
	protected int id;
	protected int x;
	protected int y;

	public int getId() {
		return id;
	}

	public int getLocationX() {
		return x;
	}

	public int getLocationY() {
		return y;
	}

	public abstract SpriteType getType();

	@Override
	public abstract String toString();
	

}
