package oht.chess.ui;
import oht.chess.util.Tcoord;

public class MoveDescriptor
{
	Tcoord _origin;
	int _key;

	public MoveDescriptor(Tcoord origin, int key)
	{
		_origin = origin;
		_key = key;
	}

	public Tcoord origin() { return _origin; }
	public int key() { return _key; }
}