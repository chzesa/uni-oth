import java.util.Random;
import oht.chess.ability.Effect;
import oht.chess.ability.Role;
import oht.chess.game.Entity;
import oht.chess.game.Game;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.Faction;
import oht.chess.util.Tcoord;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class EffectTest {
	Game game;
	Random rand;
	Entity srcEntity;
	Tcoord srcPos;

	@Before
	public void setUp() {
		game = new Game(2, 2);
		rand = new Random(52623);
		srcPos = new Tcoord(0, 0);
		game.spawn(Chesspiece.Rook, Role.Base, Faction.Black, srcPos);
		srcEntity = game.get(srcPos);
	}

	@Test
	public void testMove() {
		Tcoord dst = new Tcoord(1, 1);
		Effect.move(srcEntity, dst, game);

		assertNull(game.get(srcPos));
		assertEquals(srcEntity, game.get(dst));
		assertEquals(dst, srcEntity.pos());
	}

	@Test
	public void testDamage() {
		int baseHp = srcEntity.hp();

		Effect.damage(srcEntity, srcEntity, 1, game);
		assertEquals(baseHp - 1, srcEntity.hp());


		Effect.damage(srcEntity, srcEntity, 2, game);
		assertEquals(baseHp - 3, srcEntity.hp());
	}

	@Test
	public void testOverkill() {
		int baseHp = srcEntity.hp();
		Effect.damage(srcEntity, srcEntity, Integer.MAX_VALUE, game);

		assertEquals(0, srcEntity.hp());
		assertNull(game.get(srcPos));
	}
}