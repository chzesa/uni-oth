package oht.chess.ability;

import java.util.HashSet;
import java.util.Set;
import oht.chess.shared.Role;
import oht.chess.game.Game;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.unit.UnitFactory;
import oht.chess.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class AbilityUtilTest {
	HashSet vecSet;

	@Before
	public void setUp() {
		vecSet = new HashSet<>();
	}

	@Test
	public void filterValidSingleVector() {
		Game g = new Game(3, 3);
		Vector v = new Vector(0, 1, 1);
		// ###
		// X##
		// O##
		vecSet.add(v);
		Set<Tcoord> set = AbilityUtil.filterValid(vecSet, new Tcoord(0, 0), g);
		assertEquals(1, set.size());
		assertTrue(set.contains(new Tcoord(0, 1)));
	}

	@Test
	public void filterValidLongVector() {
		Game g = new Game(3, 3);
		vecSet.add(new Vector(0, 1, 10));

		// X##
		// X##
		// O##

		Set<Tcoord> set = AbilityUtil.filterValid(vecSet, new Tcoord(0, 0), g);
		assertEquals(2, set.size());
		assertTrue(set.contains(new Tcoord(0, 1)));
		assertTrue(set.contains(new Tcoord(0, 2)));
	}

	@Test
	public void filterValidMultipleVector() {
		Game g = new Game(5, 5);
		Set<Vector> s = UnitFactory.make(Chesspiece.King, Faction.Black).movementVectors();

		// #####
		// #XXX#
		// #XOX#
		// #XXX#
		// #####

		Tcoord origin = new Tcoord(2, 2);
		Set<Tcoord> result = AbilityUtil.filterValid(s, origin, g);

		assertEquals(8, result.size());
		assertTrue(result.contains(new Tcoord(1, 1)));
		assertTrue(result.contains(new Tcoord(2, 1)));
		assertTrue(result.contains(new Tcoord(3, 1)));

		assertTrue(result.contains(new Tcoord(1, 2)));
		assertTrue(result.contains(new Tcoord(3, 2)));

		assertTrue(result.contains(new Tcoord(1, 3)));
		assertTrue(result.contains(new Tcoord(2, 3)));
		assertTrue(result.contains(new Tcoord(3, 3)));
	}

	@Test
	public void filterValidHaltsOnObstacleInclusive() {
		Game g = new Game(5, 1);
		vecSet.add(new Vector(1, 0, 10));
		g.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 2, 0);
		// OXB##
		Set<Tcoord> result = AbilityUtil.filterValid(vecSet, new Tcoord(0, 0), g);

		assertEquals(2, result.size());
		assertTrue(result.contains(new Tcoord(1, 0)));
		assertTrue(result.contains(new Tcoord(2, 0)));
	}

	@Test
	public void filterValidHaltsOneVectorOnObstacleInclusive() {
		Game g = new Game(4, 4);
		g.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 0, 2);
		vecSet.add(new Vector(1, 0, 10));
		vecSet.add(new Vector(0, 1, 10));

		// ####
		// B###
		// X###
		// OXXX

		Set<Tcoord> result = AbilityUtil.filterValid(vecSet, new Tcoord(0, 0), g);
		assertEquals(5, result.size());
		assertTrue(result.contains(new Tcoord(0, 1)));
		assertTrue(result.contains(new Tcoord(0, 2)));
		assertTrue(result.contains(new Tcoord(1, 0)));
		assertTrue(result.contains(new Tcoord(2, 0)));
		assertTrue(result.contains(new Tcoord(3, 0)));
	}

	@Test
	public void filterEmptyTest() {
		Game g = new Game(5, 1);
		vecSet.add(new Vector(1, 0, 10));
		g.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 3, 0);
		// OXXB#
		Set<Tcoord> result = AbilityUtil.filterEmpty(vecSet, new Tcoord(0, 0), g);
		assertEquals(2, result.size());
		assertTrue(result.contains(new Tcoord(1, 0)));
		assertTrue(result.contains(new Tcoord(2, 0)));
	}

	@Test
	public void filterNonemptyTest() {
		Game g = new Game(5, 1);
		vecSet.add(new Vector(1, 0, 10));
		g.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 3, 0);
		// OXXB#
		Set<Tcoord> result = AbilityUtil.filterNonempty(vecSet, new Tcoord(0, 0), g);
		assertEquals(1, result.size());
		assertTrue(result.contains(new Tcoord(3, 0)));
	}
}
