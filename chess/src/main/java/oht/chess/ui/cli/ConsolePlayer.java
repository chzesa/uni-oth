package oht.chess.ui.cli;
import java.util.regex.*;
import oht.chess.game.Entity;
import oht.chess.game.Game;
import oht.chess.game.GameSerializer;
import oht.chess.game.IPlayerController;
import oht.chess.io.FileHandler;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Targeter;
import oht.chess.util.Tcoord;

public class ConsolePlayer extends Cli implements IPlayerController {
	static Pattern digits = Pattern.compile("^\\d*$");
	Entity selected;
	IAbility ability;

	@Override
	public MoveDescriptor selectAbility(Game game) {
		selected = null;
		ability = null;

		MoveDescriptor ret;

		read: while (true) {
			if (selected == null) {
				println("Select a unit and ability (e.g.: \"B3 1\").");
			} else {
				println("Selected: " + selected.toString());
				for (int i = 0; i < selected.numAbilities(); i++) {
					println(i + ": " + selected.getAbility(i).toString());
				}
			}

			println("Type \"forfeit\" to quit the game.");
			println("Type \"save\" followed by filename to save the current game.");
			print("> ");
			String input = scan.nextLine();
			String[] splinput = input.split("\\s");

			for (int i = 0; i < splinput.length; i++) {
				String s = splinput[i];
				s = s.trim().toLowerCase();

				if (s.equals("help") || s.equals("?")) {
					continue;
				} else if (s.equals("quit") || s.equals("q")) {
					println("Enter \"forfeit\" to exit the game.");
				} else if (s.equals("save")) {
					String p = "";
					for (int j = i + 1; j < splinput.length; j++) {
						p += splinput[j] + " ";
					}
					p = p.trim();
					if (FileHandler.write(GameSerializer.serialize(game), p)) {
						println("Saved game to " + p);
					}
					continue read;
				} else if (s.equals("forfeit")) {
					return null;
				} else if (digits.matcher(s).matches()) {
					if (selected == null) {
						continue;
					}
					int index = Integer.parseInt(s);
					ability = selected.getAbility(index);
					if (ability != null) {
						return new MoveDescriptor(selected.pos(), index);
					}
				} else {
					Tcoord parsed = CliUtil.parseString(s);
					if (parsed != null) {
						selected = game.get(parsed);
					}
				}
			}
		}
	}

	@Override
	public Targeter targetAbility(Game game, IAbility ability, Targeter t) {
		println("Using " + ability.toString());
		println("Select [ " + t.minSize() + " - " + t.maxSize() + " ] targets.");
		println("Type \"Cancel\" or \"Quit\" to cancel the ability.");
		print("> ");
		String input = scan.nextLine();
		String[] splinput = input.split("\\s");

		for (String s : splinput) {
			s = s.trim().toLowerCase();

			if (s.equals("help") || s.equals("?")) {
				continue;
			} else if (s.equals("cancel") || s.equals("c") || s.equals("quit") || s.equals("q")) {
				return null;
			} else {
				Tcoord parsed = CliUtil.parseString(s);

				if (parsed != null) {
					t.toggle(parsed);
				}
			}
		}

		return t;
	}

	@Override
	public Tcoord nominateLeader(Game game, Faction f) {
		while (true) {
			println("Nominate one " + f.toString() + " unit as leader");
			println("Type \"Quit\" to exit.");
			print("> ");
			String input = scan.nextLine();
			String[] splinput = input.split("\\s");


			for (String s : splinput) {
				s = s.trim().toLowerCase();
				if (s.equals("quit")) {
					return null;
				}
				Tcoord parsed = CliUtil.parseString(s);
				if (parsed != null) {
					return parsed;
				}
			}
		}
	}
}