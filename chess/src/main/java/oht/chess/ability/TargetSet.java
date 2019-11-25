package oht.chess.ability;
import java.util.HashSet;
import java.util.Set;
import oht.chess.util.Tcoord;

public class TargetSet {
	Set<Tcoord> refSet;
	HashSet<Tcoord> wSet = new HashSet<>();
	int min;
	int max;
	boolean lock = false;

	TargetSet(Set<Tcoord> targetables, int min, int max) {
		// if targetables < min || targetables < max throw
		this.refSet = targetables;
		this.min = min;
		this.max = max;
	}

	TargetSet(Set<Tcoord> targetables, int num) {
		this.refSet = targetables;
		this.min = num;
		this.max = num;
	}

	public boolean add(Tcoord t) {
		if (this.wSet.size() < this.max && this.refSet.contains(t)) {
			return this.wSet.add(t);
		}
		return false;
	}

	public boolean remove(Tcoord t) {
		return this.wSet.remove(t);
	}

	public boolean toggle(Tcoord t) {
		if (this.wSet.contains(t)) {
			return remove(t);
		}
		return add(t);
	}

	public int minTargets() {
		return this.min;
	}

	public int maxTargets() {
		return this.max;
	}

	public Set<Tcoord> targets() {
		return this.wSet;
	}

	public Set<Tcoord> targetables() {
		return this.refSet;
	}

	public int numTargets() {
		return this.wSet.size();
	}

	boolean isComplete() {
		return this.min <= this.wSet.size() && this.wSet.size() <= this.max;
	}

	@Override
	public String toString() {
		String ret = "Target set [ " + this.min + " - " + this.max + " ]"
						+ "\n\tOptions: ";

		for (Tcoord c : this.refSet) {
			ret += c + ",";
		}

		ret += "\n\tSelected: ";

		for (Tcoord c : this.wSet) {
			ret += c + ",";
		}

		return ret;
	}
}