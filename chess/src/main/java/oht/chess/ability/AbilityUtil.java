package oht.chess.ability;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import oht.chess.Actor;
import oht.chess.Board;
import oht.chess.Tcoord;
import oht.chess.Vector;

class AbilityUtil
{
	static Set<Tcoord> filterHostile(ArrayList<Vector> vec, Actor comp, Board b)
	{
		Set<Tcoord> set = filterNonempty(vec, comp.pos(), b);

		return set.stream()
			.filter(e -> b.get(e).faction() != comp.faction() )
			.collect(Collectors.toSet());
	}

	static Set<Tcoord> filterFriendly(ArrayList<Vector> vec, Actor comp, Board b)
	{
		Set<Tcoord> set = filterNonempty(vec, comp.pos(), b);

		return set.stream()
			.filter(e -> b.get(e).faction() == comp.faction() )
			.collect(Collectors.toSet());
	}

	static Set<Tcoord> filterEmpty(ArrayList<Vector> vec, Tcoord origin, Board b)
	{
		HashSet<Tcoord> ret = new HashSet<>();

		for (Vector v : vec)
		{
			for (int i = 1; i <= v.mag(); i++)
			{
				Vector d = new Vector(v.dir(), i);
				Tcoord coord = Vector.add(origin, d);
				if (b.isOob(coord)) { break; }

				Actor a = b.get(coord);
				if (a != null) { break; }
				ret.add(coord);
			}
		}

		return ret;
	}

	static Set<Tcoord> filterNonempty(ArrayList<Vector> vec, Tcoord origin, Board b)
	{
		HashSet<Tcoord> ret = new HashSet<>();

		for (Vector v : vec)
		{
			for (int i = 1; i <= v.mag(); i++)
			{
				Vector d = new Vector(v.dir(), i);
				Tcoord coord = Vector.add(origin, d);
				if (b.isOob(coord)) { break; }

				Actor a = b.get(coord);
				if (a != null)
				{
					ret.add(coord);
					break;
				}
			}
		}

		return ret;
	}
}