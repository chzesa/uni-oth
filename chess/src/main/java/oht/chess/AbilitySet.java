package oht.chess;
import java.util.ArrayList;

class AbilitySet
{
	Actor _caster;
	ArrayList<IAbility> _abts = new ArrayList<>();

	AbilitySet(Actor caster) { _caster = caster; }

	IAbility get(int i)
	{
		if (i < 0 || i >= _abts.size()) { return null; }
		return _abts.get(i);
	}

	int numAbilities() { return _abts.size(); }
}