package oht.chess.ui;

import oht.chess.unit.IActor;
import oht.chess.game.Game;
import oht.chess.game.GameResult;
import oht.chess.util.Tcoord;

public class ConsoleUi implements IGui {
	Iterable<Tcoord> highlighted;
	Iterable<Tcoord> selected;

	public void setSelected(Iterable<Tcoord> coords) {
		highlighted = coords;
	}

	public void setSelectables(Iterable<Tcoord> coords) {
		selected = coords;
	}

	public boolean draw(Game game) {
		System.out.print("\033[H\033[2J");
		System.out.println("Turn " + game.turn() + ", " + game.activeFaction().toString());
		int w = game.width();
		int h = game.height();

		String[][] output = new String[w + 3][h + 1];
		ConsoleUiCell[][] cells = new ConsoleUiCell[w][h];

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				cells[x][y] = new ConsoleUiCell();
				IActor a = game.get(x, y);
				cells[x][y].set(a == null ? ' ' : a.toChar());
			}
		}

		if (highlighted != null) {
			for (Tcoord c : highlighted) {
				cells[c.x()][c.y()].set(ForegroundColor.Purple);
				if (cells[c.x()][c.y()].c == ' ') {
					cells[c.x()][c.y()].set('●');
				}
			}
		}

		if (selected != null) {
			for (Tcoord c : selected) {
				cells[c.x()][c.y()].set(ForegroundColor.Cyan);
				if (cells[c.x()][c.y()].c == ' ') {
					cells[c.x()][c.y()].set('●');
				}
			}
		}

		for (int x = 0; x < w; x++) {
			for (int y = h - 1; y > -1; y--) {
				String repr = " ";
				IActor a = game.get(x, y);
				output[x + 2][game.height() - y - 1] = a == null ? " " : "" + a.toChar();
			}
		}

		// Spacing
		for (int x = 0; x < w + 3; x++) {
			for (int y = 0; y < h + 1; y++) {
				output[x][y] = " ";
			}
		}

		// Column labels
		for (int x = 0; x < w; x++) {
			output[x + 2][h] = "" + (char) (65 + x);
		}

		// Newlines
		for (int y = 0; y < h + 1; y++) {
			output[w + 2][y] = "\n";
		}

		// Row labels
		for (int y = 0; y < h; y++) {
			output[0][h - 1 - y] = "" + (y + 1);
		}

		// units
		for (int x = 0; x < w; x++) {
			for (int y = h - 1; y > -1; y--) {
				String repr = " ";
				IActor a = game.get(x, y);
				output[x + 2][game.height() - y - 1] = cells[x][y].toString();
			}
		}

		// print
		for (int y = 0; y < h + 1; y++) {
			String out = "";
			for (int x = 0; x < w + 3; x++) {
				out += output[x][y];
			}
			System.out.print(out);
		}

		return false;
	}

	@Override
	public void gameOver(GameResult result, Game game) {
		draw(game);

		switch (result) {
			case Draw:
				System.out.println("Draw.");
				break;
			case BlackWin:
				System.out.println("Black wins.");
				break;
			case WhiteWin:
				System.out.println("White wins.");
				break;
			case None:
				System.out.println("No result.");
				break;
		}
	}
}