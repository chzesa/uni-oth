package oht.chess.shared;
/**
 * Interface hahmoluokkien kykyjen tallettamiselle.
 * Interfacen toteuttava olio tulee konstruktoida
 * siten, että tietyllä roolilla on aina tietyt
 * kyvyt.
 */
public interface IAbilitySet {
	public int numAbilities();
	/**
	 * @param	i	kyvyn indeksi
	 * @return Kyky joka on indeksissä i sellainen on tässä AbilitySetissä, null muuten
	 */
	public IAbility getAbility(int i);
	public Role role();
	/**
	 * @param	s	kyvyn nimi
	 * @return Kyky jonka nimi on s jos sellainen on tässä AbilitySetissä, null muuten
	 */
	public IAbility getAbility(String s);
}