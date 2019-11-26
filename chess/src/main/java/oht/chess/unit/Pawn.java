package oht.chess.unit;
import java.util.HashSet;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

class Pawn extends Actor {
	Pawn(Faction faction) {
		super(Chesspiece.Pawn, faction);
	}

	@Override
	public HashSet<Vector> movementVectors() {
		HashSet<Vector> ret = new HashSet<>();
		ret.add(this.forward);
		return ret;
	}

	@Override
	public HashSet<Vector> attackVectors() {
		HashSet<Vector> ret = new HashSet<>();
		Vector left = new Vector(Vector.add(this.forward, new Tcoord(-1, 0)), 1);
		Vector right = new Vector(Vector.add(this.forward, new Tcoord(1, 0)), 1);

		ret.add(left);
		ret.add(right);

		return ret;
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♙' : '♟';
	}
}