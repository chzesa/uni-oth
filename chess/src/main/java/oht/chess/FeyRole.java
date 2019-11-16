package oht.chess;

class FeyRole extends BaseRole
{
	FeyRole(Actor caster)
	{
		super(caster);
		_abts.add(new Charm(caster));
	}
}