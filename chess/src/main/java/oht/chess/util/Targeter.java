package oht.chess.util;

import java.util.ArrayList;
/**
 * Luokka joka esittää järjestettyä kokoelmaa TargetSettejä
 */
public class Targeter {
	private ArrayList<TargetSet> sets = new ArrayList<>();
	private TargetSet wSet;

	/**
	 * Parametrinä syötetty TargetSet asetetaan aktiiviseksi, eli osa luokan metodeista muokkaa aktiivista settiä.
	 */
	public Targeter(TargetSet initialSet) {
		if (initialSet == null) {
			throw new IllegalArgumentException();
		}
		this.wSet = initialSet;
		this.sets.add(this.wSet);
	}

	/**
	 * Lisää parametrinä syötetty TargetSet kokoelmaan ja aseta se aktiiviseksi
	 */
	public void push(TargetSet nextSet) {
		if (nextSet == null) {
			return;
		}
		this.wSet = nextSet;
		this.sets.add(this.wSet);
	}

	/**
	 * Poistaa uusimman TargetSetin kokoelmasta ja palauttaa sen
	 * @return Uusin TargetSet, tai null jos kokoelmassa on 0 tai 1 TargetSettiä
	 */
	public TargetSet pop() {
		if (this.sets.size() <= 1) {
			return null;
		}
		TargetSet ret = this.sets.remove(this.sets.size() - 1);
		this.wSet = this.sets.get(this.sets.size() - 1);

		return ret;
	}

	/**
	 * Tarkistaa kokoelman jokaisen TargetSetin täydellisyyden
	 */
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

	/**
	 * Muuttaa aktiivisen TargetSetin koordinaatin tilaa
	 */
	public boolean toggle(Tcoord coord) {
		return wSet.toggle(coord);
	}

	/**
	 * Tarkistaa sisältääkö aktiivinen TargetSet koordinaatin
	 */
	public boolean contains(Tcoord coord) {
		return wSet.contains(coord);
	}

	/**
	 * Palauttaa n:nen setin i:nnen koordinaatin, tai null jos ainakin toinen parametreista indeksien ulkopuolella
	 */
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

	/**
	 * Palauttaa aktiivisen TargetSetin valittavissa olevat koordinaatit
	 */
	public Iterable<Tcoord> selectable() {
		return wSet.selectable();
	}

	/**
	 * Palauttaa aktiivisen TargetSetin valitut koordinaatit
	 */
	public Iterable<Tcoord> targeted() {
		return wSet.targeted();
	}

	/**
	 * Palauttaa aktiivisen TargetSetin minimikoon
	 */
	public int minSize() {
		return wSet.minSize();
	}

	/**
	 * Palauttaa aktiivisen TargetSetin maksimikoon
	 */
	public int maxSize() {
		return wSet.maxSize();
	}

	/**
	 * Palauttaa kokoelmassa olevien TargetSettien lukumäärän.
	 */
	public int size() {
		return sets.size();
	}

	/**
	 * Palauttaa aktiivisen TargetSetin koordinaattien lukumäärän
	 */
	public int numTargets() {
		return wSet.numTargets();
	}

	/**
	 * Palauttaa index:nä lisätyn TargetSetin valittujen koordinaattien lukumäärän
	 */
	public int size(int index) {
		if (index < 0 || index >= sets.size()) {
			return 0;
		}

		return sets.get(index).size();
	}
}