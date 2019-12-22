package oht.chess.unit;
import oht.chess.shared.Faction;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Role;

class Unit {
	Chesspiece base;
	Role role;
	Faction faction;

	public Unit(Chesspiece base, Role role, Faction faction) {
		this.base = base;
		this.role = role;
		this.faction = faction;
	}

	public Chesspiece base() {
		return this.base;
	}

	public Role role() {
		return this.role;
	}

	public Faction faction() {
		return this.faction;
	}
}
