package oht.chess.ability;
import oht.chess.shared.IAbility;
import oht.chess.util.Targeter;
import oht.chess.util.TargetSet;
import oht.chess.util.TargeterState;
import java.util.Set;
import oht.chess.shared.IActor;
import oht.chess.shared.IBoard;
import oht.chess.util.Tcoord;

class Charm implements IAbility {
	@Override
	public Targeter beginUse(IActor user, IBoard board) {
		Set<Tcoord> targets =
						AbilityUtil.filterHostile(user.attackVectors(), user, board);

		return new Targeter(new TargetSet(targets, 1));
	}

	@Override
	public boolean endUse(Targeter t, IActor user, IBoard board) {
		if (!isValid(t, user, board)) {
			return false;
		}

		Tcoord attacker = t.get(0, 0);
		Tcoord target = t.get(1, 0);
		Effect.damage(board.get(attacker), board.get(target), board.get(attacker).damage(), board);
		return true;
	}

	@Override
	public TargeterState isComplete(Targeter t, IActor user, IBoard board) {
		if (t.size(0) == 1) {
			if (t.size() == 1) {
				IActor target = board.get(t.get(0, 0));
				if (target == null) {
					return TargeterState.Error;
				}
				Set<Tcoord> targets =
								AbilityUtil.filterNonempty(target.attackVectors(), target.pos(), board);

				TargetSet n = new TargetSet(targets, 1);
				t.push(n);
			} else if (t.size(1) == 1) {
				return TargeterState.Complete;
			}
		}

		return TargeterState.Incomplete;
	}

	@Override
	public boolean isValid(Targeter t, IActor user, IBoard board) {
		if (isComplete(t, user, board) != TargeterState.Complete) {
			return false;
		}

		Tcoord primaryTargetSq = t.get(0, 0);
		Tcoord secondaryTargetSq = t.get(1, 0);
		if (!AbilityUtil.unitCanAttack(user, primaryTargetSq, board)) {
			return false;
		}

		IActor primaryTarget = board.get(primaryTargetSq);
		IActor secondaryTarget = board.get(secondaryTargetSq);

		if (primaryTarget == null || secondaryTarget == null
						|| !AbilityUtil.unitCanAttack(primaryTarget, secondaryTargetSq, board)) {
			return false;
		}

		return user.faction() != primaryTarget.faction();
	}

	@Override
	public String name() {
		return "Charm";
	}

	@Override
	public String description() {
		return "Charm target enemy unit and have it attack another unit.";
	}

	@Override
	public String toString() {
		return name() + ": " + description();
	}
}