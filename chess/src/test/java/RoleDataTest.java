package oht.chess.shared;

import org.junit.Test;

public class RoleDataTest {
	@Test
	public void unitcostForAll() {
		for (Chesspiece c : Chesspiece.values()) {
			for (Role r : Role.values()) {
				RoleData.totalCost(c, r);
			}
		}
	}

	@Test
	public void baseCostForAll() {
		for (Role r : Role.values()) {
			RoleData.baseCost(r);
		}
	}
}