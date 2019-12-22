package oht.chess.ui.cli;

import java.util.Scanner;
import oht.chess.game.Composition;

public class MainMenu extends DigitMenu implements ICli {
	public MainMenu() {
		if (scan == null) {
			scan = new Scanner(System.in);
		}
	}

	@Override
	void printHelp() {
		println("[1] Play Game");
		println("[2] Composition Editor");
		println("[3] Exit");
		print("> ");
	}

	@Override
	ICli onDigit(int i) {
		switch (i) {
			case 1: return new PlayGameMenu();
			case 2: {
				Composition comp = pickComposition(true);
				if (comp == null) {
					break;
				}
				return new CompositionEditor(comp, this);
			}
			case 3: return null;
		}

		return this;
	}
}