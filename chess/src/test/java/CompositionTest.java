package oht.chess.game;

import oht.chess.shared.Role;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.RoleData;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class CompositionTest {
	Composition comp;
	@Before
	public void setUp() {
		comp = new Composition();
	}

	@Test
	public void spawnIncreaseValue() {
		assertEquals(0, comp.value());
		comp.spawn(Chesspiece.Rook, Role.Base, Faction.Black, 0, 0);
		assertEquals(RoleData.totalCost(Chesspiece.Rook, Role.Base), comp.value());
	}

	@Test
	public void spawnIncreasesValueOnlyOnSuccess() {
		comp.spawn(Chesspiece.Rook, Role.Base, Faction.Black, 0, 0);
		comp.spawn(Chesspiece.Rook, Role.Base, Faction.Black, 0, 0);
		assertEquals(RoleData.totalCost(Chesspiece.Rook, Role.Base), comp.value());
	}

	@Test
	public void removeDecreasesValue() {
		assertEquals(0, comp.value());
		comp.spawn(Chesspiece.Rook, Role.Base, Faction.Black, 0, 0);
		comp.remove(new Tcoord(0, 0));
		assertEquals(0, comp.value());
	}

	@Test
	public void removeDecreasesValueOnlyOnSuccess() {
		comp.spawn(Chesspiece.Rook, Role.Base, Faction.Black, 0, 0);
		comp.remove(new Tcoord(1, 1));
		assertEquals(RoleData.totalCost(Chesspiece.Rook, Role.Base), comp.value());
	}
}
