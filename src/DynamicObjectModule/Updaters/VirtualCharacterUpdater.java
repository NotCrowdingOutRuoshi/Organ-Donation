package DynamicObjectModule.Updaters;

import java.util.HashMap;
import java.util.Map;

import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Entities.VirtualOrgan;
import Libraries.JSON.JSONArray;
import Libraries.JSON.JSONObject;

public class VirtualCharacterUpdater extends SpriteUpdater<VirtualCharacter> {
	private String _serverCurrentState;

	private static final Map<String, String> _organNameMap;

	static {
		_organNameMap = new HashMap<String, String>();
		_organNameMap.put("heart", "Heart");
		_organNameMap.put("liver", "Liver");
		_organNameMap.put("lung", "Lung");
		_organNameMap.put("pancreas", "Pancreas");
		_organNameMap.put("kidney", "Kidney");
		_organNameMap.put("small intestine", "SmallIntestine");
		_organNameMap.put("large intestine", "LargeIntestine");
	};

	public VirtualCharacterUpdater(VirtualCharacter sprite) {
		super(sprite);
		_serverCurrentState = "";
	}

	@Override
	public void update(JSONObject data) {
		characterAssertValidation(data);

		_sprite.setX(Integer.parseInt(data.get("x").toString()));
		_sprite.setY(Integer.parseInt(data.get("y").toString()));
		_sprite.setDirection(Integer.parseInt(data.get("dir").toString()));
		
		String newState = data.get("state").toString();
		if (!_serverCurrentState.equals(newState) && !_sprite.getState().equals(newState)) {
			_sprite.setState(data.get("state").toString());
			_serverCurrentState = newState;
		}
		
		_sprite.setSpeed(Integer.parseInt(data.get("speed").toString()));
		_sprite.setEnergy(Integer.parseInt(data.get("energy").toString()));
		updateOrgans(data.getJSONArray("organs"));
	}

	private void updateOrgans(JSONArray data) {
		for (Object organ : data) {
			JSONObject newOrganData = new JSONObject(organ.toString());
			updateOrgan(newOrganData);
		}
	}

	private void updateOrgan(JSONObject data) {
		organAssertValidation(data);

		String organName = data.get("name").toString();

		VirtualOrgan organ = _sprite.findOrgan(organName);
		organ.setHealth(Integer.parseInt(data.get("HP").toString()));
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
		assert _organNameMap.containsKey(organ.get("name").toString());
	}
}
