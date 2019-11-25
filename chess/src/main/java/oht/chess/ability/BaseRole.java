package oht.chess.ability;

import oht.chess.unit.IActor;

class BaseRole extends AbilitySet {
	BaseRole() {
		super(Role.Base);
		abilitys.add(new Move());
		abilitys.add(new Attack());
	}

	BaseRole(Role role) {
		super(role);
		abilitys.add(new Move());
		abilitys.add(new Attack());
	}
}