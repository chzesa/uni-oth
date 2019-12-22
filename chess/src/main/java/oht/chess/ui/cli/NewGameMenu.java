package oht.chess.ui.cli;

import oht.chess.game.Composition;
import oht.chess.game.Game;

public class NewGameMenu extends DigitMenu implements ICli {
	Composition w = null;
	Composition b = null;
	@Override
	void printHelp() {
		println("[1] First player composition: " + (w == null ? "none" : w.toString()));
		println("[2] Second player composition: " + (b == null ? "none" : b.toString()));
		println("[3] Play");
		println("[4] Return");
		print("> ");
	}

	@Override
	ICli onDigit(int i) {
		switch(i) {
			case 1:
				w = pickComposition(false);
				break;
			case 2:
				b = pickComposition(false);
				break;
			case 3: {
				if (w == null || b == null) {
					break;
				}
				return new GameRunner(new Game(8, 8, w, b));
			}

			case 4: return new PlayGameMenu();
		}

		return this;
	}
}