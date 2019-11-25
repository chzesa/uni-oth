package oht.chess.game;
import oht.chess.util.Tcoord;
import oht.chess.unit.Actor;

public class Board {
	Actor[][] cells;
	int w, h;

	public Board(int width, int height) {
		this.w = width;
		this.h = height;
		this.cells = new Actor[this.w][this.h];
	}

	public boolean emplace(Actor actor, Tcoord coord) {
		if (this.cells[coord.x()][coord.y()] == null) {
			actor.setPos(coord);
			// System.out.println("Emplacing unit " + actor.toString() + " to " + coord.toString());
			this.cells[coord.x()][coord.y()] = actor;
			return true;
		}
		System.out.println("Emplacing unit " + actor.toString() + " to " + coord.toString() + " failed");
		return false;
	}

	public Actor remove(Tcoord coord) {
		Actor a = this.cells[coord.x()][coord.y()];
		this.cells[coord.x()][coord.y()] = null;

		return a;
	}

	public Actor get(Tcoord coord) {
		return get(coord.x(), coord.y());
	}

	public Actor get(int x, int y) {
		return this.cells[x][y];
	}

	public int width() {
		return this.w;
	}

	public int height() {
		return this.h;
	}

	public boolean isOob(Tcoord coord) {
		return coord.x() < 0 || coord.y() < 0 || coord.x() >= this.w || coord.y() >= this.h;
	}
}