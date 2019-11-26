package oht.chess.game;

import oht.chess.ui.IPlayerController;
import oht.chess.unit.Faction;

public class Game extends Board {
	int turnCount;
	Board board;

	public Game(int w, int h) {
		super(w, h);
		this.turnCount = 0;
		this.board = new Board(w, h);
	}

	public Game(IPlayerController p1, IPlayerController p2, int w, int h) {
		this(w, h);
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

	public Faction nextTurn() {
		this.turnCount++;
		return activeFaction();
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

		if (turnCount != other.turnCount
						|| w != other.w
						|| h != other.h) {
			return false;
		}

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Entity a = cells[x][y];
				Entity b = other.cells[x][y];
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
}