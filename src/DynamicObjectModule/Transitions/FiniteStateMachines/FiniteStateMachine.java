package DynamicObjectModule.Transitions.FiniteStateMachines;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import DynamicObjectModule.Entities.Sprite;
import DynamicObjectModule.Transitions.States.State;

public class FiniteStateMachine<EntityType extends Sprite> {
	protected Multimap<String, String> _transitionTable;
	protected Map<String, State<?>> _stateEntityTranslationTable;
	protected EntityType _entity;
	private State<?> _currentState;

	public FiniteStateMachine(EntityType owner) {
		assert owner != null;

		_entity = owner;
		_transitionTable = ArrayListMultimap.create();
		_stateEntityTranslationTable = new HashMap<>();
	}

	public void addTransition(String from, String to) {
		_transitionTable.put(from, to);
	}

	public void removeTransition(String from, String to) {
		_transitionTable.remove(from, to);
	}

	public void updateTransition(String from, String originTo, String newTo) {
		if (_transitionTable.remove(from, originTo)) {
			_transitionTable.put(from, newTo);
		}
	}

	public void addStateTranslation(String type, State<?> state) {
		assert (!_stateEntityTranslationTable.containsKey(type));

		_stateEntityTranslationTable.put(type, state);
	}

	public State<?> removeStateTranslation(String type) {
		return _stateEntityTranslationTable.remove(type);
	}

	public void updateTranslation(String type, State<EntityType> newState) {
		assert (_stateEntityTranslationTable.containsKey(type));

		_stateEntityTranslationTable.put(type, newState);
	}

	public boolean setState(String stateType) {
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

	public State<?> getCurrentState() {
		return _currentState;
	}

	public void executeState() {
		assert (_currentState != null);

		_currentState.execute();
	}

	private boolean isTransitionValid(String stateType) {
		String currentStateType;

		if (_currentState == null) {
			currentStateType = null;
		} else {
			currentStateType = _currentState.getType();
		}

		if (currentStateType == null) {
			return true;
		}

		if (_transitionTable.containsKey(currentStateType)) {
			String[] s = new String[_transitionTable.get(currentStateType).size()];
			_transitionTable.get(currentStateType).toArray(s);

			for (String type : _transitionTable.get(currentStateType)) {
				if (type.equals(stateType)) {
					return true;
				}
			}
		}

		return false;
	}
}
