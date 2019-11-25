package oht.chess.ability;

import oht.chess.unit.IActor;

class FeyRole extends BaseRole {
	FeyRole() {
		super(Role.Fey);
		abilitys.add(new Charm());
	}
}