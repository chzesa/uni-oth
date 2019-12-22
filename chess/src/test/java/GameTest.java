package oht.chess.game;

import java.util.ArrayDeque;
import java.util.Random;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.shared.Role;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Targeter;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GameTest {
	Game game;
	Random rand;

	@Before
	public void setUp() {
		game = new Game(2, 2);
		rand = new Random(52623);
	}

	@Test
	public void testInitialTurn() {
		assertEquals(Faction.White, game.activeFaction());
		assertEquals(0, game.turn());
	}

	@Test
	public void testTurnIncrement() {
		game.nextTurn();
		assertEquals(1, game.turn());

		game.nextTurn();
		assertEquals(2, game.turn());
	}

	@Test
	public void testTurnFaction() {
		Faction f = game.nextTurn();		
		assertEquals(Faction.Black, f);
		assertEquals(Faction.Black, game.activeFaction());

		f = game.nextTurn();
		assertEquals(Faction.White, f);
		assertEquals(Faction.White, game.activeFaction());
	}

	@Test
	public void testManyTurnFaction() {
		Faction f = game.activeFaction();

		for (int i = 0; i < 100; i++) {
			assertEquals(Faction.White, f);
			assertEquals(Faction.White, game.activeFaction());

			f = game.nextTurn();
			assertEquals(Faction.Black, f);
			assertEquals(Faction.Black, game.activeFaction());

			f = game.nextTurn();
		}
	}

	@Test
	public void testInitializerDimension() {
		assertEquals(2, game.width());
		assertEquals(2, game.height());
	}

	@Test
	public void testManyInitializerDimension() {
		for (int i = 0; i < 100; i++) {
			int x = rand.nextInt(100);
			int y = rand.nextInt(100);
			Game g = new Game(x, y);
			assertEquals(x, g.width());
			assertEquals(y, g.height());
		}
	}

	@Test
	public void deployPlacesUnitsCorrectly() {
		Composition comp = new Composition();
		comp.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 0, 1);
		Game tg = new Game(8, 8, comp, comp);
		Entity pw = new Entity(Chesspiece.Pawn, Role.Base, Faction.White);
		pw.setPos(new Tcoord(0, 1));
		Entity pb = new Entity(Chesspiece.Pawn, Role.Base, Faction.Black);
		pb.setPos(new Tcoord(7, 6));
		assertEquals(pw, tg.get(0, 1));
		assertEquals(pb, tg.get(7, 6));
	}

	@Test
	public void deployWorksOnSmallerBoard() {
		Composition comp = new Composition();
		comp.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 0, 1);
		comp.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 10, 10);

		Game tg = new Game(2, 2, comp, comp);
		Entity pw = new Entity(Chesspiece.Pawn, Role.Base, Faction.White);
		pw.setPos(new Tcoord(0, 1));
		Entity pb = new Entity(Chesspiece.Pawn, Role.Base, Faction.Black);
		pb.setPos(new Tcoord(1, 0));

		assertEquals(pw, tg.get(0, 1));
		assertEquals(pb, tg.get(1, 0));
	}

	@Test
	public void cannotActOnEmptyBoard() {
		assertFalse(game.canAct(Faction.White));
		assertFalse(game.canAct(Faction.Black));
	}

	@Test
	public void canActCheckMovement() {
		// ##
		// WB

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 1, 0);
		assertTrue(game.canAct(Faction.White));
		assertFalse(game.canAct(Faction.Black));
	}

	@Test
	public void gameOverOnEmptyBoard() {
		assertEquals(GameResult.Draw, game.isGameOver());
	}

	@Test
	public void gameOverWhiteWin() {
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.p1Leader = game.get(0, 0);
		assertEquals(GameResult.WhiteWin, game.isGameOver());
	}

	@Test
	public void gameOverBlackWin() {
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 0, 0);
		game.p2Leader = game.get(0, 0);
		assertEquals(GameResult.BlackWin, game.isGameOver());
	}

	@Test
	public void gameNotOver() {
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 1, 0);
		game.p1Leader = game.get(0, 0);
		game.p2Leader = game.get(1, 0);
		assertEquals(GameResult.None, game.isGameOver());
	}
}
