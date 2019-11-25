import java.util.Random;
import oht.chess.ability.Role;
import oht.chess.game.Entity;
import oht.chess.game.Game;
import oht.chess.unit.Faction;
import oht.chess.unit.IActor;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.UnitFactory;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BoardTest {
	Game game;
	Random rand;

	@Before
	public void setUp() {
		game = new Game(8, 8);
		rand = new Random(52623);
	}

	@Test
	public void boardOobCheckNegative() {
		assertTrue(game.isOob(new Tcoord(-1, 0)));
		assertTrue(game.isOob(new Tcoord(0, -1)));
		assertTrue(game.isOob(new Tcoord(-1, -1)));
	}

	@Test
	public void boardOobCheckPositive() {
		assertTrue(game.isOob(new Tcoord(8, 0)));
		assertTrue(game.isOob(new Tcoord(0, 8)));
		assertTrue(game.isOob(new Tcoord(8, 8)));
	}

	@Test
	public void boardOobCheckInBounds() {
		assertFalse(game.isOob(new Tcoord(0, 0)));
		assertFalse(game.isOob(new Tcoord(7, 0)));
		assertFalse(game.isOob(new Tcoord(0, 7)));
		assertFalse(game.isOob(new Tcoord(7, 7)));
	}

	@Test
	public void oobQueryReturnsNull() {
		assertNull(game.get(-1, 0));
		assertNull(game.get(0, -1));
		assertNull(game.get(-1, -1));

		assertNull(game.get(8, 0));
		assertNull(game.get(0, 8));
		assertNull(game.get(8, 8));
	}

	@Test
	public void testSpawn0() {
		Tcoord coord = new Tcoord(0, 0);
		assertTrue(game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, coord));
		IActor a = game.get(0, 0);
		assertTrue(a != null);
		assertEquals(coord, a.pos());
		assertEquals(Chesspiece.Pawn, a.base());
		assertEquals(Role.Base, a.role());
		assertEquals(Faction.White, a.faction());
	}

	@Test
	public void testSpawn1() {
		Tcoord coord = new Tcoord(5, 3);
		Role role = Role.Fey;
		Chesspiece base = Chesspiece.Bishop;
		Faction f = Faction.Black;

		assertTrue(game.spawn(base, role, f, coord));
		IActor a = game.get(coord);
		assertNotNull(a);

		assertEquals(coord, a.pos());
		assertEquals(base, a.base());
		assertEquals(role, a.role());
		assertEquals(f, a.faction());
	}

	@Test
	public void testSpawn2() {
		Tcoord coord = new Tcoord(2, 7);
		Role role = Role.Kineticist;
		Chesspiece base = Chesspiece.Queen;
		Faction f = Faction.Black;

		assertTrue(game.spawn(base, role, f, coord));
		IActor a = game.get(coord);
		assertNotNull(a);

		assertEquals(coord, a.pos());
		assertEquals(base, a.base());
		assertEquals(role, a.role());
		assertEquals(f, a.faction());
	}

	@Test
	public void spawnFailOob() {
		assertFalse(game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, -1, 0));
	}

	@Test
	public void spawnFailOccupied() {
		Tcoord coord = new Tcoord(2, 7);
		Role role = Role.Kineticist;
		Chesspiece base = Chesspiece.Queen;
		Faction f = Faction.Black;

		assertTrue(game.spawn(base, role, f, coord));
		IActor a = game.get(coord);
		assertNotNull(a);

		assertFalse(game.spawn(Chesspiece.King, Role.Base, Faction.White, coord));

		// Ensure first unit remains
		assertEquals(coord, a.pos());
		assertEquals(base, a.base());
		assertEquals(role, a.role());
		assertEquals(f, a.faction());
	}

	@Test
	public void removeReturnsNullOnEmpty() {
		assertNull(game.remove(new Tcoord(4, 3)));
		assertNull(game.remove(new Tcoord(1, 5)));
		assertNull(game.remove(new Tcoord(3, 7)));
		assertNull(game.remove(new Tcoord(0, 2)));
	}

	@Test
	public void removeReturnsNullOnOob() {
		assertNull(game.remove(new Tcoord(-1, 0)));
		assertNull(game.remove(new Tcoord(8, 0)));
		assertNull(game.remove(new Tcoord(0, -1)));
		assertNull(game.remove(new Tcoord(0, 8)));
	}

	@Test
	public void removeReturnsUnit0() {
		Tcoord coord = new Tcoord(0, 0);
		game.spawn(Chesspiece.Queen, Role.Kineticist, Faction.Black, coord);
		Entity e = game.get(coord);
		assertEquals(e, game.remove(coord));
		assertNull(game.get(coord));
	}

	@Test
	public void removeReturnsUnit1() {
		Tcoord coord = new Tcoord(2, 4);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, coord);
		Entity e = game.get(coord);
		assertEquals(e, game.remove(coord));
		assertNull(game.get(coord));
	}

	@Test
	public void moveMovesUnit0() {
		Tcoord src = new Tcoord(0, 0);
		Tcoord dst = new Tcoord(2, 3);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, src);
		Entity e = game.get(src);
		assertNotNull(e);
		assertTrue(game.move(src, dst));

		assertNull(game.get(src));
		assertEquals(e, game.get(dst));
	}

	@Test
	public void moveMovesUnit1() {
		Tcoord src = new Tcoord(5, 5);
		Tcoord dst = new Tcoord(6, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, src);
		Entity e = game.get(src);
		assertNotNull(e);
		assertTrue(game.move(src, dst));

		assertNull(game.get(src));
		assertEquals(e, game.get(dst));
	}

	@Test
	public void moveChangesEntityPosition() {
		Tcoord src = new Tcoord(0, 0);
		Tcoord dst = new Tcoord(2, 2);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, src);
		Entity e = game.get(src);
		game.move(src, dst);
		assertEquals(dst, e.pos());
	}

	@Test
	public void moveFailsDstOob() {
		Tcoord src = new Tcoord(5, 5);
		Tcoord dst = new Tcoord(-1, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, src);
		Entity e = game.get(src);
		assertFalse(game.move(src, dst));
		assertEquals(src, e.pos());
		assertEquals(e, game.get(src));
	}

	@Test
	public void moveFailsSrcOob() {
		Tcoord src = new Tcoord(-1, 5);
		Tcoord dst = new Tcoord(1, 1);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, dst);
		Entity e = game.get(dst);
		assertFalse(game.move(src, dst));

		assertEquals(e, game.get(dst));
		assertEquals(dst, e.pos());
	}
	@Test
	public void moveFailsSrcUnoccupied() {
		Tcoord src = new Tcoord(0, 0);
		Tcoord dst = new Tcoord(1, 1);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, dst);
		Entity e = game.get(dst);
		assertFalse(game.move(src, dst));

		assertEquals(e, game.get(dst));
		assertEquals(dst, e.pos());
	}

	@Test
	public void moveFailsDstOccupied() {
		Tcoord src = new Tcoord(5, 5);
		Tcoord dst = new Tcoord(1, 1);
		game.spawn(Chesspiece.Rook, Role.Base, Faction.White, dst);
		Entity other = game.get(dst);

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, src);
		Entity mover = game.get(src);
		assertFalse(game.move(src, dst));
		assertEquals(mover, game.get(src));
		assertEquals(src, mover.pos());

		assertEquals(other, game.get(dst));
		assertEquals(dst, other.pos());
	}
}
