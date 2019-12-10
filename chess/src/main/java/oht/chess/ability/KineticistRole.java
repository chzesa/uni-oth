package oht.chess.ability;

import oht.chess.shared.Role;

class KineticistRole extends BaseRole {
	KineticistRole() {
		super(Role.Kineticist);
		abilitys.add(new Telekinesis());
	}
}