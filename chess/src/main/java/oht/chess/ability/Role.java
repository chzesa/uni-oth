package oht.chess.ability;

import java.util.Random;

public enum Role {
	Base,
	Kineticist,
	Fey
	;

	private static final Role[] roles = Role.values();

	public static Role rand() {
		return roles[new Random().nextInt(roles.length)];
	}
}