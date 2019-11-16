package oht.chess;
import java.util.ArrayList;

class King extends Actor
{
	King(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.King, role, coord, faction);
	}

	@Override
	ArrayList<Vector> movementVectors()
	{
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
	ArrayList<Vector> attackVectors() { return this.movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♔' : '♚'; }
}