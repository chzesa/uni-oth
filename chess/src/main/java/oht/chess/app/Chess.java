package oht.chess.app;

import oht.chess.game.Game;
import oht.chess.unit.Faction;
import oht.chess.util.Tcoord;
import oht.chess.unit.Chesspiece;
import oht.chess.ability.Role;
import java.util.Random;
import oht.chess.game.GameSerializer;
import oht.chess.io.FileHandler;
import oht.chess.ui.CliClient;

public class Chess {
	static Role[] roles = Role.values();
	static Random rand = new Random();
	static Role randRole() {
		return roles[rand.nextInt(roles.length)];
	}

	Game game;
	CliClient client;

	public Chess(String[] args) {
		client = new CliClient();

		if (args.length > 0) {
			game = GameSerializer.deserialize(FileHandler.readToString(args[0]));
		}

		if (game != null) {
			return;
		}

		game = new Game(8, 8);

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
		client.gameOver(game.play(client, client), game);
	}
}
