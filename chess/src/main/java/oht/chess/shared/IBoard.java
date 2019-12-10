package oht.chess.shared;
import oht.chess.util.Tcoord;

/**
 * Interface pelilaudan toiminnallisuuden toteuttaville luokille
 */
public interface IBoard {
	public IActor get(Tcoord coord);

	/**
	 * Poistaa koordinaatissa sijaitsevan yksikön.
	 * @return Poistettu yksikkö tai null jos ruutu
	 * tyhjä.
	 */
	public IActor remove(Tcoord coord);
	public boolean move(Tcoord src, Tcoord dst);
	public boolean spawn(Chesspiece base, Role role, Faction faction, Tcoord coord);
	public boolean spawn(Chesspiece base, Role role, Faction faction, int x, int y);

	public int width();
	public int height();

	public boolean isOob(Tcoord coord);
}