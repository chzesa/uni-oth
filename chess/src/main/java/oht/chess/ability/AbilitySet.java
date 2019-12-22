package oht.chess.ability;
import oht.chess.shared.IAbilitySet;
import oht.chess.shared.IAbility;
import oht.chess.shared.Role;
import java.util.ArrayList;

/**
 * Referenssitoteutus IAbilitySet rajapinnalle
 */
class AbilitySet implements IAbilitySet {
	protected Role role;
	protected ArrayList<IAbility> abilitys = new ArrayList<>();

	protected AbilitySet(Role role) {
		this.role = role;
	}

	public IAbility getAbility(int i) {
		if (i < 0 || i >= this.abilitys.size()) {
			return null;
		}
		return this.abilitys.get(i);
	}

	@Override
	public IAbility getAbility(String s) {
		if (s == null) {
			return null;
		}

		s = s.trim();
		for (IAbility ability : abilitys) {
			if (ability.name().compareToIgnoreCase(s) == 0) {
				return ability;
			}
		}

		return null;
	}

	public int numAbilities() {
		return this.abilitys.size();
	}

	@Override
	public Role role() {
		return role;
	}
}