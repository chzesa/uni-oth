package oht.chess.ability;

import oht.chess.shared.Role;

class FeyRole extends BaseRole {
	FeyRole() {
		super(Role.Fey);
		abilitys.add(new Charm());
	}
}