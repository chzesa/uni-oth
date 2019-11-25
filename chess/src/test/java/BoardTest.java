import oht.chess.game.Board;
import oht.chess.unit.Faction;
import oht.chess.unit.IActor;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.UnitFactory;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BoardTest {
	Board _board;
	IActor _a;
	IActor _b;

	@Before
	public void setUp() {
		_board = new Board(2, 2);
		_a = UnitFactory.make(Chesspiece.King, new Tcoord(0, 0), Faction.White);
		_b = UnitFactory.make(Chesspiece.Queen, new Tcoord(0, 0), Faction.White);
	}

	@Test
	public void emplaceSetsActorPos() {
		assertTrue(_board.emplace(_a, new Tcoord(1, 1)));
		assertEquals(new Tcoord(1, 1), _a.pos());
	}

	@Test
	public void emplaceUpdatesBoardSate() {
		assertTrue(_board.emplace(_a, new Tcoord(1, 1)));
		assertEquals(_a, _board.get(new Tcoord(1, 1)));
	}

	@Test
	public void emplaceFailsWhenSquareNotEmpty() {
		assertTrue(_board.emplace(_a, new Tcoord(1, 1)));
		assertFalse(_board.emplace(_b, new Tcoord(1, 1)));
	}

	@Test
	public void removeErasesActor() {
		assertTrue(_board.emplace(_a, new Tcoord(1, 1)));
		assertEquals(_a, _board.remove(new Tcoord(1, 1)));
		assertEquals(null, _board.get(new Tcoord(1, 1)));
	}

	@Test
	public void boardOobCheckNegative() {
		assertTrue(_board.isOob(new Tcoord(-1, 0)));
		assertTrue(_board.isOob(new Tcoord(0, -1)));
		assertTrue(_board.isOob(new Tcoord(-1, -1)));
	}

	@Test
	public void boardOobCheckPositive() {
		assertTrue(_board.isOob(new Tcoord(2, 0)));
		assertTrue(_board.isOob(new Tcoord(0, 2)));
		assertTrue(_board.isOob(new Tcoord(2, 2)));
	}

	@Test
	public void boardOobCheckInBounds() {
		assertFalse(_board.isOob(new Tcoord(0, 0)));
		assertFalse(_board.isOob(new Tcoord(1, 0)));
		assertFalse(_board.isOob(new Tcoord(0, 1)));
		assertFalse(_board.isOob(new Tcoord(1, 1)));
	}
}
