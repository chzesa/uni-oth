package oht.chess.game;
import oht.chess.game.Board;
import oht.chess.game.Faction;

public class GameState
{
	int _turnCount;
	Board _b;

	public GameState(int w, int h)
	{
		_turnCount = 0;
		_b = new Board(w, h);
	}

	public Faction activeFaction()
	{
		if (_turnCount % 2 == 0) { return Faction.White; }
		return Faction.Black;
	}

	public int turn() { return _turnCount; }
	public Board board() { return _b; }
	public Faction nextTurn() { _turnCount++; return activeFaction(); }
}