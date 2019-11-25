package oht.chess.game;
import oht.chess.game.Board;
import oht.chess.game.Faction;

public class GameState {
	int turnCount;
	Board board;

	public GameState(int w, int h) {
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

	public Board board() {
		return this.board;
	}

	public Faction nextTurn() {
		this.turnCount++;
		return activeFaction();
	}
}