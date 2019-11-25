package oht.chess.unit;
import java.util.ArrayList;
import oht.chess.game.Faction;
import oht.chess.ability.Role;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

class King extends Actor {
	King(Role role, Tcoord coord, Faction faction) {
		super(Chesspiece.King, role, coord, faction);
	}

	@Override
	public ArrayList<Vector> movementVectors() {
		ArrayList<Vector> ret = new ArrayList<>();
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
	public ArrayList<Vector> attackVectors() {
		return this.movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♔' : '♚';
	}
}