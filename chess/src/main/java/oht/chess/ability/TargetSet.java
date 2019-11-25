package oht.chess.ability;
import java.util.HashSet;
import java.util.Set;
import oht.chess.util.Tcoord;
import java.util.HashMap;

class TargetSet {
	Set<Tcoord> refSet;
	HashSet<Tcoord> wSet = new HashSet<>();
	HashMap<Tcoord, Integer> orderMap = new HashMap<>();
	HashMap<Integer, Tcoord> insertionOrder = new HashMap<>();
	int min;
	int max;

	TargetSet(Set<Tcoord> targetables, int min, int max) {
		if (targetables.size() < min || max < min || min < 0 || max < 0) {
			throw new IllegalArgumentException();
		}
		this.refSet = targetables;
		this.min = min;
		this.max = max;
	}

	TargetSet(Set<Tcoord> targetables, int num) {
		this(targetables, num, num);
	}

	private boolean add(Tcoord t) {
		if (this.wSet.size() < this.max && this.refSet.contains(t)) {
			if (this.wSet.add(t)) {
				int index = orderMap.size();
				orderMap.put(t, index);
				insertionOrder.put(index, t);
				return true;
			}
		}
		return false;
	}

	private boolean remove(Tcoord t) {
		boolean result = wSet.remove(t);
		int index = orderMap.remove(t);
		insertionOrder.remove(insertionOrder.size() - 1);

		orderMap.forEach((k, v) -> {
			if (index < v) {
				v -= 1;
			}

			insertionOrder.put(v, k);
		});

		return result;
	}

	boolean toggle(Tcoord t) {
		if (this.wSet.contains(t)) {
			remove(t);
			return false;
		}

		return add(t);
	}

	Tcoord get(int i) {
		return insertionOrder.get(i);
	}

	int minSize() {
		return this.min;
	}

	int maxSize() {
		return this.max;
	}

	int size() {
		return this.wSet.size();
	}

	Iterable<Tcoord> targeted() {
		return this.wSet;
	}

	Iterable<Tcoord> selectable() {
		return this.refSet;
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