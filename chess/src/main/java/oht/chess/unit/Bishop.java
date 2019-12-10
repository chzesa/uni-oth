package oht.chess.unit;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import java.util.HashSet;
import oht.chess.util.Vector;

class Bishop extends Actor {
	Bishop(Faction faction) {
		super(Chesspiece.Bishop, faction);
	}

	@Override
	public HashSet<Vector> movementVectors() {
		HashSet<Vector> ret = new HashSet<>();
		ret.add(new Vector(1, 1, Integer.MAX_VALUE));
		ret.add(new Vector(1, -1, Integer.MAX_VALUE));

		ret.add(new Vector(-1, 1, Integer.MAX_VALUE));
		ret.add(new Vector(-1, -1, Integer.MAX_VALUE));

		return ret;
	}

	@Override
	public HashSet<Vector> attackVectors() {
		return movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♗' : '♝';
	}
}