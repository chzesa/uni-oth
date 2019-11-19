package oht.chess;
import java.util.ArrayList;

public class Actor extends Unit
{
	protected Tcoord _pos;
	protected Faction _faction;
	protected int _hp;
	protected int _maxHp;
	protected int _damage;
	protected Vector _fwd;
	protected AbilitySet _abilities;

	protected void initAbilities(Role role)
	{
		switch(role)
		{
			case Base: _abilities = new BaseRole(this);
				break;
			case Kineticist: _abilities = new KineticistRole(this);
				break;
			case Fey: _abilities = new FeyRole(this);
				break;
			default: throw new UnsupportedOperationException();
		}
	}

	protected Actor(Chesspiece base, Role role, Tcoord coord, Faction faction)
	{
		super(base, role);
		_pos = coord;
		_faction = faction;

		if (_faction == Faction.Black)
		{
			_fwd = new Vector(0, -1, 1);
		} else {
			_fwd = new Vector(0, 1, 1);
		}

		_maxHp = ChesspieceData.maxHp(_base);
		_hp = _maxHp;
		_damage = ChesspieceData.damage(_base);
		initAbilities(role);
	}

	public Tcoord pos() { return _pos; }

	void setPos(Tcoord coord) { _pos = coord; }
	public Faction faction() { return _faction; }

	public int hp() { return _hp; }
	int setHp(int hp)
	{
		_hp = Math.min(0, Math.max(_maxHp, hp));
		return _hp;
	}

	public int damage() { return _damage; }

	public ArrayList<Vector> movementVectors() { throw new UnsupportedOperationException(); }
	public ArrayList<Vector> attackVectors() { throw new UnsupportedOperationException(); }
	Vector forward() { return _fwd; }

	public IAbility ability(int index)
	{
		IAbility a = _abilities.get(index);
		if (a == null) { return new Move(this); } // todo
		return a;
	}

	public int numAbilities() { return _abilities.numAbilities(); }

	@Override
	public String toString()
	{
		return _faction.toString() + " "+ _role.toString() +" " + _base.toString() 
			+ "; pos " + _pos.toString() + "; hp " + _hp + "/" + _maxHp + "; dmg " + _damage;
	}

	public char toChar() { return '?'; }
}