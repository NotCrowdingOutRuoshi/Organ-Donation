package DynamicObjectModule.Updaters;

import java.util.ArrayList;

import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Entities.VirtualOrgan;
import Libraries.JSON.JSONArray;
import Libraries.JSON.JSONObject;

public class VirtualCharacterUpdater extends SpriteUpdater<VirtualCharacter> {

	public VirtualCharacterUpdater(VirtualCharacter sprite) {
		super(sprite);
	}

	@Override
	public void update(JSONObject data) {
		characterAssertValidation(data);

		_sprite.setX(Integer.parseInt(data.get("x").toString()));
		_sprite.setY(Integer.parseInt(data.get("y").toString()));
		_sprite.setDirection(Integer.parseInt(data.get("dir").toString()));
		_sprite.setState(data.get("state").toString());
		_sprite.setSpeed(Integer.parseInt(data.get("speed").toString()));
		_sprite.setEnergy(Integer.parseInt(data.get("energy").toString()));
		updateOrgans(data.getJSONArray("organs"));
	}

	private void updateOrgans(JSONArray data) {
		ArrayList<VirtualOrgan> updatedOrgans = new ArrayList<VirtualOrgan>();
		
		for (Object organ : data) {
			JSONObject newOrganData = new JSONObject(organ.toString());
			updatedOrgans.add(createOrgan(newOrganData));
		}
		
		_sprite.updateOrganList(updatedOrgans);
	}
	
	private VirtualOrgan createOrgan(JSONObject data) {
		organAssertValidation(data);
		
		VirtualOrgan organ = new VirtualOrgan(data.get("name").toString(), _sprite); 
		organ.setHealth(Integer.parseInt(data.get("HP").toString()));
		
		return organ;
	}

	private void characterAssertValidation(JSONObject character) {
		assert character.has("x");
		assert character.has("y");
		assert character.has("dir");
		assert character.has("state");
		assert character.has("speed");
		assert character.has("energy");
		assert character.has("organs");
	}

	private void organAssertValidation(JSONObject organ) {
		assert organ.has("name");
		assert organ.has("HP");
	}
}
