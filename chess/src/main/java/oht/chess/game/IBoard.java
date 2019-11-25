package oht.chess.game;
import oht.chess.ability.Role;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.Faction;
import oht.chess.unit.IActor;
import oht.chess.util.Tcoord;

public interface IBoard {
	public IActor get(Tcoord coord);
	public IActor remove(Tcoord coord);
	public boolean move(Tcoord src, Tcoord dst);
	public boolean spawn(Chesspiece base, Role role, Faction faction, Tcoord coord);
	public boolean spawn(Chesspiece base, Role role, Faction faction, int x, int y);

	public int width();
	public int height();

	public boolean isOob(Tcoord coord);
}