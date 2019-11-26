package oht.chess.game;
import java.util.HashMap;
import oht.chess.util.Tcoord;
import java.util.regex.*;
import oht.chess.ability.Role;
import oht.chess.unit.Chesspiece;
import oht.chess.unit.Faction;
import oht.chess.unit.IActor;

public class GameSerializer {
	final static String widthKey = "width";
	final static String heightKey = "height";
	final static String turnKey = "turn";
	final static String unitKey = "unit";
	final static String leaderKey = "leader";

	static Pattern rTurn = Pattern.compile("^" + turnKey + "\\s*(\\d*)$");
	static Pattern rWidth = Pattern.compile("^" + widthKey + "\\s*(\\d*)$");
	static Pattern rHeight = Pattern.compile("^" + heightKey + "\\s*(\\d*)$");
	static Pattern rUnit = Pattern.compile("^" + unitKey + " (\\S*) (\\S*) (\\S*) (\\d*) (\\d*) hp (\\d*)$");
	static Pattern rLeader = Pattern.compile("^" + leaderKey + " (\\d*) (\\d*)$");

	HashMap<Tcoord, Entity> units = new HashMap<>();

	Tcoord leaderBlack = null;
	Tcoord leaderWhite = null;

	int height = -1;
	int turn = -1;
	int width = -1;

	private GameSerializer() {

	}

	Game deserialize(String[] lines) {
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

		return g;
	}

	void parseLine(String line) {
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

	void parseTurn(Matcher m) {
		try {
			turn = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
		}
	}

	void parseWidth(Matcher m) {
		try {
			width = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
		}
	}

	void parseHeight(Matcher m) {
		try {
			height = Integer.parseInt(m.group(1));
		} catch (NumberFormatException e) {
		}
	}

	void parseLeader(Matcher m) {

	}

	void parseUnit(Matcher m) {
		String faction = m.group(1);
		String role = m.group(2);
		String base = m.group(3);

		int x, y, hp;
		try {
			x = Integer.parseInt(m.group(4));
			y = Integer.parseInt(m.group(5));
			hp = Integer.parseInt(m.group(6));
		} catch(NumberFormatException e) {
			return;
		}
		
		Faction f;
		Role r;
		Chesspiece b;
		try {
			f = Faction.valueOf(faction);
			r = Role.valueOf(role);
			b = Chesspiece.valueOf(base);
		} catch(IllegalArgumentException e) {
			return;
		}

		Entity e = new Entity(b, r, f);
		e.setPos(new Tcoord(x, y));
		e.setHp(hp);

		units.put(new Tcoord(x, y), e);
	}

	public static String serialize(Game game) {
		StringBuilder ret = new StringBuilder();
		ret.append(turnKey + " " + game.turn() + '\n');
		ret.append(widthKey + " " + game.width() + '\n');
		ret.append(heightKey + " " + game.height() + '\n');

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

	public static Game deserialize(String str) {
		String[] lines = str.split("\n");

		GameSerializer s = new GameSerializer();
		return s.deserialize(lines);
	}
}