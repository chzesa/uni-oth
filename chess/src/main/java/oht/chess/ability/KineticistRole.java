package oht.chess.ability;

import oht.chess.unit.IActor;

class KineticistRole extends BaseRole {
	KineticistRole() {
		super(Role.Kineticist);
		abilitys.add(new Telekinesis());
	}
}