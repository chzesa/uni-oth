package oht.chess.ui.cli;

import java.util.Scanner;

public class CompositionMenu extends Cli implements ICli {
	public CompositionMenu(Scanner scanner) {
		super(scanner);
	}

	void printHelp() {
		println("[1] Return");
		print("> ");
	}

	@Override
	public ICli draw() {
		printHelp();
		for (String s : readSplit()) {
			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				continue;
			}

			switch (i) {
				case 1: return new MainMenu(scan);
			}
		}
                
                return this;
	}
}