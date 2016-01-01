package Net.UDP.Test.Stub.Entities;
import java.util.ArrayList;

import Common.Constants;
import Common.StateType;

public class PlayerStub extends SpriteStub{

	private int _speed;
	private int _dir;
	private String _state;
	private ArrayList<OrganStub> _organs;
	private int _energy;
	
	public PlayerStub(){
		_speed = 5;
		_dir = Constants.ACTIONCODE_EAST;
		_state = StateType.IDLE;
		_energy = 0;
		_organs = new ArrayList<OrganStub>();
		OrganStub heart = new OrganStub("heart",100);
		OrganStub liver = new OrganStub("liver",100);
		OrganStub lung = new OrganStub("lung",100);
		OrganStub pancreas = new OrganStub("pancreas",100);
		OrganStub kidney = new OrganStub("kidney",100);
		OrganStub small = new OrganStub("small intestine",100);
		OrganStub large = new OrganStub("large intestine",100);
		_organs.add(heart);
		_organs.add(liver);
		_organs.add(lung);
		_organs.add(pancreas);
		_organs.add(kidney);
		_organs.add(small);
		_organs.add(large);
	}
	
	public void addOrgan(OrganStub organ){
		_organs.add(organ);
	}
	
	public void remove(OrganStub organ){
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
	
	public int getEnergy(){
		_energy = 0;
		return _energy;
	}
	
	public ArrayList<OrganStub> getOrgans(){
		return _organs;
	}
	
	public void decreaseOrganHp(int number){
		if(_organs.size()>0){
			int randomnum = (int) (Math.random()*(_organs.size()-1));
			System.out.println(randomnum);
			OrganStub decreasecorgan = _organs.get(randomnum);
			if(decreasecorgan.getHP()>0){
				decreasecorgan.setHP(decreasecorgan.getHP()-number);
			}
			else{
				System.out.println("---"+decreasecorgan.getName()+decreasecorgan.getHP());
				_organs.remove(decreasecorgan);
			}
		}
		
	}
	
	public OrganStub StealedOrgan(){
		int randomnum = (int) (Math.random()*_organs.size());

		OrganStub stealedorgan = _organs.get(randomnum);
		return stealedorgan;

	}
}


