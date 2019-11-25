package oht.chess.util;

import oht.chess.util.Tcoord;

public class Vector {
	private int mag;
	private Tcoord dir;

	public Vector(Tcoord direction, int magnitude) {
		// assert mag >= 0
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
}