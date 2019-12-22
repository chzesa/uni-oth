package oht.chess.ability;
import oht.chess.util.Tcoord;
import oht.chess.shared.IActor;
import oht.chess.shared.IBoard;

/**
 * Apuluokka yleisten pelitilanteen muutosten suorittamiseksi
 */
public class Effect {
	/**
	 * Vähentää kohteen hit pointseja ja poistaa tämän pelistä jos hp tippuu nollaan.
	 */
	public static void damage(IActor dst, int amount, IBoard b) {
		int hp = dst.hp();
		dst.setHp(hp - amount);

		if (amount >= hp) {
			b.remove(dst.pos());
		}
	}

	/**
	 * Yrittää siirtää yksikön kohderuutuun pelilaudalla
	 */
	public static void move(IActor a, Tcoord moveTo, IBoard b) {
		b.move(a.pos(), moveTo);
	}
}