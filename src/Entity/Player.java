package Entity;

import java.util.Vector;

import Enum.SpriteType;

public class Player extends Sprite {
	
	private int direction;
	private int velocity;
	private String name;
	private Vector<Item> itmes;

	public Player(int clientId,String username,int locationX,int locationY) {
		id = clientId;
		name = username;
		locationX = x;
		locationY = y;
	}
	
	public int getDirection() {
		return direction;
	}

	public int getVelocity() {
		return velocity;
	}

	public String getName() {
		return name;
	}
	
	public Vector<Item> getOwnItems() {
		return itmes;
	}

	@Override
	public SpriteType getType() {
		// TODO Auto-generated method stub
		return SpriteType.PLAYER;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return 
				getType().toString() + " " + 
				String.valueOf(id) + " " + 
				name + " " + 
				String.valueOf(x) + " " +
				String.valueOf(y) + " " + 
				String.valueOf(direction) + " " +
				String.valueOf(velocity);
	}

}
