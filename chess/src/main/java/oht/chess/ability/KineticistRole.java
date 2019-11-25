package oht.chess.ability;

class KineticistRole extends BaseRole {
	KineticistRole() {
		super(Role.Kineticist);
		abilitys.add(new Telekinesis());
	}
}