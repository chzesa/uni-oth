package oht.chess.ui;
import oht.chess.game.GameState;
import oht.chess.util.Tcoord;

interface IGui {
	public boolean draw(GameState state);
	public void setSelected(Iterable<Tcoord> coords);
	public void setSelectables(Iterable<Tcoord> coords);
}