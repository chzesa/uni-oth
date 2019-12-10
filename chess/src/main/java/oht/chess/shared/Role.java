package oht.chess.shared;

import java.util.Random;

/**
 * Lista pelin hahmoluokista.
 * Muuttaessa listaa tulee päivittää ability paketin RoleFactory.
 */

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