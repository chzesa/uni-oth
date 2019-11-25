package oht.chess.unit;
import oht.chess.unit.Unit;
import oht.chess.unit.ChesspieceData;
import oht.chess.unit.Chesspiece;
import oht.chess.ability.Role;
import oht.chess.ability.IAbility;
import oht.chess.ability.BaseRole;
import oht.chess.ability.FeyRole;
import oht.chess.ability.KineticistRole;
import oht.chess.ability.Move;
import oht.chess.ability.AbilitySet;
import java.util.ArrayList;
import oht.chess.game.Faction;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;

public class Actor extends Unit {
	protected Tcoord pos;
	protected Faction faction;
	protected int hp;
	protected int maxHp;
	protected int damage;
	protected Vector forward;
	protected AbilitySet abilitys;

	protected void initAbilities(Role role) {
		switch (role) {
			case Base: this.abilitys = new BaseRole(this);
				break;
			case Kineticist: this.abilitys = new KineticistRole(this);
				break;
			case Fey: this.abilitys = new FeyRole(this);
				break;
			default: throw new UnsupportedOperationException();
		}
	}

	protected Actor(Chesspiece base, Role role, Tcoord coord, Faction faction) {
		super(base, role);
		this.pos = coord;
		this.faction = faction;

		if (this.faction == Faction.Black) {
			this.forward = new Vector(0, -1, 1);
		} else {
			this.forward = new Vector(0, 1, 1);
		}

		this.maxHp = ChesspieceData.maxHp(this.base);
		this.hp = this.maxHp;
		this.damage = ChesspieceData.damage(this.base);
		initAbilities(role);
	}

	public Tcoord pos() {
		return this.pos;
	}

	public void setPos(Tcoord coord) {
		this.pos = coord;
	}

	public Faction faction() {
		return this.faction;
	}

	public int hp() {
		return this.hp;
	}

	public int setHp(int hp) {
		this.hp = Math.min(0, Math.max(this.maxHp, hp));
		return this.hp;
	}

	public int damage() {
		return this.damage;
	}

	public ArrayList<Vector> movementVectors() {
		throw new UnsupportedOperationException();
	}

	public ArrayList<Vector> attackVectors() {
		throw new UnsupportedOperationException();
	}

	Vector forward() {
		return this.forward;
	}

	public IAbility ability(int index) {
		IAbility a = this.abilitys.get(index);
		if (a == null) {
			return new Move(this);
		} // todo
		return a;
	}

	public int numAbilities() {
		return this.abilitys.numAbilities();
	}

	@Override
	public String toString() {
		return this.faction.toString() + " " + this.role.toString() + " " + this.base.toString() 
			+ "; pos " + this.pos.toString() + "; hp " + this.hp + "/" + this.maxHp + "; dmg " + this.damage;
	}

	public char toChar() {
		return '?';
	}
}