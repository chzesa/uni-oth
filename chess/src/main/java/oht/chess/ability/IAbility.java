package oht.chess.ability;

import oht.chess.unit.IActor;
import oht.chess.game.IBoard;

public interface IAbility {
	AbilityTargeter beginUse(IActor user, IBoard board);
	TargeterState isComplete(AbilityTargeter t, IActor user, IBoard board);
	boolean endUse(AbilityTargeter t, IActor user, IBoard board);
	boolean isValid(AbilityTargeter t, IActor user, IBoard board);
	String name();
	String description();
}