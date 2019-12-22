package oht.chess.ui;
import java.util.regex.*;
import oht.chess.shared.IActor;
import oht.chess.shared.IBoard;
import oht.chess.util.Tcoord;

public class CliUtil {
	static Pattern stringParse = Pattern.compile("^(\\D)(\\d{1,})$");

	public static Tcoord parseString(String s) {
		s = s.trim().toLowerCase();
		Matcher m = stringParse.matcher(s);

		if (!m.matches()) {
			return null;
		}

		String col = m.group(1);
		String row = m.group(2);
		if (col == null || col == null || col.length() != 1) {
			return null;
		}

		int y = Integer.parseInt(row);
		int x = (int) col.charAt(0);

		if (x < 97 || x > 122) {
			return null;
		}

		return new Tcoord(x - 97, y - 1);
	}
	public static void print(IBoard game) {
		print(game, null, null);
	}

	public static void print(IBoard game, Iterable<Tcoord> selectable, Iterable<Tcoord> selected) {
		int w = game.width();
		int h = game.height();

		String[][] output = new String[w + 3][h + 1];
		ConsoleUiCell[][] cells = new ConsoleUiCell[w][h];

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				cells[x][y] = new ConsoleUiCell();
				IActor a = game.get(new Tcoord(x, y));
				cells[x][y].set(a == null ? ' ' : a.toChar());
			}
		}

		if (selected != null) {
			for (Tcoord c : selected) {
				cells[c.x()][c.y()].set(ForegroundColor.Purple);
				if (cells[c.x()][c.y()].c == ' ') {
					cells[c.x()][c.y()].set('●');
				}
			}
		}

		if (selectable != null) {
			for (Tcoord c : selectable) {
				cells[c.x()][c.y()].set(ForegroundColor.Cyan);
				if (cells[c.x()][c.y()].c == ' ') {
					cells[c.x()][c.y()].set('●');
				}
			}
		}

		for (int x = 0; x < w; x++) {
			for (int y = h - 1; y > -1; y--) {
				String repr = " ";
				IActor a = game.get(new Tcoord(x, y));
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
				IActor a = game.get(new Tcoord(x, y));
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
	}
}