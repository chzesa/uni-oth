package oht.chess;
import java.util.ArrayList;

class Bishop extends Actor
{
	Bishop(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Bishop, role, coord, faction);
	}

	@Override
	ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
		ret.add(new Vector(1, 1, Integer.MAX_VALUE));
		ret.add(new Vector(1, -1, Integer.MAX_VALUE));

		ret.add(new Vector(-1, 1, Integer.MAX_VALUE));
		ret.add(new Vector(-1, -1, Integer.MAX_VALUE));

		return ret;
	}

	@Override
	ArrayList<Vector> attackVectors() { return movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♗' : '♝'; }
}