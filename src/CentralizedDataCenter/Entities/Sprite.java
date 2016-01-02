package CentralizedDataCenter.Entities;

public abstract class Sprite {
	protected int _id;
	protected int _x;
	protected int _y;
	protected int _energy;


	public Sprite() {
		_id = 0;
		_x = 0;
		_y = 0;
		_energy = 500;

	}

	public void setID(int id){
		_id = id;
	}
	
	public int getId(){
		return _id;
	}
	
	public int getX() {
		return _x;
	}

	public void setX(int x) {
		_x = x;
	}

	public int getY() {
		return _y;
	}

	public void setY(int y) {
		_y = y;
	}
	
	public int getEnergy() {
		return _energy;
	}

	public void setEnergy(int health) {
		_energy = health;
	}

}