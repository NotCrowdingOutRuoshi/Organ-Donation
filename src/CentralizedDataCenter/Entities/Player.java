package CentralizedDataCenter.Entities;

import java.util.ArrayList;

import Common.Constants;
import Common.StateType;

public class Player extends Sprite{

	private int _speed;
	private int _dir;
	private String _state;
	private ArrayList<Organ> _organs;
	private int _energy;
	
	public Player(){
		_speed = 5;
		_dir = Constants.ACTIONCODE_EAST;
		_state = StateType.IDLE;
		_energy = 0;
		_organs = new ArrayList<Organ>();
	}
	
	public void addOrgan(Organ organ){
		_organs.add(organ);
	}
	
	public void setDir(int dir){
		_dir = dir;
	}
	
	public int getDir(){
		return _dir;
	}
	
	public void setState(String state){
		_state = state;
	}
	
	public String getState(){
		return _state;
	}
	
	public void setSpeed(int speed){
		_speed = speed;
	}
	
	public int getSpeed(){
		return _speed;
	}
	
	public void setEnergy(int energy){
		
		
	}
	
	public int getEnergy(){
		_energy = 0;
		for(int i=0;i<_organs.size();i++){
			_energy += _organs.get(i).getHP();
		}
		return _energy;
	}
	
	public int getRandomOrganId(){
		int id = (int) (Math.random()*5+1);
		return id;
	}
}

