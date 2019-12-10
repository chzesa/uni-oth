package oht.chess.shared;

import oht.chess.util.Targeter;
import oht.chess.util.TargeterState;

public interface IAbility {
	Targeter beginUse(IActor user, IBoard board);
	TargeterState isComplete(Targeter t, IActor user, IBoard board);
	boolean endUse(Targeter t, IActor user, IBoard board);
	boolean isValid(Targeter t, IActor user, IBoard board);
	String name();
	String description();
}