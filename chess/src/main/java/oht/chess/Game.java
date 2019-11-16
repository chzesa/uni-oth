package oht.chess;

import oht.chess.GameState;
import oht.chess.IPlayerController;

public class Game
{
	GameState _state;

	public Game(IPlayerController p1, IPlayerController p2)
	{

	}

	GameState state() { return _state; }
}