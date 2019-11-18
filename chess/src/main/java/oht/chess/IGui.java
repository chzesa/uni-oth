package oht.chess;
import java.lang.Iterable;

interface IGui
{
	public boolean draw(GameState state);
	public void setSelected(Iterable<Tcoord> coords);
	public void setSelectables(Iterable<Tcoord> coords);
}