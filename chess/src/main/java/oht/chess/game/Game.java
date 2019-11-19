package oht.chess.game;

import oht.chess.game.GameState;
import oht.chess.ui.IPlayerController;

public class Game
{
	GameState _state;

	public Game(IPlayerController p1, IPlayerController p2)
	{

	}

	GameState state() { return _state; }
}