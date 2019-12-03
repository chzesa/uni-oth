package oht.chess.ability;
import oht.chess.util.Tcoord;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;

public class Effect {
	public static void damage(IActor src, IActor dst, int amount, IBoard b) {
		int hp = dst.hp();
		dst.setHp(hp - amount);

		if (amount >= hp) {
			b.remove(dst.pos());
		}
	}

	public static void move(IActor a, Tcoord moveTo, IBoard b) {
		b.move(a.pos(), moveTo);
	}
}