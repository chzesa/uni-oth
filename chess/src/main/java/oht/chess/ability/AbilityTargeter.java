package oht.chess.ability;

import java.util.ArrayList;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;
import oht.chess.util.Tcoord;

public class AbilityTargeter {
	ArrayList<TargetSet> sets = new ArrayList<>();
	TargetSet wSet;

	AbilityTargeter(TargetSet initialSet) {
		this.wSet = initialSet;
		this.sets.add(this.wSet);
	}

	void append(TargetSet nextSet) {
		this.wSet = nextSet;
		this.sets.add(this.wSet);
	}

	boolean isComplete() {
		boolean ok = true;
		for (TargetSet set : sets) {
			if (!set.isComplete()) { 
				ok = false;
				break;
			}
		}

		return ok;
	}

	int numSets() {
		return this.sets.size();
	}

	public boolean toggle(Tcoord coord) {
		return wSet.toggle(coord);
	}

	Tcoord get(int set, int index) {
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

	public int size(int index) {
		if (index < 0 || index >= sets.size()) {
			return 0;
		}

		return sets.get(index).size();
	}
}