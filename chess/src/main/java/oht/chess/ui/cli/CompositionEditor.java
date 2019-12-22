package oht.chess.ui.cli;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import oht.chess.game.Composition;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import oht.chess.shared.IActor;
import oht.chess.shared.Role;
import oht.chess.util.Tcoord;

public class CompositionEditor extends Cli implements ICli {
	Composition comp;
	ICli ret;
	public CompositionEditor(Composition comp, ICli ret) {
		this.comp = comp;
		this.ret = ret;
	}

	void printCommands() {
		println("Commands:");
		println("rename <name>");
		println("delete");
		println("add <coord> <role> <piece>");
		println("remove <coord>");
		println("return");
		println("help");
		println("<coord>");
	}

	void printInfo() {
		println("Possible roles:");
		for (Role r : Role.values()) {
			println("\t" + r);
		}

		println("Possible pieces:");
		for (Chesspiece c : Chesspiece.values()) {
			println("\t" + c);
		}
	}

	void printHelp() {
		println("Editing " + comp.toString());
		CliUtil.print(comp, null, null);
		println("Enter \"help\" for help.");
		print("> ");
	}

	static Pattern rp = Pattern.compile("^rename\\s*(\\S*)\\s*$", Pattern.CASE_INSENSITIVE);
	boolean doRename(String s) {
		Matcher m = rp.matcher(s);
		if (!m.matches()) {
			return false;
		}
		if (m.group(1).length() > 0) {
			comp.setName(m.group(1));
		}
		return true;
	}

	static Pattern dp = Pattern.compile("^delete$", Pattern.CASE_INSENSITIVE);
	boolean doDelete(String s) {
		Matcher m = dp.matcher(s);
		if (!m.matches()) {
			return false;
		}

		Composition.manager().remove(comp);
		return true;
	}

	static Pattern ap = Pattern.compile("^add\\s*(\\S*)\\s*(\\S*)\\s*(\\S*)\\s*$", Pattern.CASE_INSENSITIVE);
	boolean doAdd(String s) {
		Matcher m = ap.matcher(s);
		if (!m.matches()) {
			return false;
		}
		try {
			Tcoord coord = CliUtil.parseString(m.group(1));
			Role r = Role.valueOf(m.group(2));
			Chesspiece p = Chesspiece.valueOf(m.group(3));
			comp.spawn(p, r, Faction.White, coord);
		} catch(IllegalArgumentException e) {
		}

		return true;
	}

	static Pattern rmp = Pattern.compile("^remove\\s*(\\S*)\\s*$", Pattern.CASE_INSENSITIVE);
	boolean doRemove(String s) {
		Matcher m = rmp.matcher(s);
		if (!m.matches()) {
			return false;
		}

		Tcoord coord = CliUtil.parseString(m.group(1));
		println(coord.toString());
		comp.remove(coord);

		return true;
	}

	static Pattern ep = Pattern.compile("^return\\s*$", Pattern.CASE_INSENSITIVE);
	boolean doExit(String s) {
		Matcher m = ep.matcher(s);
		return m.matches();
	}

	static Pattern hp = Pattern.compile("^help\\s*$", Pattern.CASE_INSENSITIVE);
	boolean doHelp(String s) {
		Matcher m = hp.matcher(s);
		if (!m.matches()) {
			return false;
		}
		printCommands();
		printInfo();
		return true;
	}

	@Override
	public ICli draw() {
		printHelp();
		String s = read();
		if (doRename(s) || doAdd(s) || doRemove(s) || doHelp(s)) {
			return this;
		} else if (doDelete(s) || doExit(s)) {
			return ret;
		}

		Tcoord coord = CliUtil.parseString(s);
		if (coord != null) {
			IActor a = comp.get(coord);
			if (a != null) {
				println(a.toString());
			}
		}

		return this;
	}
}