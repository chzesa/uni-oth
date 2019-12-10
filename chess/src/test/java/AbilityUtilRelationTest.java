import java.util.Set;
import oht.chess.ability.AbilityUtil;
import oht.chess.shared.Role;
import oht.chess.game.Game;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.IActor;
import oht.chess.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AbilityUtilRelationTest {
	IActor actor;
	Game game;

	Tcoord f1 = new Tcoord(0, 0);
	Tcoord f2 = new Tcoord(0, 2);
	Tcoord f3 = new Tcoord(1, 2);

	Tcoord h1 = new Tcoord(0, 1);
	Tcoord h2 = new Tcoord(1, 0);
	Tcoord h3 = new Tcoord(2, 1);
	
	@Before
	public void setUp() {
		game = new Game(3, 3);

		// FF#
		// HOH
		// FH#

		Tcoord origin = new Tcoord(1, 1);
		game.spawn(Chesspiece.King, Role.Base, Faction.Black, origin);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, f1);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, f2);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, f3);

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, h1);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, h2);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, h3);

		actor = game.get(origin);
	}

	@Test
	public void testFilterFriendly() {
		Set<Tcoord> result = AbilityUtil.filterFriendly(actor.movementVectors(), actor, game);

		assertEquals(3, result.size());
		assertTrue(result.contains(f1));
		assertTrue(result.contains(f2));
		assertTrue(result.contains(f3));
	}

	@Test
	public void testFilterHostile() {
		Set<Tcoord> result = AbilityUtil.filterHostile(actor.movementVectors(), actor, game);

		assertEquals(3, result.size());
		assertTrue(result.contains(h1));
		assertTrue(result.contains(h2));
		assertTrue(result.contains(h3));
	}
		
}