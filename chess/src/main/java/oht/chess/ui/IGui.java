package oht.chess.ui;
import oht.chess.game.Game;
import oht.chess.game.GameResult;
import oht.chess.util.Tcoord;

interface IGui {
	public boolean draw(Game game);
	public void setSelected(Iterable<Tcoord> coords);
	public void setSelectables(Iterable<Tcoord> coords);
	public void gameOver(GameResult result, Game game);
}