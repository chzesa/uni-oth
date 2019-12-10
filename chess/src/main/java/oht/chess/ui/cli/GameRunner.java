package oht.chess.ui.cli;

import java.util.Scanner;
import oht.chess.game.Game;
import oht.chess.ui.CliClient;

public class GameRunner extends Cli implements ICli {
	Game g;
	public GameRunner(Scanner scanner, Game g) {
		super(scanner);
		this.g = g;
	}

	@Override
	public ICli draw() {
		CliClient client = new CliClient();
		client.gameOver(g.play(client, client), g);
		return new MainMenu(scan);
	}
}