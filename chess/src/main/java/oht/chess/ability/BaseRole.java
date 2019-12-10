package oht.chess.ability;

import oht.chess.shared.Role;

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