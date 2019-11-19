package oht.chess.util;

import oht.chess.util.Tcoord;

public class Vector
{
	private int _mag;
	private Tcoord _dir;

	public Vector(Tcoord direction, int magnitude)
	{
		// assert mag >= 0
		_dir = direction;
		_mag = magnitude;
	}

	public Vector(int x, int y, int magnitude)
	{
		_dir = new Tcoord(x, y);
		_mag = magnitude;
	}

	public int mag() { return _mag; }
	public Tcoord dir() { return _dir; }
	public int x() { return _dir.x(); }
	public int y() { return _dir.y(); }
	public Vector mirror() { return new Vector(new Tcoord( -_dir.x(), - _dir.y()), _mag); }

	public static Tcoord add(Vector v, Tcoord t)
	{
		Tcoord vc = v.dir();
		return new Tcoord ( t.x() + vc.x() * v.mag(), t.y() + vc.y() * v.mag() );
	}

	public static Tcoord add(Tcoord t, Vector v) { return add(v, t); }
}