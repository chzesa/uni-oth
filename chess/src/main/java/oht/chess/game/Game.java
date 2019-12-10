package oht.chess.game;

import oht.chess.ability.AbilityTargeter;
import oht.chess.ability.AbilityUseChecker;
import oht.chess.ability.IAbility;
import oht.chess.ability.Role;
import oht.chess.ability.TargeterState;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.Faction;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Tcoord;

public class Game extends Board {
	int turnCount;
	Board board;
	Entity p1Leader = null;
	Entity p2Leader = null;

	public Game(int w, int h) {
		super(w, h);
		this.turnCount = 0;
		this.board = new Board(w, h);
	}

	boolean isDraw(Faction f) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Entity ent = get(x, y);
				if (ent == null || ent.faction() != f) {
					continue;
				}

				for (int i = 0; i < ent.numAbilities(); i++) {
					AbilityUseChecker check = new AbilityUseChecker(ent.getAbility(i), ent, this);
					if (check.result()) {
						return false;
					}
				}
			}
		}

		return true;
	}

	public GameResult play(IPlayerController p1, IPlayerController p2) {
		if (!assignLeaders(p1, p2)) {
			return GameResult.None;
		}
		GameResult result;

		while (true) {
			if (isDraw(activeFaction())) {
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
			AbilityTargeter t = a.beginUse(origin, this);

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

	public Faction activeFaction() {
		if (this.turnCount % 2 == 0) {
			return Faction.White;
		}
		return Faction.Black;
	}

	public int turn() {
		return this.turnCount;
	}

	Faction nextTurn() {
		this.turnCount++;
		return activeFaction();
	}

	// Checkstyle finagling
	boolean setupNotEquals(Game rhs) {
		return turnCount != rhs.turnCount || w != rhs.w || h != rhs.h
			|| p1Leader != null && !p1Leader.equals(rhs.p1Leader)
			|| p1Leader == null && rhs.p1Leader != null
			|| p2Leader != null && !p2Leader.equals(rhs.p2Leader)
			|| p2Leader == null && rhs.p2Leader != null;
	}

	// Checkstyle finagling
	boolean contentsEquals(Game rhs) {
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

	// Checkstyle finagling
	private void spawnBlack() {
		for (int i = 0; i < 8; i++) {
			spawn(Chesspiece.Pawn, Role.rand(), Faction.Black, i, 6);
		}

		spawn(Chesspiece.Rook, Role.rand(), Faction.Black, 0, 7);
		spawn(Chesspiece.Knight, Role.rand(), Faction.Black, 1, 7);
		spawn(Chesspiece.Bishop, Role.rand(), Faction.Black, 2, 7);
		spawn(Chesspiece.Queen, Role.rand(), Faction.Black, 3, 7);
		spawn(Chesspiece.King, Role.rand(), Faction.Black, 4, 7);
		spawn(Chesspiece.Bishop, Role.rand(), Faction.Black, 5, 7);
		spawn(Chesspiece.Knight, Role.rand(), Faction.Black, 6, 7);
		spawn(Chesspiece.Rook, Role.rand(), Faction.Black, 7, 7);
	}

	// Checkstyle finagling
	private void spawnWhite() {
		for (int i = 0; i < 8; i++) {
			spawn(Chesspiece.Pawn, Role.rand(), Faction.White, i, 1);
		}

		spawn(Chesspiece.Rook, Role.rand(), Faction.White, 0, 0);
		spawn(Chesspiece.Knight, Role.rand(), Faction.White, 1, 0);
		spawn(Chesspiece.Bishop, Role.rand(), Faction.White, 2, 0);
		spawn(Chesspiece.Queen, Role.rand(), Faction.White, 3, 0);
		spawn(Chesspiece.King, Role.rand(), Faction.White, 4, 0);
		spawn(Chesspiece.Bishop, Role.rand(), Faction.White, 5, 0);
		spawn(Chesspiece.Knight, Role.rand(), Faction.White, 6, 0);
		spawn(Chesspiece.Rook, Role.rand(), Faction.White, 7, 0);
	}

	public static Game rand() {
		Game game = new Game(8, 8);
		game.spawnBlack();
		game.spawnWhite();
		return game;
	}
}