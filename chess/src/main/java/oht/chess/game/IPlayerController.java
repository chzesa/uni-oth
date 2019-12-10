package oht.chess.game;
import oht.chess.shared.Faction;
import oht.chess.shared.IAbility;
import oht.chess.util.MoveDescriptor;
import oht.chess.util.Targeter;
import oht.chess.util.Tcoord;

public interface IPlayerController {
	public MoveDescriptor selectAbility(Game game);
	public Targeter targetAbility(Game game, IAbility ability, Targeter t);
	public Tcoord nominateLeader(Game game, Faction f);
}