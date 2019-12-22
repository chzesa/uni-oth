package oht.chess.app;

import oht.chess.ui.cli.ICli;
import oht.chess.ui.cli.MainMenu;

public class Chess {
	private ICli cli;
	public Chess(String[] args) {
		cli = new MainMenu();
	}

	public void run() {
		do {
			cli = cli.draw();
		} while (cli != null);
	}
}
