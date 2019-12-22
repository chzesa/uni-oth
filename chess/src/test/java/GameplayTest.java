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

public class GameplayTest {
	Game game;
	Random rand;

	@Before
	public void setUp() {
		game = new Game(2, 2);
		rand = new Random(52623);
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

	@Test
	public void testSimpleGameMoveIntoDraw() {
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
		// W#
		// #B
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 1, 1);

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
				addFirst(new MoveDescriptor(new Tcoord(1, 1), 0));
			}}
			, new Tcoord[][]{
				new Tcoord[] { new Tcoord(1, 0) }
			}, new Tcoord(1, 1)
		);

		assertEquals(GameResult.Draw, game.play(p1, p2));
	}
}