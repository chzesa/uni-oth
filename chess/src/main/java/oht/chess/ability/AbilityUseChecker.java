package oht.chess.ability;

import java.util.Iterator;
import oht.chess.game.IBoard;
import oht.chess.unit.IActor;
import oht.chess.util.Tcoord;

public class AbilityUseChecker {
	IAbility ability;
	IActor user;
	IBoard board;
	boolean result;

	Tcoord nth(Iterator<Tcoord> t, int index) {
		Tcoord elem = null;
		for (int i = 0; i < index + 1; i++) {
			elem = t.next();
		}

		return elem;
	}

	boolean beginRecursion(AbilityTargeter t) {
		// start recursion for all target counts in the set
		if (t.maxSize() == 0) {
			return true;
		}

		for (int i = 1; i < t.maxSize() + 1; i++) {
			if (recurse(t, i)) {
				return true;
			}
		}

		return false;
	}

	boolean recurse(AbilityTargeter t, int targets) {
		int targetableCount = t.numTargets();
		for (int i = 0; i < targetableCount; i++) {
			Tcoord coord = nth(t.selectable().iterator(), i);
			if (t.contains(coord)) {
				continue;
			}

			// add new coordinate to target set
			t.toggle(coord);

			// Compare size of the newest set to desired # of targets
			if (t.size(t.size() - 1) == targets && t.isComplete()) {

				int setCount = t.numSets();
				if (ability.isComplete(t, user, board) == TargeterState.Complete
								&& ability.isValid(t, user, board)) {
					return true;
				}

				if (t.numSets() > setCount) {
					// new set was added to targeter, restart recursion
					if (beginRecursion(t)) {
						return true;
					}

					// remove newest set from targeter
					t.pop();
				}
			} else if (t.size() < targets && recurse(t, targets)) {
				return true;
			}

			// remove the added coordinate from target set
			t.toggle(coord);
		}
	
		return false;
	}

	public AbilityUseChecker(IAbility ability, IActor user, IBoard board) {
		this.ability = ability;
		this.user = user;
		this.board = board;
		result = beginRecursion(ability.beginUse(user, board));
	}

	public boolean result() {
		return this.result;
	}
}