package oht.chess.ability;
import java.util.Set;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;
import oht.chess.util.Tcoord;

class Attack implements IAbility {
	@Override
	public AbilityTargeter beginUse(IActor user, IBoard board) {
		Set<Tcoord> targets = AbilityUtil.filterHostile(user.attackVectors(), user, board);
		return new AbilityTargeter(new TargetSet(targets, 1));
	}

	@Override
	public TargeterState isComplete(AbilityTargeter t, IActor user, IBoard board) {
		if (t.size(0) == 1) {
			return TargeterState.Complete;
		}

		return TargeterState.Incomplete;
	}

	@Override
	public boolean endUse(AbilityTargeter t, IActor user, IBoard board) {
		if (!isValid(t, user, board)) {
			return false;
		}

		Tcoord a = t.get(0, 0);
		IActor target = board.get(a);

		Effect.damage(user, target, user.damage(), board);
		return true;
	}

	@Override
	public boolean isValid(AbilityTargeter t, IActor user, IBoard board) {
		if (isComplete(t, user, board) != TargeterState.Complete) {
			return false;
		}

		Tcoord target = t.get(0, 0);
		if (!AbilityUtil.unitCanAttack(user, target, board)) {
			return false;
		}

		IActor actor = board.get(target);
		if (actor == null) {
			return false;
		}

		return user.faction() != actor.faction();
	}

	@Override
	public String name() {
		return "Attack";
	}

	@Override
	public String description() {
		return "Attack target unit inflicting damage.";
	}
	
	@Override
	public String toString() {
		return name() + ": " + description();
	}
}