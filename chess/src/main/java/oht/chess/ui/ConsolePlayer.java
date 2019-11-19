package oht.chess.ui;
import java.util.Scanner;
import java.util.regex.*;
import oht.chess.ability.AbilityTargeter;
import oht.chess.unit.Actor;
import oht.chess.game.GameState;
import oht.chess.ability.IAbility;
import oht.chess.util.Tcoord;

public class ConsolePlayer implements IPlayerController
{
	static Pattern _digits = Pattern.compile("^\\d*$");
	Actor _selected;
	IAbility _ability;

	@Override
	public MoveDescriptor selectAbility(GameState state)
	{
		_selected = null;
		_ability = null;
		Scanner scan = new Scanner(System.in);

		MoveDescriptor ret;

		read: while (true)
		{
			if (_selected == null)
			{
				System.out.println("Select a unit and ability (e.g.: \"B3 1\").");
			} else {
				System.out.println("Selected: " + _selected.toString());
				for (int i = 0; i < _selected.numAbilities(); i++)
				{
					System.out.println( i + ": " + _selected.ability(i).toString());
				}
			}

			System.out.println("Type \"forfeit\" to quit the game.");
			System.out.print("> ");
			String input = scan.nextLine();
			String[] splinput = input.split("\\s");

			for (String s : splinput)
			{
				s = s.trim().toLowerCase();

				if (s.equals("help") || s.equals("?"))
				{
					continue;
				}
				else if ( s.equals("quit") || s.equals("q"))
				{
					System.out.println("Enter \"forfeit\" to exit the game.");
				}
				else if (s.equals("forfeit"))
				{
					return null;
				}
				else if (_digits.matcher(s).matches())
				{
					if (_selected == null) { continue; }
					int i = Integer.parseInt(s);
					_ability = _selected.ability(i);
					if (_ability != null)
					{
						return new MoveDescriptor(_selected.pos(), i);
					}
				}
				else
				{
					Tcoord parsed = ControllerUtil.parseString(s);
					if (parsed != null)
					{
						_selected = state.board().get(parsed);
					}
				}
			}
		}
	}

	@Override
	public AbilityTargeter targetAbility(GameState state, IAbility ability, AbilityTargeter t) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Using " + ability.toString());
		System.out.println("Select [ " + t.current().minTargets() +" - "+t.current().maxTargets() + " ] targets.");
		System.out.println("Type \"Cancel\" or \"Quit\" to cancel the ability.");
		System.out.print("> ");
		String input = scan.nextLine();
		String[] splinput = input.split("\\s");

		for (String s : splinput)
		{
			s = s.trim().toLowerCase();

			if (s.equals("help") || s.equals("?"))
			{
				continue;
			}
			else if (s.equals("cancel") || s.equals("c") || s.equals("quit") || s.equals("q"))
			{
				return null;
			}
			else
			{
				Tcoord parsed = ControllerUtil.parseString(s);

				if (parsed != null) { t.current().toggle(parsed); }
			}
		}

		return t;
	}
}