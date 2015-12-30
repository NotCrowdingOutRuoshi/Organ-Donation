package CDC.Entities;

import java.util.ArrayList;

import Common.Direction;

public class Player extends Sprite{

	private int _speed;
	private Direction _dir;
	private StateType _state;
	private ArrayList<Organ> _organs;
	private int _energy;
	
	public Player(){
		_speed = 0;
		_dir = Direction.RIGHT;
		_state = StateType.IDLE;
		_energy = 500;
		_organs = new ArrayList<Organ>();
	}
	
	public void addOrgan(Organ organ){
		_organs.add(organ);
	}
	
	public void setDir(Direction dir){
		_dir = dir;
	}
	
	public Direction getDir(){
		return _dir;
	}
	
	public void setState(StateType state){
		_state = state;
	}
	
	public StateType getState(){
		return _state;
	}
	
	public void setSpeed(int speed){
		_speed = speed;
	}
	
	public int getSpeed(){
		return _speed;
	}
	
	public void setEnergy(int energy){
		_energy = energy;
	}
	
	public int getEnergy(){
		return _energy;
	}
}

