package oht.chess.ui.cli;

public class PlayGameMenu extends DigitMenu implements ICli {
	@Override
	void printHelp() {
		println("[1] New Game");
		println("[2] Load Game");
		println("[3] Return");
		print("> ");
	}

	@Override
	ICli onDigit(int i) {
		switch (i) {
			case 1: return new NewGameMenu();
			case 2: return new LoadGameMenu();
			case 3: return new MainMenu();
		}

		return this;
	}
}