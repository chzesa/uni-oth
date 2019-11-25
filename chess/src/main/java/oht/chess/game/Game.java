package oht.chess.game;

import oht.chess.ui.IPlayerController;
import oht.chess.unit.Faction;

public class Game extends Board {
	int turnCount;
	Board board;

	public Game(IPlayerController p1, IPlayerController p2, int w, int h) {
		super(w, h);
		this.turnCount = 0;
		this.board = new Board(w, h);
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
}