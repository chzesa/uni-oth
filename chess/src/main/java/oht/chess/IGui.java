package oht.chess;
import java.util.List;

interface IGui
{
	public boolean draw(GameState state);
	public void setSelected(List<Tcoord> coords);
	public void setSelectables(List<Tcoord> coords);
}