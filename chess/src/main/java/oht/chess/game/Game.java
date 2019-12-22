package oht.chess.game;

import oht.chess.ability.AbilityUseChecker;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Targeter;
import oht.chess.util.TargeterState;
import oht.chess.util.Tcoord;
/**
 * Peliä suorittava luokka
 */
public class Game extends Board {
	int turnCount;
	private Board board;
	/**
	 * Valkoisten yksiköiden johtaja, jonka menetys tarkoittaa valkoisen häviötä
	 */
	Entity p1Leader = null;
	/**
	 * Mustien yksiköiden johtaja, jonka menetys tarkoittaa mustan häviötä
	 */
	Entity p2Leader = null;

	/**
	 * @param	w	Laudan leveys
	 * @param	h	Laudan korkeus
	 */
	public Game(int w, int h) {
		super(w, h);
		this.turnCount = 0;
		this.board = new Board(w, h);
	}

	/**
	 * @param	width	Laudan leveys
	 * @param	height	Laudan korkeus
	 * @param	w	Valkoisten yksiköiden kokoonpano
	 * @param	b	Mustien yksiköiden kokoonpano
	 */
	public Game(int width, int height, Composition w, Composition b) {
		this(width, height);
		deploy(w, Faction.White);
		deploy(b, Faction.Black);
	}

	void deploy(Composition comp, Faction f) {
		for (int x = 0; x < Math.min(w, comp.width()); x++) {
			for (int y = 0; y < Math.min(h, comp.height()); y++) {
				Entity e = comp.get(x, y);
				if (e == null) {
					continue;
				}
				Tcoord coord = f == Faction.White ? new Tcoord(x, y) : new Tcoord(w - x - 1, h - y - 1);
				spawn(e.base(), e.role(), f, coord);
			}
		}
	}

	boolean canAct(Faction f) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Entity ent = get(x, y);
				if (ent == null || ent.faction() != f) {
					continue;
				}

				for (int i = 0; i < ent.numAbilities(); i++) {
					AbilityUseChecker check = new AbilityUseChecker(ent.getAbility(i), ent, this);
					if (check.result()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Funktio joka suorittaa peliä kunnes toinen pelaajista voittaa, tai pelitilannetta ei pysty muuttamaan.
	 * @param	p1	Valkoisia yksiköitä kontrolloiva pelaaja
	 * @param	p2	Mustia yksiköitä kontrolloiva pelaaja
	 * @return	Pelin lopputulos
	 */
	public GameResult play(IPlayerController p1, IPlayerController p2) {
		if (!canAct(Faction.White) || !canAct(Faction.Black)) {
			return GameResult.Draw;
		}

		if (!assignLeaders(p1, p2)) {
			return GameResult.None;
		}
		GameResult result;

		while (true) {
			if (!canAct(activeFaction())) {
				return GameResult.Draw;
			}

			result = playTurn(p1, p2);
			if (result == GameResult.None) {
				nextTurn();
			} else {
				return result;
			}
		}
	}

	boolean assignLeaders(IPlayerController p1, IPlayerController p2) {
		while (p1Leader == null || p1Leader.faction() != Faction.White) {
			Tcoord coord = p1.nominateLeader(this, Faction.White);
			if (coord == null) {
				return false;
			}
			p1Leader = get(coord);
		}

		while (p2Leader == null || p2Leader.faction() != Faction.Black) {
			Tcoord coord = p2.nominateLeader(this, Faction.Black);
			if (coord == null) {
				return false;
			}
			p2Leader = get(coord);
		}

		return true;
	}

	GameResult playTurn(IPlayerController p1, IPlayerController p2) {
		turn: while (true) {
			MoveDescriptor desc = selectAbility(activeFaction() == Faction.White ? p1 : p2, activeFaction());
			if (desc == null) {
				if (activeFaction() == Faction.White) {
					return GameResult.BlackWin;
				} else {
					return GameResult.WhiteWin;
				}
			}

			Entity origin = get(desc.origin());
			IAbility a = origin.getAbility(desc.key());
			Targeter t = a.beginUse(origin, this);

			do {
				t = (activeFaction() == Faction.White ? p1 : p2).targetAbility(this, a, t);

				if (t == null) {
					continue turn;
				}
			} while (a.isComplete(t, origin, this) == TargeterState.Incomplete);

			if (a.endUse(t, origin, this)) {
				break;
			}
		}

		return isGameOver();
	}

	MoveDescriptor selectAbility(IPlayerController player, Faction f) {
		while (true) {
			MoveDescriptor desc = player.selectAbility(this);
			if (desc == null) {
				return null;
			}

			Entity tar = get(desc.origin());
			if (tar != null && tar.faction() == f && tar.getAbility(desc.key()) != null) {
				return desc;
			}
		}
	}

	GameResult isGameOver() {
		if (p1Leader == null || p1Leader.hp() == 0) {
			if (p2Leader == null || p2Leader.hp() == 0) {
				return GameResult.Draw;
			}
			return GameResult.BlackWin;
		}

		if (p2Leader == null || p2Leader.hp() == 0) {
			return GameResult.WhiteWin;
		}

		return GameResult.None;
	}
	/**
	 * Faction, joka voi suorittaa siirron tällä vuorolla
	 */
	public Faction activeFaction() {
		if (this.turnCount % 2 == 0) {
			return Faction.White;
		}
		return Faction.Black;
	}

	public int turn() {
		return this.turnCount;
	}

	/**
	 * Nostaa vuoronumeroa yhdellä
	 * @return Faction, jonka vuoro uusi vuoro on
	 */
	Faction nextTurn() {
		this.turnCount++;
		return activeFaction();
	}

	private boolean setupNotEquals(Game rhs) {
		return turnCount != rhs.turnCount || w != rhs.w || h != rhs.h
			|| p1Leader != null && !p1Leader.equals(rhs.p1Leader)
			|| p1Leader == null && rhs.p1Leader != null
			|| p2Leader != null && !p2Leader.equals(rhs.p2Leader)
			|| p2Leader == null && rhs.p2Leader != null;
	}

	private boolean contentsEquals(Game rhs) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Entity a = cells[x][y];
				Entity b = rhs.cells[x][y];
				if (a == null && b == null) {
					continue;
				}

				if (a != null && b != null && a.equals(b)) {
					continue;
				} else {
					return false;
				}
			}
		}

		return true;
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

		Game other = (Game) rhs;
		return !setupNotEquals(other) && contentsEquals(other);
	}
}