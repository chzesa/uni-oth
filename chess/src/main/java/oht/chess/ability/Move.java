package oht.chess.ability;
import java.util.Set;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;
import oht.chess.util.Tcoord;

class Move implements IAbility {
	@Override
	public AbilityTargeter beginUse(IActor user, IBoard board) {
		Set<Tcoord> targets =
						AbilityUtil.filterEmpty(user.movementVectors(), user.pos(), board);

		return new AbilityTargeter(new TargetSet(targets, 1));
	}

	@Override
	public boolean endUse(AbilityTargeter t, IActor user, IBoard board) {
		if (!isValid(t, user, board)) {
			return false;
		}

		Tcoord dst = t.get(0, 0);
		Effect.move(user, dst, board);
		return true;
	}

	@Override
	public TargeterState isComplete(AbilityTargeter t, IActor user, IBoard board) {
		if (t.size(0) == 1) {
			return TargeterState.Complete;
		}

		return TargeterState.Incomplete;
	}

	@Override
	public boolean isValid(AbilityTargeter t, IActor user, IBoard board) {
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