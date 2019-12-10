package oht.chess.util;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

public class TargetSet {
	Set<Tcoord> refSet;
	HashSet<Tcoord> wSet = new HashSet<>();
	HashMap<Tcoord, Integer> orderMap = new HashMap<>();
	HashMap<Integer, Tcoord> insertionOrder = new HashMap<>();
	int min;
	int max;

	public TargetSet(Set<Tcoord> targetables, int min, int max) {
		if (targetables == null || max < min || min < 0 || max < 0) {
			throw new IllegalArgumentException();
		}
		this.refSet = targetables;
		this.min = min;
		this.max = max;
	}

	public TargetSet(Set<Tcoord> targetables, int num) {
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
		if (!result) {
			return false;
		}
		int index = orderMap.remove(t);
		insertionOrder.remove(insertionOrder.size() - 1);

		orderMap.entrySet().forEach(pair -> {
			if (index < pair.getValue()) {
				pair.setValue(pair.getValue() - 1);
			}

			insertionOrder.put(pair.getValue(), pair.getKey());
		});

		return result;
	}

	public boolean toggle(Tcoord t) {
		if (this.wSet.contains(t)) {
			remove(t);
			return false;
		}

		return add(t);
	}

	public boolean contains(Tcoord coord) {
		return this.wSet.contains(coord);
	}

	public Tcoord get(int i) {
		return insertionOrder.get(i);
	}

	public int minSize() {
		return this.min;
	}

	public int maxSize() {
		return this.max;
	}

	public int size() {
		return this.wSet.size();
	}

	public Iterable<Tcoord> targeted() {
		return this.wSet;
	}

	public Iterable<Tcoord> selectable() {
		return this.refSet;
	}

	public boolean isComplete() {
		return this.min <= this.wSet.size() && this.wSet.size() <= this.max;
	}

	public int numTargets() {
		return refSet.size();
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