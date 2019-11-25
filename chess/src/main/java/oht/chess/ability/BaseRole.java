package oht.chess.ability;

import oht.chess.unit.Actor;

public class BaseRole extends AbilitySet {
	public BaseRole(Actor caster) {
		super(caster);
		abilitys.add(new Move(caster));
		abilitys.add(new Attack(caster));
	}
}