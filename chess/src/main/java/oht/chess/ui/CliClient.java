package oht.chess.ui;

import java.util.ArrayList;
import oht.chess.ability.AbilityTargeter;
import oht.chess.ability.IAbility;
import oht.chess.game.Game;
import oht.chess.game.GameResult;
import oht.chess.game.IPlayerController;
import oht.chess.unit.Faction;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Tcoord;

public class CliClient implements IGui, IPlayerController {
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
	public AbilityTargeter targetAbility(Game game, IAbility ability, AbilityTargeter t) {
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