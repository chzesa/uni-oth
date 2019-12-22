package oht.chess.ui.cli;

import oht.chess.game.Game;
import oht.chess.game.GameSerializer;
import oht.chess.io.FileHandler;

public class LoadGameMenu extends Cli implements ICli {
	void printHelp() {
		println("Enter the file path of the save.\nEnter blank to return.");
		print("> ");
	}

	@Override
	public ICli draw() {
		printHelp();
		String input = read();

		if (input.equals("")) {
			return new PlayGameMenu();
		}

		Game g = GameSerializer.deserialize(FileHandler.readToString(input));
		if (g != null) {
			return new GameRunner(g);
		} else {
			println("Failed to load the game.");
		}

		return this;
	}
}