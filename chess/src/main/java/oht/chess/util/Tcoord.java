package oht.chess.util;

public class Tcoord {
	int y;
	int x;

	public Tcoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return this.x;
	}
	
	public int y() {
		return this.y;
	}

	public int distance(Tcoord other) {
		return distance(this, other);
	}
	public static int distance(Tcoord first, Tcoord second) {
		return Math.abs(first.y - second.y)
			+ Math.abs(first.x - second.x);
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

		Tcoord other = (Tcoord) rhs;
		return x() == other.x() && y() == other.y();
	}

	@Override
	public int hashCode() {
		return this.x;
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}