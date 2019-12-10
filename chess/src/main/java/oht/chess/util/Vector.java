package oht.chess.util;

/**
 * Erilaisia pelilaudan liikkeitä esittävä luokka. Vektorin magnitude kertoo kuinka monta 'askelta ' on mahdollista suorittaa vektorin määräämään suuntaan.
 */
public class Vector {
	private int mag;
	private Tcoord dir;

	/**
	 * @param	direction	Ruutu, johon pääsee yhdellä vektorin 'askeleella' origosta.
	 * @param	magntidude	Kuinka monta askelta vektoria pitkin voi maksimissaan ottaa. Arvon tulee olla nollaa suurempi.
	 */
	public Vector(Tcoord direction, int magnitude) {
		if (magnitude <= 0) {
			throw new IllegalArgumentException();
		}
		this.dir = direction;
		this.mag = magnitude;
	}

	/**
	 * @param	x	X-akselin suuntainen muutos yhdellä vektorin askeleella.
	 * @param	y	Y-akselin suuntainen muutos yhdellä vektorin askeleella.
	 * @param	magntidude	Kuinka monta askelta vektoria pitkin voi maksimissaan ottaa. Arvon tulee olla nollaa suurempi.
	 */
	public Vector(int x, int y, int magnitude) {
		this(new Tcoord(x, y), magnitude);
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

	/**
	 * Laskee vektorin ja koordinaatin yhteen. Tulos on koordinaatti, johon pääsee suorittamalla kaikki vektorin sallimat askeleet, kun lähtöpiste on annettu koordinaatti.
	 */
	public static Tcoord add(Vector v, Tcoord t) {
		Tcoord vc = v.dir();
		return new Tcoord(t.x() + vc.x() * v.mag(), t.y() + vc.y() * v.mag());
	}

	/**
	 * Laskee vektorin ja koordinaatin yhteen. Tulos on koordinaatti, johon pääsee suorittamalla kaikki vektorin sallimat askeleet, kun lähtöpiste on annettu koordinaatti.
	 */
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