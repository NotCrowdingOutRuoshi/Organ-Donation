package DynamicObjectModule.Transitions.States.VirtualCharacter;

import Common.StateType;
import DynamicObjectModule.Entities.VirtualCharacter;
import DynamicObjectModule.Transitions.States.WalkState;

public class CharacterWalkState extends WalkState<VirtualCharacter> {

	public CharacterWalkState(VirtualCharacter sprite) {
		super(sprite);

		_returnState = StateType.IDLE;
	}

	@Override
	public void execute() {
		super.execute();
		int currentFrame = _entity.getCurrentAnimation().getCurrentFrameCount();
		if (currentFrame == 1 || currentFrame == 6) {
			playSound("SoundEffects/walk.wav");
		}
		
		if (_entity.getCurrentAnimation().isAtFrameEnd()) {
			resetSoundFrame();
		}
	}
	
	@Override
	protected void setupAnimation() {
		_entity.setAnimation(getType(), _entity.getDirection());
		_entity.getCurrentAnimation().loop();
	}

}
