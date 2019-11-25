import oht.chess.ability.Role;
import oht.chess.game.Faction;
import oht.chess.unit.Actor;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.UnitFactory;
import oht.chess.util.Tcoord;
import java.util.Random;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UnitFactoryTest {
	Actor _unit;
	Random _rand;
	@Before
	public void setUp() {
		_rand = new Random(5239);
	}

	@Test
	public void makePawn() {
		_unit = UnitFactory.make(Chesspiece.Pawn, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Chesspiece.Pawn, _unit.base());
	}

	@Test
	public void makeRook() {
		_unit = UnitFactory.make(Chesspiece.Rook, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Chesspiece.Rook, _unit.base());
	}

	@Test
	public void makeKnight() {
		_unit = UnitFactory.make(Chesspiece.Knight, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Chesspiece.Knight, _unit.base());
	}

	@Test
	public void makeBishop() {
		_unit = UnitFactory.make(Chesspiece.Bishop, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Chesspiece.Bishop, _unit.base());
	}

	@Test
	public void makeKing() {
		_unit = UnitFactory.make(Chesspiece.King, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Chesspiece.King, _unit.base());
	}

	@Test
	public void makeQueen() {
		_unit = UnitFactory.make(Chesspiece.Queen, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Chesspiece.Queen, _unit.base());
	}

	@Test
	public void setCoordinate() {
		for (int i = 0; i < 1000; i++)
		{
			int x = _rand.nextInt();
			int y = _rand.nextInt();
			_unit = UnitFactory.make(Chesspiece.Pawn, Role.Base, new Tcoord(x, y), Faction.White);
			assertEquals(x, _unit.pos().x());
			assertEquals(y, _unit.pos().y());
		}
	}

	@Test
	public void setFaction() {
		_unit = UnitFactory.make(Chesspiece.Pawn, Role.Base, new Tcoord(0, 0), Faction.White);
		assertEquals(Faction.White, _unit.faction());

		_unit = UnitFactory.make(Chesspiece.Pawn, Role.Base, new Tcoord(0, 0), Faction.Black);
		assertEquals(Faction.Black, _unit.faction());
	}
}
