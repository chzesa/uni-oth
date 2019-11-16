package oht.chess;
import oht.chess.Board;
import oht.chess.Faction;

public class GameState
{
	int _turnCount;
	Board _b;

	GameState(int w, int h)
	{
		_turnCount = 0;
		_b = new Board(w, h);
	}

	Faction activeFaction()
	{
		if (_turnCount % 2 == 0) { return Faction.White; }
		return Faction.Black;
	}

	int turn() { return _turnCount; }
	Board board() { return _b; }
	Faction nextTurn() { _turnCount++; return activeFaction(); }
}