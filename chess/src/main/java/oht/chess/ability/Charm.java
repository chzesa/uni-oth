package oht.chess.ability;
import java.util.Set;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;
import oht.chess.util.Tcoord;

class Charm implements IAbility {
	@Override
	public AbilityTargeter beginUse(IActor user, IBoard board) {
		Set<Tcoord> targets =
						AbilityUtil.filterHostile(user.attackVectors(), user, board);

		return new AbilityTargeter(new TargetSet(targets, 1));
	}

	@Override
	public boolean endUse(AbilityTargeter t, IActor user, IBoard board) {
		// if (!isValid(t)) { return false; }
		Tcoord attacker = t.get(0, 0);
		Tcoord target = t.get(1, 0);
		Effect.damage(board.get(attacker), board.get(target), board.get(attacker).damage(), board);
		return true;
	}

	@Override
	public TargeterState isComplete(AbilityTargeter t, IActor user, IBoard board) {
		if (t.size(0) == 1) {
			if (t.size() == 1) {
				IActor target = board.get(t.get(0, 0));
				if (target == null) {
					return TargeterState.Error;
				}
				Set<Tcoord> targets =
								AbilityUtil.filterNonempty(target.attackVectors(), target.pos(), board);

				TargetSet n = new TargetSet(targets, 1);
				t.append(n);
			} else if (t.size(1) == 1) {
				return TargeterState.Complete;
			}
		}

		return TargeterState.Incomplete;
	}

	@Override
	public boolean isValid(AbilityTargeter t, IActor user, IBoard board) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String name() {
		return "Charm";
	}

	@Override
	public String description() {
		return "Charm target enemy unit and have it attack another unit.";
	}

	@Override public String toString() {
		return name() + ": " + description();
	}
}