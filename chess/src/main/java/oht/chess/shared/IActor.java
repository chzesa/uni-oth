package oht.chess.shared;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;
import java.util.Set;
/**
 * Interface pelilaudalla liikkuvalle yksikölle.
 */
public interface IActor {
	public Role role();
	public Chesspiece base();
	public Faction faction();

	public Tcoord pos();
	public void setPos(Tcoord coord);

	public int hp();
	public int setHp(int hp);

	public int damage();

	/**
	 * Palauttaa yksikön liikkumista kuvaavat tiedot
	 * Sotilaan tapauksessa siis edessä olevan ruudun.
	 */
	public Set<Vector> movementVectors();
	/**
	 * Palauttaa yksikön hyökkäämistä kuvaavat tiedot.
	 * Sotilaan tapauksessa siis edessä olevat viistosuuntaiset ruudut.
	 */
	public Set<Vector> attackVectors();
	public Vector forward();

	public char toChar();
	public boolean equals(IActor other);
}