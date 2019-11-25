package oht.chess.game;

import java.util.Set;
import oht.chess.ability.IAbility;
import oht.chess.unit.IActor;
import oht.chess.ability.IAbilitySet;
import oht.chess.ability.Role;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.Faction;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;
import oht.chess.unit.UnitFactory;
import oht.chess.ability.RoleFactory;

public class Entity implements IActor, IAbilitySet
{
	IActor actor;
	IAbilitySet abilities;

	Entity(Chesspiece base, Role role, Faction faction) {
		actor = UnitFactory.make(base, faction);
		abilities = RoleFactory.make(role);
	}

	@Override
	public Role role() {
		return actor.role();
	}

	@Override
	public Chesspiece base() {
		return actor.base();
	}

	@Override
	public Faction faction() {
		return actor.faction();
	}

	@Override
	public Tcoord pos() {
		return actor.pos();
	}

	@Override
	public void setPos(Tcoord coord) {
		actor.setPos(coord);
	}

	@Override
	public int hp() {
		return actor.hp();
	}

	@Override
	public int setHp(int hp) {
		return actor.setHp(hp);
	}

	@Override
	public int damage() {
		return actor.damage();
	}

	@Override
	public Set<Vector> movementVectors() {
		return actor.movementVectors();
	}

	@Override
	public Set<Vector> attackVectors() {
		return actor.attackVectors();
	}

	@Override
	public Vector forward() {
		return actor.forward();
	}

	@Override
	public char toChar() {
		return actor.toChar();
	}

	@Override
	public int numAbilities() {
		return abilities.numAbilities();
	}

	@Override
	public IAbility getAbility(int i) {
		return abilities.getAbility(i);
	}
	
}