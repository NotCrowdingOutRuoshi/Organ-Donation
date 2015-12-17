package Entity;

import Enum.SpriteType;

public abstract class Sprite {
	protected int id;
	protected String name;
	protected int x;
	protected int y;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLocationX() {
		return x;
	}

	public int getLocationY() {
		return y;
	}

	abstract SpriteType getType();

}
