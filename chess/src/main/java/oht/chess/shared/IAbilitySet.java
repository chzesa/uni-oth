package oht.chess.shared;
/**
 * Interface hahmoluokkien kykyjen tallettamiselle.
 * Interfacen toteuttava olio tulee konstruktoida
 * siten, että tietyllä roolilla on aina tietyt
 * kyvyt.
 */
public interface IAbilitySet {
	public int numAbilities();
	public IAbility getAbility(int i);
	public Role role();
	public IAbility getAbility(String s);
}