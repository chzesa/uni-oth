package oht.chess.ability;

import oht.chess.unit.Actor;
import oht.chess.game.GameState;
import oht.chess.game.GameState;
import oht.chess.util.Tcoord;
import oht.chess.ability.AbilityTargeter;

public interface IAbility
{
	AbilityTargeter beginUse(GameState state);
	boolean isComplete(AbilityTargeter t);
	boolean endUse(AbilityTargeter t);
	boolean isValid(AbilityTargeter t);
	String name();
	String description();
}