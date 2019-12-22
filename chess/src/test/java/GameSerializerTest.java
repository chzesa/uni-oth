package oht.chess.game;

import java.util.Random;
import oht.chess.shared.Role;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GameSerializerTest {
	Game g;
	Random rand;

	@Before
	public void setUp() {
		g = new Game(8, 8);
		rand = new Random(21589);
	}

	void spawnRandom(int w, int h) {
		Faction f = Faction.values()[rand.nextInt(Faction.values().length)];
		Chesspiece b = Chesspiece.values()[rand.nextInt(Chesspiece.values().length)];
		Role r = Role.values()[rand.nextInt(Role.values().length)];
		int x = rand.nextInt(w);
		int y = rand.nextInt(h);
		g.spawn(b, r, f, new Tcoord(x, y));
		Entity e = g.get(x, y);
		e.setHp(e.hp() + 1);
	}

	@Test
	public void testSerializeLeaderP1() {
		g.spawn(Chesspiece.King, Role.Base, Faction.White, 0, 0);
		g.p1Leader = g.get(0, 0);

		Game result = GameSerializer.deserialize(GameSerializer.serialize(g));
		assertEquals(g, result);
	}

	@Test
	public void testSerializeLeaderP1NonMatching() {
		g.spawn(Chesspiece.King, Role.Base, Faction.White, 0, 0);
		g.p1Leader = g.get(0, 0);

		Game result = GameSerializer.deserialize(GameSerializer.serialize(g));
		g.p1Leader = null;
		assertNotEquals(g, result);
	}

	@Test
	public void testSerializeLeaderP2() {
		g.spawn(Chesspiece.King, Role.Base, Faction.Black, 0, 0);
		g.p2Leader = g.get(0, 0);

		Game result = GameSerializer.deserialize(GameSerializer.serialize(g));
		assertEquals(g, result);
	}

	@Test
	public void testSerializeLeaderP2NonMatching() {
		g.spawn(Chesspiece.King, Role.Base, Faction.Black, 0, 0);
		g.p2Leader = g.get(0, 0);

		Game result = GameSerializer.deserialize(GameSerializer.serialize(g));
		g.p2Leader = null;
		assertNotEquals(g, result);
	}

	@Test
	public void testSerialize() {
		for (int i = 0; i < 10000; i++) {
			g = new Game(8, 8);
			int num = rand.nextInt(64);
			for (int n = 0; n < num; n++) {
				spawnRandom(8, 8);
			}

			if (rand.nextInt(2) == 1) {

			}

			if (rand.nextInt(2) == 1) {

			}

			String str = GameSerializer.serialize(g);
			Game result = GameSerializer.deserialize(str);
			assertNotNull(result);
			assertEquals(g, result);
		}
	}

	@Test
	public void testSerializeRandomCorrupt() {
		for (int i = 0; i < 10000; i++) {
			g = new Game(4, 4);
			int num = rand.nextInt(16);
			for (int n = 0; n < num; n++) {
				spawnRandom(4, 4);
			}

			String str = GameSerializer.serialize(g);
			StringBuilder sb = new StringBuilder(str);
			int position = rand.nextInt(str.length());
			sb.replace(position, position + 1, "&");
			Game result = GameSerializer.deserialize(sb.toString());
			if (result != null) {
				assertNotEquals(g, result);
			}
		}
	}
}