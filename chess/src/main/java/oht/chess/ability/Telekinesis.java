package oht.chess.ability;
import java.util.Set;
import oht.chess.unit.Actor;
import oht.chess.game.Board;
import oht.chess.Effect;
import oht.chess.game.GameState;
import oht.chess.util.Tcoord;

class Telekinesis implements IAbility
{
	Actor _usr;
	Telekinesis(Actor user) { _usr = user; }

	@Override
	public AbilityTargeter beginUse(GameState state) {
		Set<Tcoord> targets =
			AbilityUtil.filterNonempty(_usr.attackVectors(), _usr.pos(), state.board());

		TargetSet t = new TargetSet(targets, 1);
		return new AbilityTargeter(this, _usr, state, t);
	}

	@Override
	public boolean endUse(AbilityTargeter t) {
		// if (!isValid(t)) { return false; }
		Tcoord tar = t.sets().get(0).targets().iterator().next();
		Tcoord to = t.sets().get(1).targets().iterator().next();
		Board board = t.state().board();
		Effect.move( board.get(tar), to, board );
		return true;
	}

	@Override
	public boolean isComplete(AbilityTargeter t) {
		if (t.set(0).numTargets() == 1)
		{
			if (t.set(1) == null) {
				Set<Tcoord> targets =
					AbilityUtil.filterEmpty(_usr.attackVectors(), _usr.pos(), t.state().board());

				TargetSet n = new TargetSet(targets, 1);
				t.append(n);
			} else if (t.set(1).numTargets() == 1)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isValid(AbilityTargeter t) {
		if (!isComplete(t)) { return false; }
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public String name() { return "Telekinesis"; }

	@Override
	public String description() { return "Move target unit to target square."; }

	@Override public String toString( ) { return name() + ": " + description(); }
}