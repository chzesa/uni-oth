package oht.chess.unit;

import oht.chess.shared.Faction;
import oht.chess.shared.IActor;
import oht.chess.util.Vector;
import static org.junit.Assert.*;
import org.junit.Test;

public class ChesspieceTest {
	public boolean testUnitMovementEqualsAttack(IActor actor) {
		if (actor.movementVectors().size() != actor.attackVectors().size()) {
			return false;
		}

		for (Vector v : actor.movementVectors()) {
			if (!actor.attackVectors().contains(v)) {
				return false;
			}
		}

		return true;
	}

	@Test
	public void testChesspieceMoveAttackEquality() {
		assertTrue(testUnitMovementEqualsAttack(new Rook(Faction.Black)));
		assertTrue(testUnitMovementEqualsAttack(new Knight(Faction.Black)));
		assertTrue(testUnitMovementEqualsAttack(new Bishop(Faction.Black)));
		assertTrue(testUnitMovementEqualsAttack(new King(Faction.Black)));
		assertTrue(testUnitMovementEqualsAttack(new Queen(Faction.Black)));
	}
}