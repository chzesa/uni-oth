package oht.chess.util;

/**
 * Taxicab-geometriaa kuvaava koordinaatti
 */
public class Tcoord {
	private int y;
	private int x;

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