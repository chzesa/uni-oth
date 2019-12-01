package oht.chess.ability;

import java.util.ArrayList;
import oht.chess.util.Tcoord;

public class AbilityTargeter {
	ArrayList<TargetSet> sets = new ArrayList<>();
	TargetSet wSet;

	public AbilityTargeter(TargetSet initialSet) {
		this.wSet = initialSet;
		this.sets.add(this.wSet);
	}

	public void push(TargetSet nextSet) {
		this.wSet = nextSet;
		this.sets.add(this.wSet);
	}

	public TargetSet pop() {
		if (this.sets.size() <= 1) {
			return null;
		}
		TargetSet ret = this.sets.remove(this.sets.size() - 1);
		this.wSet = this.sets.get(this.sets.size() -1);

		return ret;
	}

	public boolean isComplete() {
		boolean ok = true;
		for (TargetSet set : sets) {
			if (!set.isComplete()) { 
				ok = false;
				break;
			}
		}

		return ok;
	}

	public int numSets() {
		return this.sets.size();
	}

	public boolean toggle(Tcoord coord) {
		return wSet.toggle(coord);
	}

	public boolean contains(Tcoord coord) {
		return wSet.contains(coord);
	}

	public Tcoord get(int set, int index) {
		if (index < 0 || set < 0) {
			return null;
		}

		if (sets.size() <= set) {
			return null;
		}

		if (sets.get(set).size() <= index) {
			return null;
		}

		return sets.get(set).get(index);
	}

	public Iterable<Tcoord> selectable() {
		return wSet.selectable();
	}

	public Iterable<Tcoord> targeted() {
		return wSet.targeted();
	}

	public int minSize() {
		return wSet.minSize();
	}

	public int maxSize() {
		return wSet.maxSize();
	}

	public int size() {
		return sets.size();
	}

	public int numTargets() {
		return wSet.numTargets();
	}

	public int size(int index) {
		if (index < 0 || index >= sets.size()) {
			return 0;
		}

		return sets.get(index).size();
	}
}