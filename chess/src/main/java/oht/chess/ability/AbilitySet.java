package oht.chess.ability;
import java.util.ArrayList;
import oht.chess.unit.Actor;

public class AbilitySet
{
	Actor _caster;
	ArrayList<IAbility> _abts = new ArrayList<>();

	AbilitySet(Actor caster) { _caster = caster; }

	public IAbility get(int i)
	{
		if (i < 0 || i >= _abts.size()) { return null; }
		return _abts.get(i);
	}

	public int numAbilities() { return _abts.size(); }
}