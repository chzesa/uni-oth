package oht.chess.app;

import oht.chess.game.Game;
import oht.chess.unit.Faction;
import oht.chess.util.Tcoord;
import oht.chess.unit.Chesspiece;
import oht.chess.ability.Role;
import oht.chess.ability.IAbility;
import oht.chess.ability.AbilityTargeter;
import oht.chess.util.MoveDescriptor;
import oht.chess.ui.ConsoleUi;
import oht.chess.ui.ConsolePlayer;
import java.util.ArrayList;
import java.util.Random;
import oht.chess.ability.TargeterState;
import oht.chess.game.Entity;
import oht.chess.game.GameSerializer;
import oht.chess.io.FileHandler;

public class Chess {
	static Role[] roles = Role.values();
	static Random rand = new Random();
	static Role randRole() {
		return roles[rand.nextInt(roles.length)];
	}

	Game game;
	ConsoleUi ui;
	ConsolePlayer p;

	public Chess(String[] args) {
		ui = new ConsoleUi();
		p = new ConsolePlayer();

		if (args.length > 0) {
			game = GameSerializer.deserialize(FileHandler.readToString(args[0]));
		}

		if (game != null) {
			return;
		}

		game = new Game(p, p, 8, 8);

		for (int i = 0; i < 8; i++) {
			Tcoord pos = new Tcoord(i, 6);
			game.spawn(Chesspiece.Pawn, randRole(), Faction.Black, i, 6);
			game.spawn(Chesspiece.Pawn, randRole(), Faction.White, i, 1);
		}

		game.spawn(Chesspiece.Rook, randRole(), Faction.White, 0, 0);
		game.spawn(Chesspiece.Knight, randRole(), Faction.White, 1, 0);
		game.spawn(Chesspiece.Bishop, randRole(), Faction.White, 2, 0);
		game.spawn(Chesspiece.Queen, randRole(), Faction.White, 3, 0);
		game.spawn(Chesspiece.King, randRole(), Faction.White, 4, 0);
		game.spawn(Chesspiece.Bishop, randRole(), Faction.White, 5, 0);
		game.spawn(Chesspiece.Knight, randRole(), Faction.White, 6, 0);
		game.spawn(Chesspiece.Rook, randRole(), Faction.White, 7, 0);

		game.spawn(Chesspiece.Rook, randRole(), Faction.Black, 0, 7);
		game.spawn(Chesspiece.Knight, randRole(), Faction.Black, 1, 7);
		game.spawn(Chesspiece.Bishop, randRole(), Faction.Black, 2, 7);
		game.spawn(Chesspiece.Queen, randRole(), Faction.Black, 3, 7);
		game.spawn(Chesspiece.King, randRole(), Faction.Black, 4, 7);
		game.spawn(Chesspiece.Bishop, randRole(), Faction.Black, 5, 7);
		game.spawn(Chesspiece.Knight, randRole(), Faction.Black, 6, 7);
		game.spawn(Chesspiece.Rook, randRole(), Faction.Black, 7, 7);
	}

	public void run() {
		turn: while (true) {
			ui.setSelected(new ArrayList<>());
			ui.setSelectables(new ArrayList<>());
			ui.draw(game);
			MoveDescriptor desc = p.selectAbility(game);

			if (desc == null) {
				break;
			}

			System.out.println("User picked: \n\t"
							+ game.get(desc.origin()).toString()
							+ "\n\t" + game.get(desc.origin()).getAbility(desc.key()));

			Entity tar = game.get(desc.origin());

			if (tar == null) {
				continue;
			}

			IAbility a = tar.getAbility(desc.key());
			if (a == null) {
				continue;
			}

			AbilityTargeter t = a.beginUse(tar, game);
			while (a.isComplete(t, tar, game) == TargeterState.Incomplete) {
				ui.setSelectables(t.selectable());
				ui.setSelected(t.targeted());

				ui.draw(game);

				t = p.targetAbility(game, a, t);
				if (t == null) {
					continue turn;
				}
			}

			System.out.println("Using " + a.toString());
			if (a.endUse(t, tar, game)) {
				game.nextTurn();
			}
		}
		System.out.println("Exiting...");
	}
}
