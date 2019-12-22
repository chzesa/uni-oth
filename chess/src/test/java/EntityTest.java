package oht.chess.game;

import org.junit.Test;
import static org.junit.Assert.*;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.Role;


public class EntityTest {
	@Test
	public void testEquals() {
		Entity e1 = new Entity(Chesspiece.Pawn, Role.Base, Faction.White);
		Entity e2 = new Entity(Chesspiece.Pawn, Role.Base, Faction.White);
		assertEquals(e1, e2);
	}
}