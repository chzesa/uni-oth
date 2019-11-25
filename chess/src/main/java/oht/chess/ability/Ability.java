package oht.chess.ability;
import oht.chess.game.IBoard;
import oht.chess.unit.IActor;

class Ability {
	IActor user;
	IBoard board;
	AbilityTargeter targeter;

	protected Ability(IActor user) {
		user = user;
	}
}