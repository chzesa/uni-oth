package oht.chess.ability;

import oht.chess.shared.IAbilitySet;
import oht.chess.shared.Role;

/**
 * Luokka joka tarjoaa keinon instansoida roolien kykypaketin.
 */
public class RoleFactory {
	public static IAbilitySet make(Role role) {
		switch (role) {
			case Base: return new BaseRole();
			case Fey: return new FeyRole();
			case Kineticist: return new KineticistRole();
		}

		throw new UnsupportedOperationException();
	}
}