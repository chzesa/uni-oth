package oht.chess.ability;

import oht.chess.Actor;

public class FeyRole extends BaseRole
{
	public FeyRole(Actor caster)
	{
		super(caster);
		_abts.add(new Charm(caster));
	}
}