package oht.chess.ability;

class FeyRole extends BaseRole {
	FeyRole() {
		super(Role.Fey);
		abilitys.add(new Charm());
	}
}