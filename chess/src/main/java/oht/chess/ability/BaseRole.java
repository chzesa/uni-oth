package oht.chess.ability;

import oht.chess.Actor;

public class BaseRole extends AbilitySet
{
	public BaseRole(Actor caster)
	{
		super(caster);
		_abts.add(new Move(caster));
		_abts.add(new Attack(caster));
	}
}