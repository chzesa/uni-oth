package oht.chess.ui.cli;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import oht.chess.game.Composition;

public class Cli {
	static Scanner scan;

	void clearScreen() {
		println("\033[H\033[2J");
	}

	String[] readSplit() {
		return read().split("\\s");
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

	Composition pickComposition(boolean allowNew) {
		int offset = allowNew ? 3 : 2;
		List<Composition> comps = Composition.manager().stream().sorted((a, b) -> {
			int result = a.name().compareTo(b.name());
			if (result != 0) {
				return result;
			}
			return Integer.compare(a.value(), b.value());
		}).collect(Collectors.toList());

		while(true) {
			println("[1] Return");
			if (allowNew) {
				println("[2] New");
			}
			println("");

			for (int i = 0; i < comps.size(); i++) {
				Composition c = comps.get(i);
				println("[" + (i + offset) + "] " + c.toString());
			}

			print("> ");
			try {
				int i = Integer.parseInt(read());
				if (i == 1) {
					return null;
				} else if (allowNew && i == 2) {
					Composition comp = new Composition();
					Composition.manager().add(comp);
					return comp;
				}

				if (i - offset >= 0 && i - offset < comps.size()) {
					return comps.get(i - offset);
				}
			} catch(Exception e) {
				return null;
			}
		}
		
	}
}