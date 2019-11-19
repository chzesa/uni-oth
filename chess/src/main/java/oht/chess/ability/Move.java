package oht.chess.ability;
import java.util.Set;
import oht.chess.Actor;
import oht.chess.Effect;
import oht.chess.GameState;
import oht.chess.Tcoord;

// todo make non-public
public class Move implements IAbility
{
	Actor _usr;
	public Move(Actor user) { _usr = user; }

	@Override
	public AbilityTargeter beginUse(GameState state) {
		Set<Tcoord> targets =
			AbilityUtil.filterEmpty(_usr.movementVectors(), _usr.pos(), state.board());

		TargetSet t = new TargetSet(targets, 1);
		return new AbilityTargeter(this, _usr, state, t);
	}

	@Override
	public boolean endUse(AbilityTargeter t) {
		// if (!isValid(t)) { return false; }
		Tcoord a = t.sets().get(0).targets().iterator().next();
		Effect.move( _usr, a, t.state().board() );
		return true;
	}

	@Override
	public boolean isComplete(AbilityTargeter t) {
		if (t.set(0).numTargets() == 1)
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean isValid(AbilityTargeter t) {
		if (!isComplete(t)) { return false; }
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String name() { return "Move"; }

	@Override
	public String description() { return "Move unit to target square."; }

	@Override public String toString( ) { return name() + ": " + description(); }
}