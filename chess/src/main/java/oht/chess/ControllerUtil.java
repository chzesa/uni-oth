package oht.chess;
import oht.chess.Tcoord;
import java.util.regex.*;

class ControllerUtil
{
	static Pattern stringParse = Pattern.compile("^(\\D)(\\d{1,})$");
	static Tcoord parseString(String s)
	{
		s = s.trim().toLowerCase();
		Matcher m = stringParse.matcher(s);

		if (!m.matches()) { return null; }

		String col = m.group(1);
		String row = m.group(2);
		if (col == null || col == null) { return null; }
		if (col.length() != 1) { return null; }
		int y = Integer.parseInt(row);
		int x = (int)col.charAt(0);

		if (x < 97 || x > 122 ) { return null; }

		return new Tcoord(x - 97, y - 1);
	}
}