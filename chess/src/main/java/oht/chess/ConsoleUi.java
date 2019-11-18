package oht.chess;

import oht.chess.GameState;
import oht.chess.Faction;
import oht.chess.Role;
import java.lang.Iterable;

public class ConsoleUi implements IGui
{	
	Iterable<Tcoord> _highlighted;
	Iterable<Tcoord> _selected;

	public void setSelected(Iterable<Tcoord> coords)
	{
		_highlighted = coords;
	}

	public void setSelectables(Iterable<Tcoord> coords)
	{
		_selected = coords;
	}

	public boolean draw(GameState state)
	{
		System.out.print("\033[H\033[2J");
		System.out.println("Turn " + state.turn() + ", " + state.activeFaction().toString());
		int w = state.board().width();
		int h = state.board().height();

		String[][] output = new String[w + 3][h + 1];
		ConsoleUiCell[][] cells = new ConsoleUiCell[w][h];

		for (int x = 0; x < w; x++)
		{
			for (int y = 0; y < h; y++)
			{
				cells[x][y] = new ConsoleUiCell();
				Actor a = state.board().get(x, y);
				cells[x][y].set(a == null ? ' ' : a.toChar());
			}
		}

		if (_highlighted != null)
		for (Tcoord c : _highlighted)
		{
			cells[c.x()][c.y()].set(ForegroundColor.Purple);
			if (cells[c.x()][c.y()]._c == ' ')
			{
				cells[c.x()][c.y()].set('●');
			}
		}

		if (_selected != null)
		for (Tcoord c : _selected)
		{
			cells[c.x()][c.y()].set(ForegroundColor.Cyan);
			if (cells[c.x()][c.y()]._c == ' ')
			{
				cells[c.x()][c.y()].set('●');
			}
		}

		for (int x = 0; x < w; x++)
		{
			for (int y = h - 1; y > -1; y--)
			{
				String repr = " ";
				Actor a = state.board().get(x,y);
				output[x + 2][state.board().height() - y - 1] = a == null ? " " : "" + a.toChar();
			}
		}

		// Spacing
		for (int x = 0; x < w + 3; x++)
		{
			for (int y = 0; y < h + 1; y++)
			{
				output[x][y] = " ";
			}
		}

		// Column labels
		for (int x = 0; x < w; x++) { output[x + 2][h] = "" +(char)(65 + x); }

		// Newlines
		for (int y = 0; y < h + 1; y++) { output[w + 2][y] = "\n"; }

		// Row labels
		for (int y = 0; y < h; y++) { output[0][h - 1 - y] = "" + (y + 1); }

		// units
		for (int x = 0; x < w; x++)
		{
			for (int y = h - 1; y > -1; y--)
			{

				String repr = " ";
				Actor a = state.board().get(x,y);
				// output[x + 2][state.board().height() - y - 1] = a == null ? " " : actorStr(a);
				output[x + 2][state.board().height() - y - 1] = cells[x][y].toString();
			}
		}

		// print
		for (int y = 0; y < h + 1; y++)
		{
			String out = "";
			for (int x = 0; x < w + 3; x++)
			{
				out += output[x][y];
			}
			System.out.print(out);
		}

		return false;
	}
}