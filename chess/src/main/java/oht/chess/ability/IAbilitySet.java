package oht.chess.ability;

public interface IAbilitySet
{
	public int numAbilities();
	public IAbility getAbility(int i);
	public Role role();
}