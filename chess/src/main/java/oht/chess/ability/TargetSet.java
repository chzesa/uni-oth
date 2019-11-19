package oht.chess.ability;
import java.util.HashSet;
import java.util.Set;
import oht.chess.util.Tcoord;

public class TargetSet
{
	Set<Tcoord> _refSet;
	HashSet<Tcoord> _wSet = new HashSet<>();
	int _min;
	int _max;
	boolean _lock = false;

	TargetSet(Set<Tcoord> targetables, int min, int max)
	{
		// if targetables < min || targetables < max throw
		_refSet = targetables;
		_min = min;
		_max = max;
	}

	TargetSet(Set<Tcoord> targetables, int num)
	{
		_refSet = targetables;
		_min = num;
		_max = num;
	}

	public boolean add(Tcoord t)
	{
		if (_wSet.size() < _max && _refSet.contains(t))
		{
			return _wSet.add(t);
		}
		return false;
	}

	public boolean remove(Tcoord t)
	{
		return _wSet.remove(t);
	}

	public boolean toggle(Tcoord t)
	{
		if (_wSet.contains(t)) { return remove(t); }
		return add(t);
	}

	public int minTargets() { return _min; }
	public int maxTargets() { return _max; }
	public Set<Tcoord> targets() { return _wSet; }
	public Set<Tcoord> targetables() { return _refSet; }
	public int numTargets() { return _wSet.size(); }
	boolean isComplete() { return _min <= _wSet.size() && _wSet.size() <= _max; }

	@Override
	public String toString()
	{
		String ret = "Target set [ " +_min +" - " + _max + " ]"
			+ "\n\tOptions: ";

		for (Tcoord c : _refSet)
		{
			ret += c + ",";
		}

		ret += "\n\tSelected: ";

		for (Tcoord c : _wSet)
		{
			ret += c + ",";
		}

		return ret;
	}
}