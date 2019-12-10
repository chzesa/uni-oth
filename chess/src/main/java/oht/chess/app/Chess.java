package oht.chess.app;

import java.util.Scanner;
import oht.chess.ui.cli.ICli;
import oht.chess.ui.cli.MainMenu;

public class Chess {
	ICli cli;
	public Chess(String[] args) {
		cli = new MainMenu(new Scanner(System.in));
	}

	public void run() {
		do {
			cli = cli.draw();
		} while (cli != null);
	}
}
