package oht.chess.ability;
import oht.chess.shared.IAbility;
import oht.chess.util.Targeter;
import oht.chess.util.TargetSet;
import oht.chess.util.TargeterState;
import java.util.Set;
import oht.chess.shared.IActor;
import oht.chess.shared.IBoard;
import oht.chess.util.Tcoord;

/**
 * Yksiköiden liikuttamisen suorittava kyky.
 */
class Move implements IAbility {
	@Override
	public Targeter beginUse(IActor user, IBoard board) {
		Set<Tcoord> targets =
						AbilityUtil.filterEmpty(user.movementVectors(), user.pos(), board);

		return new Targeter(new TargetSet(targets, 1));
	}

	@Override
	public boolean endUse(Targeter t, IActor user, IBoard board) {
		if (!isValid(t, user, board)) {
			return false;
		}

		Tcoord dst = t.get(0, 0);
		Effect.move(user, dst, board);
		return true;
	}

	@Override
	public TargeterState isComplete(Targeter t, IActor user, IBoard board) {
		if (t == null) {
			return TargeterState.Error;
		}

		if (t.size(0) == 1) {
			return TargeterState.Complete;
		}

		return TargeterState.Incomplete;
	}

	@Override
	public boolean isValid(Targeter t, IActor user, IBoard board) {
		if (isComplete(t, user, board) != TargeterState.Complete) {
			return false;
		}

		Tcoord target = t.get(0, 0);
		if (!AbilityUtil.unitCanMove(user, target, board)) {
			return false;
		}

		if (board.isOob(target)) {
			return false;
		}

		return board.get(target) == null;
	}

	@Override
	public String name() {
		return "Move";
	}

	@Override
	public String description() {
		return "Move unit to target square.";
	}

	@Override public String toString() {
		return name() + ": " + description();
	}
}