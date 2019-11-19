package oht.chess.ui;
import oht.chess.ability.AbilityTargeter;
import oht.chess.GameState;
import oht.chess.ability.IAbility;

public interface IPlayerController
{
	public MoveDescriptor selectAbility(GameState state);
	public AbilityTargeter targetAbility(GameState state, IAbility ability, AbilityTargeter t);
	// Tcoord nominateLeader(GameState state);
}