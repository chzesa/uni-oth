package oht.chess.ui;
import oht.chess.util.MoveDescriptor;
import oht.chess.ability.AbilityTargeter;
import oht.chess.game.Game;
import oht.chess.ability.IAbility;

public interface IPlayerController {
	public MoveDescriptor selectAbility(Game game);
	public AbilityTargeter targetAbility(Game game, IAbility ability, AbilityTargeter t);
	// Tcoord nominateLeader(GameState state);
}