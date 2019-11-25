package oht.chess.ability;
import java.util.ArrayList;

class AbilitySet implements IAbilitySet {
	Role role;
	ArrayList<IAbility> abilitys = new ArrayList<>();

	protected AbilitySet(Role role) {
		this.role = role;
	}

	public IAbility getAbility(int i) {
		if (i < 0 || i >= this.abilitys.size()) {
			return null;
		}
		return this.abilitys.get(i);
	}

	public int numAbilities() {
		return this.abilitys.size();
	}

	@Override
	public Role role() {
		return role;
	}
}