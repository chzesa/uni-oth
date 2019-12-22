package oht.chess.shared;

import org.junit.Test;

public class ChesspieceDataTest {
	@Test
	public void maxHpForAll() {
		for (Chesspiece value : Chesspiece.values()) {
			ChesspieceData.maxHp(value);
		}
	}

	@Test
	public void damageForAll() {
		for (Chesspiece value : Chesspiece.values()) {
			ChesspieceData.damage(value);
		}
	}

	@Test
	public void costMultiplierForAll() {
		for (Chesspiece value : Chesspiece.values()) {
			ChesspieceData.costMulti(value);
		}
	}	
}