package oht.chess.ui.cli;

import java.util.Scanner;

public class Cli {
	Scanner scan;

	public Cli(Scanner scanner) {
		scan = scanner;
	}

	void clearScreen() {
		println("\033[H\033[2J");
	}

	String[] readSplit() {
		return scan.nextLine().split("\\s");
	}

	String read() {
		return scan.nextLine();
	}

	void println(String s) {
		System.out.println(s);
	}

	void print(String s) {
		System.out.print(s);
	}
}