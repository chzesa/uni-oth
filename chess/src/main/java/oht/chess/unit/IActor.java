package oht.chess.unit;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;
import oht.chess.ability.Role;
import java.util.Set;

public interface IActor {
	public Role role();
	public Chesspiece base();
	public Faction faction();

	public Tcoord pos();
	public void setPos(Tcoord coord);

	public int hp();
	public int setHp(int hp);

	public int damage();

	public Set<Vector> movementVectors();
	public Set<Vector> attackVectors();
	public Vector forward();

	public char toChar();
}