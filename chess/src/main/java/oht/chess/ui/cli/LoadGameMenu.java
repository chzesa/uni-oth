package oht.chess.ui.cli;

import java.util.Scanner;
import oht.chess.game.Game;
import oht.chess.game.GameSerializer;
import oht.chess.io.FileHandler;

public class LoadGameMenu extends Cli implements ICli {
	public LoadGameMenu(Scanner scanner) {
		super(scanner);
	}

	void printHelp() {
		println("Enter the file path of the save.\nEnter blank to return.");
		print("> ");
	}

	@Override
	public ICli draw() {
		printHelp();
		String input = read();

		if (input.equals("")) {
			return new PlayGameMenu(scan);
		}

		Game g = GameSerializer.deserialize(FileHandler.readToString(input));
		if (g != null) {
			return new GameRunner(scan, g);
		} else {
			println("Failed to load the game.");
		}

		return this;
	}
}