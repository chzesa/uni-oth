package oht.chess;

class BaseRole extends AbilitySet
{
	BaseRole(Actor caster)
	{
		super(caster);
		_abts.add(new Move(caster));
		_abts.add(new Attack(caster));
	}
}