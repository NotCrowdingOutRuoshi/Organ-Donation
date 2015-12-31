package DynamicObjectModule.Transitions;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import Common.StateType;
import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Transitions.States.State;

public class FiniteStateMachine {
	protected Multimap<StateType, StateType> _transitionTable;
	protected Map<StateType, State> _stateEntityTranslationTable;
	protected Sprite _entity;
	private State _currentState;

	public FiniteStateMachine(Sprite owner) {
		assert owner != null;

		_entity = owner;
		_transitionTable = ArrayListMultimap.create();
		_stateEntityTranslationTable = new HashMap<StateType, State>();
	}

	public void addTransition(StateType from, StateType to) {
		assert (!_transitionTable.containsKey(from)
				|| (_transitionTable.containsKey(from) && _transitionTable.get(from) != to));

		_transitionTable.put(from, to);
	}

	public void removeTransition(StateType from, StateType to) {
		_transitionTable.remove(from, to);
	}

	public void updateTransition(StateType from, StateType originTo, StateType newTo) {
		assert ((_transitionTable.containsKey(from) && _transitionTable.get(from) == originTo));
		_transitionTable.put(from, newTo);
	}

	public void addStateTranslation(StateType type, State state) {
		assert (!_stateEntityTranslationTable.containsKey(type));

		_stateEntityTranslationTable.put(type, state);
	}

	public State removeStateTranslation(StateType type) {
		return _stateEntityTranslationTable.remove(type);
	}

	public void updateTranslation(StateType type, State newState) {
		assert (_stateEntityTranslationTable.containsKey(type));

		_stateEntityTranslationTable.put(type, newState);
	}

	public boolean setState(StateType stateType) {
		assert (stateType != null);

		if (!isTransitionValid(stateType)) {
			return false;
		}

		if (_currentState != null) {
			_currentState.exit();
		}

		assert (_stateEntityTranslationTable.containsKey(stateType));

		_currentState = _stateEntityTranslationTable.get(stateType);

		assert (_currentState != null);

		_currentState.enter();

		return true;
	}

	public void executeState() {
		assert (_currentState != null);

		_currentState.execute();
	}

	private boolean isTransitionValid(StateType stateType) {
		String oldStateType;
		
		if (_currentState == null) {
			oldStateType = null;
		}
		else {
			oldStateType = _currentState.getType();
		}

		if (oldStateType == null || _transitionTable.containsKey(oldStateType)) {
			for (StateType type : _transitionTable.values()) {
				if (type == stateType) {
					return true;
				}
			}
		}

		return false;
	}
}
