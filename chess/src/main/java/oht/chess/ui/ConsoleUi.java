package oht.chess.ui;

import oht.chess.game.Game;
import oht.chess.game.GameResult;
import oht.chess.shared.IActor;
import oht.chess.util.Tcoord;

public class ConsoleUi implements IGui {
	Iterable<Tcoord> selected;
	Iterable<Tcoord> selectable;

	public void setSelected(Iterable<Tcoord> coords) {
		selected = coords;
	}

	public void setSelectables(Iterable<Tcoord> coords) {
		selectable = coords;
	}

	public boolean draw(Game game) {
		// System.out.print("\033[H\033[2J");
		System.out.println("Turn " + game.turn() + ", " + game.activeFaction().toString());
		
		CliUtil.print(game, selectable, selected);

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