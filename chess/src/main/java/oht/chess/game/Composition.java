package oht.chess.game;

import java.util.HashSet;
import java.util.Set;
import oht.chess.shared.IBoard;
import oht.chess.shared.RoleData;
import oht.chess.util.Tcoord;

/**
 * Pelaajan yksikkökokoonpanoa kuvaava luokka
 */
public class Composition extends Board implements IBoard {
	protected String name;
	protected int value;

	static HashSet<Composition> compositions = new HashSet<>();
	public static Set<Composition> manager() {
		return compositions;
	}

	public Composition() {
		super(8, 2);
		value = 0;
		name = "Unnamed Composition";
	}

	public String name() {
		return this.name;
	}

	public boolean setName(String name) {
		if (name == null || name.equals("")) {
			return false;
		}

		this.name = name;
		return true;
	}

	/**
	 * Palauttaa kokoonpanon kokonaispistearvon.
	 */
	public int value() {
		return this.value;
	}

	@Override
	protected boolean emplace(Entity actor, Tcoord coord) {
		boolean result = super.emplace(actor, coord);

		if (result) {
			value += RoleData.totalCost(actor.base(), actor.role());
		}
		
		return result;
	}

	@Override
	public Entity remove(Tcoord coord) {
		Entity e = super.remove(coord);
		if (e != null) {
			value -= RoleData.totalCost(e.base(), e.role());
		}

		return e;
	}

	@Override
	public String toString() {
		return name + " [" + value + "pts]";
	}
}