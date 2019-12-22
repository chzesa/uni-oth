package oht.chess.game;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.IBoard;
import oht.chess.shared.Role;
import oht.chess.util.Tcoord;

/**
 * Referenssitoteutus IBoard rajapinnalle.
 */
class Board implements IBoard {
	protected Entity[][] cells;
	protected int w, h;

	protected Board(int width, int height) {
		this.w = width;
		this.h = height;
		this.cells = new Entity[this.w][this.h];
	}

	protected boolean emplace(Entity actor, Tcoord coord) {
		if (isOob(coord) || actor == null) {
			return false;
		}

		if (this.cells[coord.x()][coord.y()] == null) {
			actor.setPos(coord);
			this.cells[coord.x()][coord.y()] = actor;
			return true;
		}
		return false;
	}

	public Entity remove(Tcoord coord) {
		if (isOob(coord)) {
			return null;
		}

		Entity a = this.cells[coord.x()][coord.y()];
		this.cells[coord.x()][coord.y()] = null;

		return a;
	}

	public Entity get(Tcoord coord) {
		if (isOob(coord)) {
			return null;
		}

		return get(coord.x(), coord.y());
	}

	public Entity get(int x, int y) {
		if (isOob(x, y)) {
			return null;
		}

		return this.cells[x][y];
	}

	public int width() {
		return this.w;
	}

	public int height() {
		return this.h;
	}

	public boolean isOob(Tcoord coord) {
		if (coord == null) {
			return true;
		}
		return isOob(coord.x(), coord.y());
	}

	protected boolean isOob(int x, int y) {
		return x < 0 || y < 0 || x >= this.w || y >= this.h;
	}

	@Override
	public boolean move(Tcoord src, Tcoord dst) {
		if (isOob(src) || isOob(dst)) {
			return false;
		}

		if (get(src) == null || get(dst) != null) {
			return false;
		}

		Entity actor = remove(src);
		return emplace(actor, dst);
	}

	@Override
	public boolean spawn(Chesspiece base, Role role, Faction faction, Tcoord coord) {
		return spawn(base, role, faction, coord.x(), coord.y());
	}

	@Override
	public boolean spawn(Chesspiece base, Role role, Faction faction, int x, int y) {
		if (isOob(x, y) || get(x, y) != null) {
			return false;
		}

		Entity ent = new Entity(base, role, faction);
		return emplace(ent, new Tcoord(x, y));
	}
}