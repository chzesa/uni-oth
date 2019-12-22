package oht.chess.ability;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import oht.chess.shared.IActor;
import oht.chess.shared.IBoard;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

/**
 * Apuluokka joka tarjoaa metodeja erilaisten koordinaattisettien generoimiselle.
 */
class AbilityUtil {
	/**
	 * Palauttaa joukon koordinaatteja, jotka ovat laudan sisällä, ja joihin pääsee vektoreiden
	 * kuvaamilla liikkeillä. Palautettu joukko sisältää ensimmäiset epätyhjät koordinaatit
	 * joihin pääsee lähtöpisteestä liikevektoreilla.
	 @param	vec	Liikevektorit
	 @param	origin	Lähtöpiste
	 @param b	Nykyinen lautatilanne.
	 */
	static Set<Tcoord> filterValid(Set<Vector> vec, Tcoord origin, IBoard b) {
		HashSet<Tcoord> ret = new HashSet<>();
		if (vec == null || origin == null || b == null) {
			return ret;
		}
		for (Vector v : vec) {
			for (int i = 1; i <= v.mag(); i++) {
				Vector d = new Vector(v.dir(), i);
				Tcoord coord = Vector.add(origin, d);
				if (b.isOob(coord)) {
					break;
				}
				ret.add(coord);
				if (b.get(coord) != null) {
					break;
				}
			}
		}

		return ret;
	}

	/**
	 * Suodattaa edelleen filterNonempty funktion palautusarvosta ne ruudut, joissa on verratavalle yksikölle vihamielinen yksikkö.
	 */
	static Set<Tcoord> filterHostile(Set<Vector> vec, IActor comp, IBoard b) {
		Set<Tcoord> set = filterNonempty(vec, comp.pos(), b);

		return set.stream()
			.filter(e -> b.get(e).faction() != comp.faction())
			.collect(Collectors.toSet());
	}

	/**
	 * Suodattaa edelleen filterNonempty funktion palautusarvosta ne ruudut, joissa on samaan Factioniin kuuluva yksikkö kuin verrattava yksikkö.
	 */
	static Set<Tcoord> filterFriendly(Set<Vector> vec, IActor comp, IBoard b) {
		Set<Tcoord> set = filterNonempty(vec, comp.pos(), b);

		return set.stream()
			.filter(e -> b.get(e).faction() == comp.faction())
			.collect(Collectors.toSet());
	}

	/**
	 * Suodattaa edelleen filterValid funktion syötteestä ne ruudut, joissa ei ole yksikköä.
	 */
	static Set<Tcoord> filterEmpty(Set<Vector> vec, Tcoord origin, IBoard b) {
		return filterValid(vec, origin, b).stream()
			.filter(c -> b.get(c) == null).collect(Collectors.toSet());
	}

	/**
	 * Suodattaa edelleen filterValid funktion syötteestä ne ruudut, on yksikkö.
	 */
	static Set<Tcoord> filterNonempty(Set<Vector> vec, Tcoord origin, IBoard b) {
		return filterValid(vec, origin, b).stream()
			.filter(c -> b.get(c) != null).collect(Collectors.toSet());
	}

	/**
	 * Apufunktio joka tarkastaa pääseekö lähtöpisteestä kohteeseen jollakin liikevektorilla.
	 @param	vec	Liikevektorit
	 @param	origin	Lähtöpiste
	 @param	target	Kohdepiste
	 @param b	Nykyinen lautatilanne
	 @return true, jos lähtöpisteestä pääsee kohteeseen jollakin liikevektorilla.
	 */
	static boolean isValidTarget(Set<Vector> vec, Tcoord origin, Tcoord target, IBoard b) {
		return filterValid(vec, origin, b).contains(target);
	}

	/**
	 * Apufunktio joka tarkastaa pääseekö yksikön liikevektoreilla yksikön sijainnista kohderuutuun.
	 */
	static boolean unitCanMove(IActor actor, Tcoord target, IBoard b) {
		if (actor == null) {
			return false;
		}
		return isValidTarget(actor.movementVectors(), actor.pos(), target, b);
	}

	/**
	 * Apufunktio joka tarkastaa pääseekö yksikön hyökkäysvektoreilla yksikön sijainnista kohderuutuun.
	 */
	static boolean unitCanAttack(IActor actor, Tcoord target, IBoard b) {
		if (actor == null) {
			return false;
		}
		return isValidTarget(actor.attackVectors(), actor.pos(), target, b);
	}
}