package oht.chess;
import oht.chess.Actor;
import oht.chess.Board;

public class Effect
{
	public static void damage(Actor src, Actor dst, int amount, Board b)
	{
		System.out.println(src.toString() + " attacks " + dst.toString() + " for " + amount + " damage!");

		int hp = dst.hp();
		dst.setHp( dst.hp() - amount );

		if (amount >= hp)
		{
			// death
			System.out.println(dst.toString() + " dies!");
			b.remove(dst.pos());
		}
		else
		{

		}

		
		// raise event
	}

	public static void move(Actor a, Tcoord moveTo, Board b)
	{
		System.out.println("Moving " + a.toString() + " to " + moveTo.toString() );

		b.remove(a.pos());
		b.emplace(a, moveTo);

		// raise event, movement type enum
	}
}