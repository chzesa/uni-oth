package oht.chess.ui;
import oht.chess.AbilityTargeter;
import oht.chess.GameState;
import oht.chess.IAbility;

public interface IPlayerController
{
	public MoveDescriptor selectAbility(GameState state);
	public AbilityTargeter targetAbility(GameState state, IAbility ability, AbilityTargeter t);
	// Tcoord nominateLeader(GameState state);
}