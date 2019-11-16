package oht.chess;

public class Tcoord
{
	int _y;
	int _x;

	Tcoord(int x, int y)
	{
		_x = x;
		_y = y;
	}

	int x() { return _x; }
	int y() { return _y; }

	int distance(Tcoord other) { return distance(this, other); }
	static int distance(Tcoord first, Tcoord second)
	{
		return Math.abs(first._y - second._y)
			+ Math.abs(first._x - second._x);
	}

	@Override
	public boolean equals(Object rhs) {
		if (this == rhs) { return true; }
		if (rhs == null) { return false; }
		if (getClass() != rhs.getClass()) { return false; }

		Tcoord other = (Tcoord)rhs;
		return x() == other.x() && y() == other.y();
	}

	@Override
	public int hashCode() { return _x; }

	@Override
	public String toString()
	{
		return "("+ _x + ", " + _y+ ")";
	}
}