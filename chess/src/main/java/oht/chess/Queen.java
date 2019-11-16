package oht.chess;
import java.util.ArrayList;

class Queen extends Actor
{
	Queen(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Queen, role, coord, faction);
	}

	@Override
	ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
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
	ArrayList<Vector> attackVectors() { return movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♕' : '♛'; }
}