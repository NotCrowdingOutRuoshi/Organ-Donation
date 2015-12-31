package DynamicObjectModule.Entities;

import java.awt.Graphics;

public class VirtualItem extends Sprite {
	public final static int EMPTY_OWNER = -1;

	private int _owner;
	private String _name;
	private boolean _shared;

	public VirtualItem(String name, int id, boolean shared, int x, int y) {
		super(id, x, y);

		assert (name != null && !name.isEmpty());

		_name = name;
		_owner = EMPTY_OWNER;
		_shared = shared;
	}
	
	@Override
	protected void loadAnimations() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {

	}

	public int getOwner() {
		return _owner;
	}

	public void setOwner(int owner) {
		assert (owner >= 0 || owner == EMPTY_OWNER);
		_owner = owner;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		assert (name != null && !name.isEmpty());
		_name = name;
	}

	public boolean isOwned() {
		return _owner != EMPTY_OWNER;
	}

	public boolean isShared() {
		return _shared;
	}

	public void setShared(boolean isShared) {
		_shared = isShared;
	}

	@Override
	protected void initPackageToDirection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initPackageToState() {
		// TODO Auto-generated method stub
		
	}
}
