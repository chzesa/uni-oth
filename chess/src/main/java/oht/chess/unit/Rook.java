package oht.chess.unit;
import java.util.ArrayList;
import oht.chess.Actor;
import oht.chess.Chesspiece;
import oht.chess.Faction;
import oht.chess.Role;
import oht.chess.Tcoord;
import oht.chess.Vector;

class Rook extends Actor
{
	Rook(Role role, Tcoord coord, Faction faction)
	{
		super(Chesspiece.Rook, role, coord, faction);
	}

	@Override
	public ArrayList<Vector> movementVectors()
	{
		ArrayList<Vector> ret = new ArrayList<>();
		ret.add(new Vector(1, 0, Integer.MAX_VALUE));
		ret.add(new Vector(-1, 0, Integer.MAX_VALUE));

		ret.add(new Vector(0, 1, Integer.MAX_VALUE));
		ret.add(new Vector(0, -1, Integer.MAX_VALUE));

		return ret;
	}

	@Override
	public ArrayList<Vector> attackVectors() { return movementVectors(); }

	@Override
	public char toChar() { return _faction == Faction.White ? '♖' : '♜'; }
}