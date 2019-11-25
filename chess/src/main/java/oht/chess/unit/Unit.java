package oht.chess.unit;
import oht.chess.ability.Role;

public class Unit {
	Chesspiece base;
	Role role;

	public Unit(Chesspiece base, Role role) {
		this.base = base;
		this.role = role;
	}

	public Chesspiece base() {
		return this.base;
	}

	public Role role() {
		return this.role;
	}
}
