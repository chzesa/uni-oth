package oht.chess.unit;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import java.util.HashSet;
import oht.chess.util.Vector;

class Knight extends Actor {
	Knight(Faction faction) {
		super(Chesspiece.Knight, faction);
	}

	@Override
	public HashSet<Vector> movementVectors() {
		HashSet<Vector> ret = new HashSet<>();
		ret.add(new Vector(-1, 2, 1)); // up,left
		ret.add(new Vector(1, 2, 1)); // up,right

		ret.add(new Vector(2, 1, 1)); // right, up
		ret.add(new Vector(2, -1, 1)); // right, down

		ret.add(new Vector(1, -2, 1)); // down, right
		ret.add(new Vector(-1, -2, 1)); // down, left

		ret.add(new Vector(-2, -1, 1)); // left, down
		ret.add(new Vector(-2, 1, 1)); // left, up

		return ret;
	}

	@Override
	public HashSet<Vector> attackVectors() {
		return movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♘' : '♞';
	}
}