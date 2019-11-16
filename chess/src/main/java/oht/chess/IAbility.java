package oht.chess;

import oht.chess.Actor;
import oht.chess.GameState;
import oht.chess.Tcoord;
import oht.chess.AbilityTargeter;

interface IAbility
{
	AbilityTargeter beginUse(GameState state);
	boolean isComplete(AbilityTargeter t);
	boolean endUse(AbilityTargeter t);
	boolean isValid(AbilityTargeter t);
	String name();
	String description();
}