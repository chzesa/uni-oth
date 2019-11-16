package oht.chess;
import java.util.ArrayList;

class Knight extends Actor
{
	Knight(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Knight, role, coord, faction);
	}

	@Override
	ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
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
	ArrayList<Vector> attackVectors() { return movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♘' : '♞'; }
}