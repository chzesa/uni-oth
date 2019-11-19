package oht.chess.game;
import oht.chess.util.Tcoord;
import oht.chess.unit.Actor;

public class Board
{
	Actor[][] _board;
	int _w, _h;

	public Board(int width, int height)
	{
		_w = width; _h = height;
		_board = new Actor[_w][_h];
	}

	public boolean emplace(Actor actor, Tcoord coord)
	{
		if (_board[coord.x()][coord.y()] == null)
		{
			actor.setPos(coord);
			// System.out.println("Emplacing unit " + actor.toString() + " to " + coord.toString());
			_board[coord.x()][coord.y()] = actor;
			return true;
		}
		System.out.println("Emplacing unit " + actor.toString() + " to " + coord.toString() + " failed" );
		return false;
	}

	public Actor remove(Tcoord coord)
	{
		Actor a = _board[coord.x()][coord.y()];
		_board[coord.x()][coord.y()] = null;

		return a;
	}

	public Actor get(Tcoord coord) { return get(coord.x(), coord.y()); }
	public Actor get(int x, int y) { return _board[x][y]; }

	public int width() { return _w; }
	public int height() { return _h; }
	public boolean isOob(Tcoord coord)
	{
		return coord.x() < 0 || coord.y() < 0 || coord.x() >= _w || coord.y() >= _h;
	}
}