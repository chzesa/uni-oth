package oht.chess;

import java.util.ArrayList;
import oht.chess.IAbility;
import oht.chess.TargetSet;
import oht.chess.Tcoord;
import java.util.List;

class AbilityTargeter
{
	IAbility _abty;
	Actor _usr;
	GameState _state;

	ArrayList<TargetSet> _sets = new ArrayList<>();
	TargetSet _wSet;

	AbilityTargeter(IAbility ability, Actor user, GameState state, TargetSet initialSet)
	{
		_abty = ability;
		_usr = user;
		_state = state;
		_wSet = initialSet;
		_sets.add(_wSet);
	}

	void append(TargetSet nextSet)
	{
		_wSet = nextSet;
		_sets.add(_wSet);
	}

	TargetSet current() { return _wSet; }
	boolean isComplete() { return _wSet.isComplete() && _abty.isComplete(this); }
	int numSets() { return _sets.size(); }
	List<TargetSet> sets() { return _sets; }
	TargetSet set(int i)
	{
		if (i < 0 || i >= _sets.size()) { return null; }
		return _sets.get(i);
	}

	Actor user() { return _usr; }
	GameState state() { return _state; }
}