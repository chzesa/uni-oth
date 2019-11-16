package oht.chess;
import oht.chess.Tcoord;


class MoveDescriptor
{
	Tcoord _origin;
	int _key;

	MoveDescriptor(Tcoord origin, int key)
	{
		_origin = origin;
		_key = key;
	}

	Tcoord origin() { return _origin; }
	int key() { return _key; }
}