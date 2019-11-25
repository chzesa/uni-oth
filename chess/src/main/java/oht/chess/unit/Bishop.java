package oht.chess.unit;
import java.util.ArrayList;
import oht.chess.game.Faction;
import oht.chess.ability.Role;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

class Bishop extends Actor {
	Bishop(Role role, Tcoord coord, Faction faction) {
		super(Chesspiece.Bishop, role, coord, faction);
	}

	@Override
	public ArrayList<Vector> movementVectors() {
		ArrayList<Vector> ret = new ArrayList<>();
		ret.add(new Vector(1, 1, Integer.MAX_VALUE));
		ret.add(new Vector(1, -1, Integer.MAX_VALUE));

		ret.add(new Vector(-1, 1, Integer.MAX_VALUE));
		ret.add(new Vector(-1, -1, Integer.MAX_VALUE));

		return ret;
	}

	@Override
	public ArrayList<Vector> attackVectors() {
		return movementVectors();
	}

	@Override
	public char toChar() {
		return this.faction == Faction.White ? '♗' : '♝';
	}
}