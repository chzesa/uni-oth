package oht.chess.unit;

import oht.chess.shared.Faction;
import oht.chess.shared.IActor;
import oht.chess.util.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ActorTest {
	@Test
	public void inequalityOnDifferentHp() {
		IActor p1 = new Pawn(Faction.White);
		IActor p2 = new Pawn(Faction.White);
		p1.setHp(0);
		assertNotEquals(p1, p2);
	}
	
	@Test
	public void inequalityOnDifferentBase() {
		IActor p1 = new Pawn(Faction.White);
		Rook p2 = new Rook(Faction.White);
		assertNotEquals(p1, p2);
	}

	@Test
	public void inequalityOnDifferentFaction() {
		IActor p1 = new Pawn(Faction.White);
		IActor p2 = new Pawn(Faction.Black);
		assertNotEquals(p1, p2);
	}

	@Test
	public void inequalityOnDifferentPosition() {
		IActor p1 = new Pawn(Faction.White);
		IActor p2 = new Pawn(Faction.Black);
		p2.setPos(new Tcoord(666, 666));
		assertNotEquals(p1, p2);
	}

	@Test
	public void equalOnEqual() {
		IActor p1 = new Pawn(Faction.White);
		assertEquals(p1, p1);
	}
}