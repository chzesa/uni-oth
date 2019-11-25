package oht.chess.ability;

import oht.chess.unit.Actor;

public class FeyRole extends BaseRole {
	public FeyRole(Actor caster) {
		super(caster);
		abilitys.add(new Charm(caster));
	}
}