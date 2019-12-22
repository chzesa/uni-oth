package oht.chess.game;

import java.util.Set;
import oht.chess.ability.RoleFactory;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.shared.IAbilitySet;
import oht.chess.shared.IActor;
import oht.chess.shared.Role;
import oht.chess.unit.UnitFactory;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

/**
 * Yhdiste IActor ja IAbilityset rajapinnoille.
 */
public class Entity implements IActor, IAbilitySet {
	IActor actor;
	IAbilitySet abilities;
	Role role;

	Entity(Chesspiece base, Role role, Faction faction) {
		actor = UnitFactory.make(base, faction);
		abilities = RoleFactory.make(role);
		this.role = role;
	}

	@Override
	public Role role() {
		return role;
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

	@Override
	public String toString() {
		return actor.toString();
	}

	@Override
	public boolean equals(IActor other) {
		return actor.equals(other) && this.role.equals(other.role());
	}

	@Override
	public boolean equals(Object rhs) {
		if (this == rhs) {
			return true;
		}
		if (rhs == null) {
			return false;
		}

		if (getClass() != rhs.getClass()) {
			return false;
		}

		Entity other = (Entity) rhs;
		return actor.equals(other.actor) && this.role.equals(other.role());
	}

	@Override
	public IAbility getAbility(String s) {
		return abilities.getAbility(s);
	}
}