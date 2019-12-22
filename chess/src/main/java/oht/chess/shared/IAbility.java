package oht.chess.shared;

import oht.chess.util.Targeter;
import oht.chess.util.TargeterState;

/**
 * Rajapinta, jonka kykyjen on toteutettava
 */
public interface IAbility {
	/**
	 * Aloittaa kyvyn käytön
	 * @param	user	Kykyä käyttäväy yksikkö
	 * @param	game	Nykyinen pelitilanne
	 * @return Targeter, joka on konfiguroitu kyvyn vaatimusten mukaan.
	 */
	Targeter beginUse(IActor user, IBoard board);
	/**
	 * Tarkistaa onkoo parametrina annettu targeter täytetty.
	 * Jos targeter vaatii lisätietoja, palauttaa muokatun targeterin uusilla tiedoilla.
	 * @param	user	Kykyä käyttäväy yksikkö
	 * @param	game	Nykyinen pelitilanne
	 * @return Targeter, joka on konfiguroitu kyvyn vaatimusten mukaan.
	 */
	TargeterState isComplete(Targeter t, IActor user, IBoard board);
	/**
	 * Suorittaa kyvyn parametrina annetun targeterin tietojen mukaisesti jos se on täytetty ja oikeellinen.
	 * @param	user	Kykyä käyttäväy yksikkö
	 * @param	game	Nykyinen pelitilanne
	 * @return true jos kyvyn efektit toteutettiin, false muuten
	 */
	boolean endUse(Targeter t, IActor user, IBoard board);
	/**
	 * Tarkistaa parametrina annetun targeterin oikeellisuuden nykyisessä pelitilanteessa
	 * @param	user	Kykyä käyttäväy yksikkö
	 * @param	game	Nykyinen pelitilanne
	 * @return true jos targeter on täyetty ja oikeellinen, false muuten.
	 */
	boolean isValid(Targeter t, IActor user, IBoard board);
	String name();
	String description();
}