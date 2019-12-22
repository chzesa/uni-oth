package oht.chess.shared;

public class RoleData {
	public static int baseCost(Role role) {
		switch (role) {
			case Base: return 1;
			case Kineticist: return 2;
			case Fey: return 5;
		}

		throw new UnsupportedOperationException();
	}

	static public int totalCost(Chesspiece p, Role r) {
		return baseCost(r) * ChesspieceData.costMulti(p);
	}
}