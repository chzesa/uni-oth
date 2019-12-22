package oht.chess.util;

/**
 * Luokka jolla voi esimerkiksi kuvata käyttäjän valitsema yksikkö ja kyvyn indeksi.
 */
public class MoveDescriptor {
	private Tcoord origin;
	private int key;

	public MoveDescriptor(Tcoord origin, int key) {
		this.origin = origin;
		this.key = key;
	}

	public Tcoord origin() {
		return this.origin;
	}
	
	public int key() {
		return this.key;
	}
}