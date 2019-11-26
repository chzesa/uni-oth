package oht.chess.ability;
import oht.chess.util.Tcoord;
import oht.chess.unit.IActor;
import oht.chess.game.IBoard;

public class Effect {
	public static void damage(IActor src, IActor dst, int amount, IBoard b) {
		System.out.println(src.toString() + " attacks " + dst.toString() + " for " + amount + " damage!");

		int hp = dst.hp();
		dst.setHp(hp - amount);

		if (amount >= hp) {
			System.out.println(dst.toString() + " dies!");
			b.remove(dst.pos());
		}
	}

	public static void move(IActor a, Tcoord moveTo, IBoard b) {
		System.out.println("Moving " + a.toString() + " to " + moveTo.toString());
		b.move(a.pos(), moveTo);
	}
}