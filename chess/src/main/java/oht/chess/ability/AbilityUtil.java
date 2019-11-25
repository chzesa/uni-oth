package oht.chess.ability;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

class AbilityUtil {
	static Set<Tcoord> filterHostile(Set<Vector> vec, IActor comp, IBoard b) {
		Set<Tcoord> set = filterNonempty(vec, comp.pos(), b);

		return set.stream()
			.filter(e -> b.get(e).faction() != comp.faction())
			.collect(Collectors.toSet());
	}

	static Set<Tcoord> filterFriendly(Set<Vector> vec, IActor comp, IBoard b) {
		Set<Tcoord> set = filterNonempty(vec, comp.pos(), b);

		return set.stream()
			.filter(e -> b.get(e).faction() == comp.faction())
			.collect(Collectors.toSet());
	}

	static Set<Tcoord> filterEmpty(Set<Vector> vec, Tcoord origin, IBoard b) {
		return filterValid(vec, origin, b).stream()
			.filter(c -> b.get(c) == null).collect(Collectors.toSet());
	}

	static Set<Tcoord> filterNonempty(Set<Vector> vec, Tcoord origin, IBoard b) {
		return filterValid(vec, origin, b).stream()
			.filter(c -> b.get(c) != null).collect(Collectors.toSet());
	}

	static Set<Tcoord> filterValid(Set<Vector> vec, Tcoord origin, IBoard b) {
		HashSet<Tcoord> ret = new HashSet<>();

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
}