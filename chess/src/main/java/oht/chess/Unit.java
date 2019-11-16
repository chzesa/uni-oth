package oht.chess;
import oht.chess.Chesspiece;
import oht.chess.Role;

public class Unit
{
	Chesspiece _base;
	Role _role;

	public Unit(Chesspiece base, Role role)
	{
		_base = base;
		_role = role;
	}

	public Chesspiece base() { return _base; }
	public Role role() { return _role; }
}
