import java.util.Random;
import oht.chess.game.Game;
import oht.chess.unit.Faction;
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
}
