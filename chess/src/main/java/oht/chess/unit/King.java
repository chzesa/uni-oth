package oht.chess.unit;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import java.util.HashSet;
import oht.chess.util.Vector;

class King extends Actor {
	King(Faction faction) {
		super(Chesspiece.King, faction);
	}

	@Override
	public HashSet<Vector> movementVectors() {
		HashSet<Vector> ret = new HashSet<>();
		ret.add(new Vector(0, 1, 1)); // up
		ret.add(new Vector(1, 1, 1)); // upright

		ret.add(new Vector(1, 0, 1)); // right
		ret.add(new Vector(1, -1, 1)); //  downright

		ret.add(new Vector(0, -1, 1)); // down
		ret.add(new Vector(-1, -1, 1)); //downleft

		ret.add(new Vector(-1, 0, 1)); // left
		ret.add(new Vector(-1, 1, 1)); // upleft

		return ret;
	}

	@Override
	public HashSet<Vector> attackVectors() {
		return this.movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♔' : '♚';
	}
}