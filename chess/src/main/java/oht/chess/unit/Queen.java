package oht.chess.unit;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import java.util.HashSet;
import oht.chess.util.Vector;

class Queen extends Actor {
	Queen(Faction faction) {
		super(Chesspiece.Queen, faction);
	}

	@Override
	public HashSet<Vector> movementVectors() {
		HashSet<Vector> ret = new HashSet<>();
		ret.add(new Vector(0, 1, Integer.MAX_VALUE)); // up
		ret.add(new Vector(1, 1, Integer.MAX_VALUE)); // upright

		ret.add(new Vector(1, 0, Integer.MAX_VALUE)); // right
		ret.add(new Vector(1, -1, Integer.MAX_VALUE)); //  downright

		ret.add(new Vector(0, -1, Integer.MAX_VALUE)); // down
		ret.add(new Vector(-1, -1, Integer.MAX_VALUE)); //downleft

		ret.add(new Vector(-1, 0, Integer.MAX_VALUE)); // left
		ret.add(new Vector(-1, 1, Integer.MAX_VALUE)); //upleft

		return ret;
	}

	@Override
	public HashSet<Vector> attackVectors() {
		return movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♕' : '♛';
	}
}