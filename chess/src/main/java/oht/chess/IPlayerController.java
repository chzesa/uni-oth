package oht.chess;
import oht.chess.MoveDescriptor;

interface IPlayerController
{
	MoveDescriptor selectAbility(GameState state);
	AbilityTargeter targetAbility(GameState state, IAbility ability, AbilityTargeter t);
	// Tcoord nominateLeader(GameState state);
}