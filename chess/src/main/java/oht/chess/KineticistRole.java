package oht.chess;

class KineticistRole extends BaseRole
{
	KineticistRole(Actor caster)
	{
		super(caster);
		_abts.add(new Telekinesis(caster));
	}
}