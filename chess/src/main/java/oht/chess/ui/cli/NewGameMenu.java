package oht.chess.ui.cli;

import java.util.Scanner;
import oht.chess.game.Game;

public class NewGameMenu extends Cli implements ICli {
	public NewGameMenu(Scanner scanner) {
		super(scanner);
	}

	@Override
	public ICli draw() {
		return new GameRunner(scan, Game.rand());
	}
}