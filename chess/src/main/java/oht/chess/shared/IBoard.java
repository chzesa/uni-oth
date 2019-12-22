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
	/**
	 * Yrittää siirtää lähtöruudussa olevan yksikön kohderuutuun
	 * @param	src	lähtöruutu
	 * @param	dst	kohderuutu
	 * @return True jos siirto onnistui, false muuten
	 */
	public boolean move(Tcoord src, Tcoord dst);
	/**
	 * Yrittää lisätä pelilaudalle parametrein määritellyn yksikön
	 * @return True jos lisäys onnistui, false muuten
	 */
	public boolean spawn(Chesspiece base, Role role, Faction faction, Tcoord coord);
	public boolean spawn(Chesspiece base, Role role, Faction faction, int x, int y);

	public int width();
	public int height();

	/**
	 * @return True, jos annettu koordinaatti on pelilaudan sisällä, false muuten.
	 */
	public boolean isOob(Tcoord coord);
}