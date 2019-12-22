package oht.chess.ability;

import oht.chess.shared.IAbility;
import oht.chess.util.Targeter;
import oht.chess.util.TargeterState;
import java.util.Iterator;
import oht.chess.shared.IBoard;
import oht.chess.shared.IActor;
import oht.chess.util.Tcoord;

/**
 * Pattitilanteen selvittämiseen käytettävä luokka. Käy kaikki kyvyn mahdolliset Targeter variaatiot läpi.
 */
public class AbilityUseChecker {
	private IAbility ability;
	private IActor user;
	private IBoard board;
	private boolean result;

	private Tcoord nth(Iterator<Tcoord> t, int index) {
		Tcoord elem = null;
		for (int i = 0; i < index + 1; i++) {
			elem = t.next();
		}

		return elem;
	}

	private boolean beginRecursion(Targeter t) {
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

	private boolean recurse(Targeter t, int targets) {
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

	/**
	 * Luo uuden tarkastajan ja käy kaikki kyvyn mahdolliset variaatiot läpi.
	 * @param	ability	Tarkistettava kyky
	 * @param	user	Kykyä käyttävä yksikkö
	 * @param	board	nykyinen pelilauta
	 */
	public AbilityUseChecker(IAbility ability, IActor user, IBoard board) {
		this.ability = ability;
		this.user = user;
		this.board = board;
		if (ability == null || user == null || board == null) {
			result = false;
		} else {
			result = beginRecursion(ability.beginUse(user, board));
		}
	}

	/**
	 * Lukee tarkastajan tuloksen.
	 * @return	true, jos hahmo pystyy käyttämään kykyä nykyisellä lautakonfiguraatiolla, false muuten.
	 */
	public boolean result() {
		return this.result;
	}
}