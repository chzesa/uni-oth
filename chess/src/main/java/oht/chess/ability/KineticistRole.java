package oht.chess.ability;

import oht.chess.unit.Actor;

public class KineticistRole extends BaseRole {
	public KineticistRole(Actor caster) {
		super(caster);
		abilitys.add(new Telekinesis(caster));
	}
}