package oht.chess.game;
import oht.chess.util.MoveDescriptor;
import oht.chess.ability.AbilityTargeter;
import oht.chess.ability.IAbility;
import oht.chess.unit.Faction;
import oht.chess.util.Tcoord;

public interface IPlayerController {
	public MoveDescriptor selectAbility(Game game);
	public AbilityTargeter targetAbility(Game game, IAbility ability, AbilityTargeter t);
	public Tcoord nominateLeader(Game game, Faction f);
}