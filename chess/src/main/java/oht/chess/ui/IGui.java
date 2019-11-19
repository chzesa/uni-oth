package oht.chess.ui;
import oht.chess.GameState;
import oht.chess.Tcoord;

interface IGui
{
	public boolean draw(GameState state);
	public void setSelected(Iterable<Tcoord> coords);
	public void setSelectables(Iterable<Tcoord> coords);
}