package oht.chess.ui.cli;

import java.util.ArrayList;
import oht.chess.game.Game;
import oht.chess.game.GameResult;
import oht.chess.game.IPlayerController;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Targeter;
import oht.chess.util.Tcoord;
import oht.chess.ui.IUi;

public class CliClient implements IUi, IPlayerController {
	ConsolePlayer player = new ConsolePlayer();
	ConsoleUi ui = new ConsoleUi();

	@Override
	public boolean draw(Game game) {
		return ui.draw(game);
	}

	@Override
	public void setSelected(Iterable<Tcoord> coords) {
		ui.setSelected(coords);
	}

	@Override
	public void setSelectables(Iterable<Tcoord> coords) {
		ui.setSelectables(coords);
	}

	@Override
	public MoveDescriptor selectAbility(Game game) {
		ui.setSelected(new ArrayList<>());
		ui.setSelectables(new ArrayList<>());
		ui.draw(game);
		return player.selectAbility(game);
	}

	@Override
	public Targeter targetAbility(Game game, IAbility ability, Targeter t) {
		ui.setSelectables(t.selectable());
		ui.setSelected(t.targeted());
		ui.draw(game);
		return player.targetAbility(game, ability, t);
	}

	@Override
	public Tcoord nominateLeader(Game game, Faction f) {
		draw(game);
		return player.nominateLeader(game, f);
	}

	@Override
	public void gameOver(GameResult result, Game game) {
		ui.setSelected(new ArrayList<>());
		ui.setSelectables(new ArrayList<>());
		ui.gameOver(result, game);
	}
}