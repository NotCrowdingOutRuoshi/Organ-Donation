package CentralizedDataCenter.Entities;

public class Organ extends Sprite{

	private int _hp;
	private String _name;
	public Organ(int id,String name,int hp){
		_id = id;
		_name = name;
		_hp = hp;
	}

	public int getHP(){
		return _hp;
	}

	
}
