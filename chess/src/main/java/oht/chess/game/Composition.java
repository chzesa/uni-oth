package oht.chess.game;

import java.util.ArrayList;
import oht.chess.ability.Role;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.Faction;
import oht.chess.unit.Unit;

public class Composition {
	String name;
	ArrayList<Unit> units;
	int value;

	public Composition() {
		units = new ArrayList<>();
		value = 0;
	}

	public Composition(ArrayList<Unit> units) {
		this.units = units;
		value = 0; // todo
	}

	public boolean addUnit(Chesspiece base, Role role) {
		// price check
		units.add(new Unit(base, role, Faction.Black));
		return true;
	}

	public boolean removeUnit(Chesspiece base, Role role) {
		Unit unit = new Unit(base, role, Faction.Black);
		units.remove(unit);
		// contains ?
		return true;
	}

	public boolean removeUnit(int index) {
		if (index < 0 || index >= units.size()) {
			return false;
		}

		units.remove(index);
		return true;
	}

	public int value() {
		return this.value;
	}

	public int size() {
		return units.size();
	}

	public static Composition defaultComp() {
		Composition comp = new Composition();
		for (int i = 0; i < 8; i++) {
			comp.addUnit(Chesspiece.Pawn, Role.Base);
		}

		for (int i = 0; i < 2; i++) {
			comp.addUnit(Chesspiece.Rook, Role.Base);
			comp.addUnit(Chesspiece.Knight, Role.Base);
			comp.addUnit(Chesspiece.Bishop, Role.Base);
		}

		comp.addUnit(Chesspiece.King, Role.Base);
		comp.addUnit(Chesspiece.Queen, Role.Base);
		return comp;
	}
}