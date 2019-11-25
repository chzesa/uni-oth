package oht.chess.unit;
import oht.chess.ability.Role;
import oht.chess.util.Tcoord;
import oht.chess.util.Vector;
import java.util.Set;

class Actor extends Unit implements IActor {
	protected Tcoord pos;
	protected Faction faction;
	protected int hp;
	protected int maxHp;
	protected int damage;
	protected Vector forward;

	protected Actor(Chesspiece base, Faction faction) {
		super(base, Role.Base);
		this.pos = new Tcoord(0, 0);
		this.faction = faction;

		if (this.faction == Faction.Black) {
			this.forward = new Vector(0, -1, 1);
		} else {
			this.forward = new Vector(0, 1, 1);
		}

		this.maxHp = ChesspieceData.maxHp(this.base);
		this.hp = this.maxHp;
		this.damage = ChesspieceData.damage(this.base);
		// initAbilities(role);
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

	public Set<Vector> movementVectors() {
		throw new UnsupportedOperationException();
	}

	public Set<Vector> attackVectors() {
		throw new UnsupportedOperationException();
	}

	public Vector forward() {
		return this.forward;
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