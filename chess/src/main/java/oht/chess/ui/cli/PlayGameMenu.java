package oht.chess.ui.cli;

import java.util.Scanner;

public class PlayGameMenu extends Cli implements ICli {
	public PlayGameMenu(Scanner scanner) {
		super(scanner);
	}

	void printHelp() {
		println("[1] New Game");
		println("[2] Load Game");
		println("[3] Return");
		print("> ");
	}

	@Override
	public ICli draw() {
		printHelp();
		String[] input = readSplit();
		int i = 0;

		for (String s : input) {
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				continue;
			}

			switch (i) {
				case 1: return new NewGameMenu(scan);
				case 2: return new LoadGameMenu(scan);
				case 3: return new MainMenu(scan);
			}
		}

		return this;
	}
}