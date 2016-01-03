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
		Organ heart = new Organ("heart",100);
		Organ liver = new Organ("liver",90);
		Organ lung = new Organ("lung",80);
		Organ pancreas = new Organ("pancreas",70);
		Organ kidney = new Organ("kidney",60);
		Organ small = new Organ("small intestine",50);
		Organ large = new Organ("large intestine",50);
		_organs.add(heart);
		_organs.add(liver);
		_organs.add(lung);
		_organs.add(pancreas);
		_organs.add(kidney);
		_organs.add(small);
		_organs.add(large);
	}
	
	public void addOrgan(Organ organ){
		_organs.add(organ);
	}
	
	public void remove(Organ organ){
		_organs.remove(organ);
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
	
	
	
	public ArrayList<Organ> getOrgans(){
		return _organs;
	}
	
	public void decreaseOrganHp(int number){
		if(_organs.size()>0){
			int randomnum = (int) (Math.random()*(_organs.size()-1));
//			System.out.println(randomnum);
			Organ decreasecorgan = _organs.get(randomnum);
			if(decreasecorgan.getHP()>0){
				decreasecorgan.setHP(decreasecorgan.getHP()-number);
			}
			else{
				System.out.println("---"+decreasecorgan.getName()+decreasecorgan.getHP());
				_organs.remove(decreasecorgan);
			}
		}
		
	}
	
	public Organ StealedOrgan(){
		int randomnum = (int) (Math.random()*_organs.size());

		Organ stealedorgan = _organs.get(randomnum);
		return stealedorgan;

	}
	
	public void StealOrganIncressHp(Organ o){
		for(Organ organ : _organs){
			if(organ.getName().equals(o.getName())){
				organ.setHP(organ.getHP()+o.getHP());
				o.setHP(0);
			}
		}
		getHealth();
	}
	
	public int getHealth(){
		_energy = 0;
		for(int i=0;i<_organs.size();i++){
			_energy += _organs.get(i).getHP();
		}
		return _energy;
	}
}

