package oht.chess.ability;

import java.util.ArrayList;
import java.util.List;
import oht.chess.unit.Actor;
import oht.chess.game.GameState;

public class AbilityTargeter {
	IAbility abty;
	Actor usr;
	GameState state;

	ArrayList<TargetSet> sets = new ArrayList<>();
	TargetSet wSet;

	AbilityTargeter(IAbility ability, Actor user, GameState state, TargetSet initialSet) {
		this.abty = ability;
		this.usr = user;
		this.state = state;
		this.wSet = initialSet;
		this.sets.add(this.wSet);
	}

	void append(TargetSet nextSet) {
		this.wSet = nextSet;
		this.sets.add(this.wSet);
	}

	public TargetSet current() {
		return this.wSet;
	}
	public boolean isComplete() {
		return this.wSet.isComplete() && this.abty.isComplete(this);
	}
	int numSets() {
		return this.sets.size();
	}
	List<TargetSet> sets() {
		return this.sets;
	}
	TargetSet set(int i) {
		if (i < 0 || i >= this.sets.size()) {
			return null;
		}
		return this.sets.get(i);
	}

	Actor user() {
		return this.usr;
	}
	GameState state() {
		return this.state;
	}
}