package oht.chess.unit;
import java.util.HashSet;
import oht.chess.util.Vector;

class Rook extends Actor {
	Rook(Faction faction) {
		super(Chesspiece.Rook, faction);
	}

	@Override
	public HashSet<Vector> movementVectors() {
		HashSet<Vector> ret = new HashSet<>();
		ret.add(new Vector(1, 0, Integer.MAX_VALUE));
		ret.add(new Vector(-1, 0, Integer.MAX_VALUE));

		ret.add(new Vector(0, 1, Integer.MAX_VALUE));
		ret.add(new Vector(0, -1, Integer.MAX_VALUE));

		return ret;
	}

	@Override
	public HashSet<Vector> attackVectors() {
		return movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♖' : '♜';
	}
}