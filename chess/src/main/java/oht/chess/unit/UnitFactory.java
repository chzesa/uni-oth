package oht.chess.unit;

public class UnitFactory {
	public static IActor make(Chesspiece base, Faction faction) {
		switch (base) {
			case Pawn: return new Pawn(faction);
			case Rook: return new Rook(faction);
			case Knight: return new Knight(faction);
			case Bishop: return new Bishop(faction);
			case King: return new King(faction);
			case Queen: return new Queen(faction);
		}

		throw new IllegalArgumentException();
	}
}