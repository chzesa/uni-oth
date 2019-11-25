package oht.chess.ability;
import java.util.ArrayList;
import oht.chess.unit.Actor;

public class AbilitySet {
	Actor caster;
	ArrayList<IAbility> abilitys = new ArrayList<>();

	AbilitySet(Actor caster) {
		this.caster = caster;
	}

	public IAbility get(int i) {
		if (i < 0 || i >= this.abilitys.size()) {
			return null;
		}
		return this.abilitys.get(i);
	}

	public int numAbilities() {
		return this.abilitys.size();
	}
}