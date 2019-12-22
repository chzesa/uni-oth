package oht.chess.game;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Targeter;
import oht.chess.util.Tcoord;

/**
 * Rajapinta jonka peliä pelaavan luokan tulee toteuttaa.
 */
public interface IPlayerController {
	/**
	 * Funktio, jonka avulla peli kysyy minkä yksikön kykyä pelaaja
	 * haluaa vuorollaa käyttää. Kysely toistetaan kunnes pelaaja
	 * valitsee jonkin oman yksikkönsä kyvyn.
	 * @param	game	Nykyinen pelitilanne
	 * @return MoveDescriptor, jossa on pelaajan oman yksikön koorinaatti ja yksikön kyvyn indeksi. Jos null, pelaaja luovuttaa.
	 */
	public MoveDescriptor selectAbility(Game game);

	/**
	 * Funktio jonka avulla peli kysyy yksityiskohdat hahmon kyvyn käytöstä.
	 * Kysely toistetaan kunnes pelaaja palauttaa täyden Targeterin. Jos
	 * palautusarvo on null, peli toistaa selectAbility kyselyn.
	 * @param	game	Nykyinen pelitilanne
	 * @param	ability	Pelaajan aikaisemmin valitsema kyky
	 * @param	t	Kyvyn alustama Targeter jonka pelaajan tulee täyttää.
	 * @return	Täytetty targeter, tai null.
	 */
	public Targeter targetAbility(Game game, IAbility ability, Targeter t);

	/**
	 * Pelaajalta kysytään pelin alussa kuka tämän johtaja on. Kysely toistetaan
	 * kunnes luokka palauttaa koordinaatin jota vastaa ei-tyhjä ruutu, jossa
	 * oleva yksikkö on sama kuin parametrina annettu Faction.
	 */
	public Tcoord nominateLeader(Game game, Faction f);
}