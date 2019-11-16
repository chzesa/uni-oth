package oht.chess;
import java.util.ArrayList;

class Rook extends Actor
{
	Rook(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Rook, role, coord, faction);
	}

	@Override
	ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
		ret.add(new Vector(1, 0, Integer.MAX_VALUE));
		ret.add(new Vector(-1, 0, Integer.MAX_VALUE));

		ret.add(new Vector(0, 1, Integer.MAX_VALUE));
		ret.add(new Vector(0, -1, Integer.MAX_VALUE));

		return ret;
	}

	@Override
	ArrayList<Vector> attackVectors() { return movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♖' : '♜'; }
}