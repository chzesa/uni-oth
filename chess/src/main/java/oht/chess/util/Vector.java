package oht.chess.util;

public class Vector {
	private int mag;
	private Tcoord dir;

	public Vector(Tcoord direction, int magnitude) {
		if (mag <= 0) {
			throw new IllegalArgumentException();
		}
		this.dir = direction;
		this.mag = magnitude;
	}

	public Vector(int x, int y, int magnitude) {
		this.dir = new Tcoord(x, y);
		this.mag = magnitude;
	}

	public int mag() {
		return this.mag;
	}
	public Tcoord dir() {
		return this.dir;
	}
	public int x() {
		return this.dir.x();
	}
	public int y() {
		return this.dir.y();
	}
	public Vector mirror() {
		return new Vector(new Tcoord(-this.dir.x(), -this.dir.y()), this.mag);
	}

	public static Tcoord add(Vector v, Tcoord t) {
		Tcoord vc = v.dir();
		return new Tcoord(t.x() + vc.x() * v.mag(), t.y() + vc.y() * v.mag());
	}

	public static Tcoord add(Tcoord t, Vector v) {
		return add(v, t);
	}

	@Override
	public boolean equals(Object rhs) {
		if (this == rhs) {
			return true;
		}
		if (rhs == null) {
			return false;
		}
		if (getClass() != rhs.getClass()) {
			return false;
		}

		Vector other = (Vector) rhs;
		return x() == other.x() && y() == other.y() && mag() == other.mag();
	}

	@Override
	public int hashCode() {
		return dir.hashCode();
	}
}