package oht.chess.game;
import java.util.HashMap;
import java.util.regex.*;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.IActor;
import oht.chess.shared.Role;
import oht.chess.util.Tcoord;

public class GameSerializer {
	private final static String WIDTH_KEY = "width";
	private final static String HEIGHT_KEY = "height";
	private final static String TURN_KEY = "turn";
	private final static String UNIT_KEY = "unit";
	private final static String LEADER_KEY = "lead";

	private static Pattern rTurn = Pattern.compile("^" + TURN_KEY + "\\s*(\\d*)$");
	private static Pattern rWidth = Pattern.compile("^" + WIDTH_KEY + "\\s*(\\d*)$");
	private static Pattern rHeight = Pattern.compile("^" + HEIGHT_KEY + "\\s*(\\d*)$");
	private static Pattern rUnit = Pattern.compile("^" + UNIT_KEY + " (\\S*) (\\S*) (\\S*) (\\d*) (\\d*) hp (\\d*)$");
	private static Pattern rLeader = Pattern.compile("^" + LEADER_KEY + " (\\S*) (\\d*) (\\d*)$");

	private HashMap<Tcoord, Entity> units = new HashMap<>();

	private Tcoord p2Lead = null;
	private Tcoord p1Lead = null;

	private int height = -1;
	private int turn = -1;
	private int width = -1;

	private GameSerializer() {

	}

	private Game deserialize(String[] lines) {
		Matcher m;
		for (String line : lines) {
			parseLine(line);
		}

		if (turn == -1 || width == -1 || height == -1) {
			return null;
		}

		Game g = new Game(width, height);
		g.turnCount = turn;
		for (Entity unit : units.values()) {
			g.spawn(unit.base(), unit.role(), unit.faction(), unit.pos());
			Entity spawned = g.get(unit.pos());
			spawned.setHp(unit.hp());
		}

		g.p1Leader = g.get(p1Lead);
		g.p2Leader = g.get(p2Lead);

		return g;
	}

	private void parseLine(String line) {
		Matcher m = rUnit.matcher(line);
		if (m.matches()) {
			parseUnit(m);
			return;
		}

		m = rTurn.matcher(line);
		if (m.matches()) {
			parseTurn(m);
			return;
		}

		m = rWidth.matcher(line);
		if (m.matches()) {
			parseWidth(m);
			return;
		}

		m = rHeight.matcher(line);
		if (m.matches()) {
			parseHeight(m);
			return;
		}

		m = rLeader.matcher(line);
		if (m.matches()) {
			parseLeader(m);
			return;
		}
	}

	private void parseTurn(Matcher m) {
		try {
			turn = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
		}
	}

	private void parseWidth(Matcher m) {
		try {
			width = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
		}
	}

	private void parseHeight(Matcher m) {
		try {
			height = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
		}
	}

	private void parseLeader(Matcher m) {
		int x, y;
		Faction f;

		try {
			f = Faction.valueOf(m.group(1));
		} catch (IllegalArgumentException e) {
			return;
		}

		try {
			x = Integer.parseInt(m.group(2));
			y = Integer.parseInt(m.group(3));
		} catch (NumberFormatException e) {
			return;
		}

		if (f == Faction.Black) {
			p2Lead = new Tcoord(x, y);
		} else {
			p1Lead = new Tcoord(x, y);
		}
	}

	private void parseUnit(Matcher m) {
		Faction f;
		Role r;
		Chesspiece b;
		int x, y, hp;

		try {
			f = Faction.valueOf(m.group(1));
			r = Role.valueOf(m.group(2));
			b = Chesspiece.valueOf(m.group(3));

			x = Integer.parseInt(m.group(4));
			y = Integer.parseInt(m.group(5));
			hp = Integer.parseInt(m.group(6));
		} catch (Exception e) {
			return;
		}

		Entity e = new Entity(b, r, f);
		e.setPos(new Tcoord(x, y));
		e.setHp(hp);

		units.put(new Tcoord(x, y), e);
	}

	/**
	 * Luo parametrinä syötetystä pelistä tekstimuotoisen esityksen, jonka voi palauttaa myöhemmin deserialize metodilla.
	 */
	public static String serialize(Game game) {
		if (game == null) {
			return "";
		}
		StringBuilder ret = new StringBuilder();
		ret.append(TURN_KEY + " " + game.turn() + '\n');
		ret.append(WIDTH_KEY + " " + game.width() + '\n');
		ret.append(HEIGHT_KEY + " " + game.height() + '\n');
		if (game.p1Leader != null) {
			ret.append(LEADER_KEY + " " + Faction.White + " " + game.p1Leader.pos().x()
							+ " " + game.p1Leader.pos().y() + '\n');
		}

		if (game.p2Leader != null) {
			ret.append(LEADER_KEY + " " + Faction.Black + " " + game.p2Leader.pos().x()
							+ " " + game.p2Leader.pos().y() + '\n');
		}

		for (int x = 0; x < game.width(); x++) {
			for (int y = 0; y < game.height(); y++) {
				IActor a = game.get(x, y);
				if (a == null) {
					continue;
				}
				String s = "unit " + a.faction() + " " + a.role() + " " + a.base() + " "
								+ a.pos().x() + " " + a.pos().y() + " hp " + a.hp() + '\n';
				ret.append(s);
			}
		}

		return ret.toString();
	}

	/**
	 * Palauttaa parametrinä syötetyn merkkijonon kuvaaman pelin, jos merkkijono kuvaa peliä. Muuten null.
	 */
	public static Game deserialize(String str) {
		String[] lines = str.split("\n");

		GameSerializer s = new GameSerializer();
		return s.deserialize(lines);
	}
}