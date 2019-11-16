package oht.chess;
import java.util.Set;
import oht.chess.Board;
import oht.chess.TargetSet;
import oht.chess.Tcoord;
import oht.chess.Vector;

class Attack implements IAbility
{
	Actor _usr;
	Attack(Actor user) { _usr = user; }

	@Override
	public AbilityTargeter beginUse(GameState state) {
		Set<Tcoord> targets = AbilityUtil.filterHostile(_usr.attackVectors(), _usr, state.board());

		TargetSet t = new TargetSet(targets, 1);
		return new AbilityTargeter(this, _usr, state, t);
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
	public boolean endUse(AbilityTargeter t) {
		// if (!isValid(t)) { return false; }
		Tcoord a = t.sets().get(0).targets().iterator().next();

		Actor target = t.state().board().get(a);

		Effect.damage( _usr, target, _usr.damage(), t.state().board() );
		return true;
	}

	@Override
	public boolean isValid(AbilityTargeter t) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	@Override
	public String name() { return "Attack"; }

	@Override
	public String description() { return "Attack target unit inflicting damage."; }
	
	@Override public String toString( ) { return name() + ": " + description(); }
}