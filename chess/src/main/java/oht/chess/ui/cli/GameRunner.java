package oht.chess.ui.cli;

import oht.chess.game.Game;
import oht.chess.ui.CliClient;

public class GameRunner extends Cli implements ICli {
	Game g;
	public GameRunner(Game g) {
		this.g = g;
	}

	@Override
	public ICli draw() {
		CliClient client = new CliClient();
		client.gameOver(g.play(client, client), g);
		return new MainMenu();
	}
}