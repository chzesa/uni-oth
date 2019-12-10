package oht.chess.shared;

import java.util.Random;

public enum Role {
	Base,
	Kineticist,
	Fey
	;

	private static final Role[] ROLES = Role.values();

	public static Role rand() {
		return ROLES[new Random().nextInt(ROLES.length)];
	}
}