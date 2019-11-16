package oht.chess;
import oht.chess.Actor;

class Board
{
	Actor[][] _board;
	int _w, _h;

	Board(int width, int height)
	{
		_w = width; _h = height;
		_board = new Actor[_w][_h];
	}

	boolean emplace(Actor actor, Tcoord coord)
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

	Actor remove(Tcoord coord)
	{
		Actor a = _board[coord.x()][coord.y()];
		_board[coord.x()][coord.y()] = null;

		return a;
	}

	Actor get(Tcoord coord) { return get(coord.x(), coord.y()); }
	Actor get(int x, int y) { return _board[x][y]; }

	int width() { return _w; }
	int height() { return _h; }
	boolean isOob(Tcoord coord)
	{
		return coord.x() < 0 || coord.y() < 0 || coord.x() >= _w || coord.y() >= _h;
	}
}