package oht.chess.ui.cli;

import oht.chess.game.Game;
import oht.chess.game.GameResult;
import oht.chess.shared.IActor;
import oht.chess.util.Tcoord;
import oht.chess.ui.IUi;

public class ConsoleUi extends Cli implements IUi {
	Iterable<Tcoord> selected;
	Iterable<Tcoord> selectable;

	public void setSelected(Iterable<Tcoord> coords) {
		selected = coords;
	}

	public void setSelectables(Iterable<Tcoord> coords) {
		selectable = coords;
	}

	public boolean draw(Game game) {
		clearScreen();
		println("Turn " + game.turn() + ", " + game.activeFaction().toString());
		
		CliUtil.print(game, selectable, selected);
		return false;
	}

	@Override
	public void gameOver(GameResult result, Game game) {
		draw(game);

		switch (result) {
			case Draw:
				println("Draw.");
				break;
			case BlackWin:
				println("Black wins.");
				break;
			case WhiteWin:
				println("White wins.");
				break;
			case None:
				println("No result.");
				break;
		}
	}
}