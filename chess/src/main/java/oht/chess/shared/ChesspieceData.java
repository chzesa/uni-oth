package oht.chess.shared;

public class ChesspieceData {
	public static int maxHp(Chesspiece base) {
		switch (base) {
			case Bishop: return 3;
			case King: return 4;
			case Knight: return 5;
			case Pawn: return 2;
			case Queen: return 4;
			case Rook: return 8;
		}
		throw new UnsupportedOperationException();
	}

	public static int damage(Chesspiece base) {
		switch (base) {
			case Bishop: return 3;
			case King: return 4;
			case Knight: return 2;
			case Pawn: return 1;
			case Queen: return 4;
			case Rook: return 5;
		}
		throw new UnsupportedOperationException();
	}
	/**
 	* @return Shakkinappulan kerroin roolin hinnalle
 	*/
	public static int costMulti(Chesspiece base) {
		switch (base) {
			case Bishop: return 3;
			case King: return 4;
			case Knight: return 2;
			case Pawn: return 1;
			case Queen: return 4;
			case Rook: return 5;
		}
		throw new UnsupportedOperationException();
	}
}