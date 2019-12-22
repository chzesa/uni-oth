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

	class DummyController implements IPlayerController {
		ArrayDeque<MoveDescriptor> descs;
		Tcoord[][] targs;
		int pTarg = 0;
		Tcoord leader;
		DummyController() {
		}

		DummyController(Tcoord leader) {
			this.leader = leader;
		}

		DummyController(ArrayDeque<MoveDescriptor> descs, Tcoord[][] targs, Tcoord leader) {
			this.descs = descs;
			this.targs = targs;
			this.leader = leader;
		}

		@Override
		public MoveDescriptor selectAbility(Game game) {
			return descs.removeFirst();
		}

		@Override
		public Targeter targetAbility(Game game, IAbility ability, Targeter t) {
			for (Tcoord coord : targs[pTarg++]) {
				t.toggle(coord);
			}

			return t;
                }

		@Override
		public Tcoord nominateLeader(Game game, Faction f) {
			return leader;
		}
	}

	@Test
	public void gameImmediateDrawEmptyBoard() {
		DummyController dummy = new DummyController();
		assertEquals(GameResult.Draw, game.play(dummy, dummy));
	}

	@Test
	public void testSimpleGameWhiteWin() {
		// T1 H
		// #B
		// W#
		// T1 T
		// ##
		// W#
		game.spawn(Chesspiece.Bishop, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 1, 1);

		DummyController p1 = new DummyController(
			new ArrayDeque<MoveDescriptor> () {{
				addFirst(new MoveDescriptor(new Tcoord(0, 0), 1));
			}}
			, new Tcoord[][]{
				new Tcoord[] { new Tcoord(1, 1) }
			}, new Tcoord(0, 0)
		);

		DummyController p2 = new DummyController(new Tcoord(1, 1));
		assertEquals(GameResult.WhiteWin, game.play(p1, p2));
	}

	@Test
	public void testSimpleGameBlackWin() {
		// T1 H
		// #B
		// W#
		// T1 T
		// WB
		// ##
		// T2 H
		// WB
		// ##
		// T2 T
		// #B
		// ##
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Rook, Role.Base, Faction.Black, 1, 1);

		DummyController p1 = new DummyController(
			new ArrayDeque<MoveDescriptor> () {{
				addFirst(new MoveDescriptor(new Tcoord(0, 0), 0));
			}}
			, new Tcoord[][]{
				new Tcoord[] { new Tcoord(0, 1) }
			}, new Tcoord(0, 0)
		);

		DummyController p2 = new DummyController(
			new ArrayDeque<MoveDescriptor> () {{
				addFirst(new MoveDescriptor(new Tcoord(1, 1), 1));
			}}
			, new Tcoord[][]{
				new Tcoord[] { new Tcoord(0, 1) }
			}, new Tcoord(1, 1)
		);

		assertEquals(GameResult.BlackWin, game.play(p1, p2));
	}
	@Test
	public void testSimpleGameDraw() {
		// T1 H
		// W#
		// #B
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 1);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 1, 0);
		DummyController p1 = new DummyController(new Tcoord(0, 1));
		DummyController p2 = new DummyController(new Tcoord(1, 0));
		assertEquals(GameResult.Draw, game.play(p1, p2));
	}
}
