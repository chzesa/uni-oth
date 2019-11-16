package oht.chess;

import oht.chess.Tcoord;

class Vector
{
	int _mag;
	Tcoord _dir;

	Vector(Tcoord direction, int magnitude)
	{
		// assert mag >= 0
		_dir = direction;
		_mag = magnitude;
	}

	Vector(int x, int y, int magnitude)
	{
		_dir = new Tcoord(x, y);
		_mag = magnitude;
	}

	int mag() { return _mag; }
	Tcoord dir() { return _dir; }
	int x() { return _dir.x(); }
	int y() { return _dir.y(); }
	Vector mirror() { return new Vector(new Tcoord( -_dir.x(), - _dir.y()), _mag); }

	static Tcoord add(Vector v, Tcoord t)
	{
		Tcoord vc = v.dir();
		return new Tcoord ( t.x() + vc.x() * v.mag(), t.y() + vc.y() * v.mag() );
	}

	static Tcoord add(Tcoord t, Vector v) { return add(v, t); }
}