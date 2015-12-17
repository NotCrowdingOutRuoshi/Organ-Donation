package Entity;

import java.util.Vector;

import Enum.SpriteType;

public class Player extends Sprite {

	private int direction;
	private int velocity;
	private Vector<Item> itmes;

	public int getDirection() {
		return direction;
	}

	public int getVelocity() {
		return velocity;
	}

	public Vector<Item> getOwnItems() {
		return itmes;
	}

	@Override
	SpriteType getType() {
		// TODO Auto-generated method stub
		return SpriteType.PLAYER;
	}

}
