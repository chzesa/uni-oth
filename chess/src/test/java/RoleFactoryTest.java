package oht.chess.ability;

import oht.chess.shared.IAbilitySet;
import oht.chess.shared.Role;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoleFactoryTest {
	IAbilitySet role;

	@Test
	public void baseTest() {
		role = RoleFactory.make(Role.Base);
		assertEquals(Role.Base, role.role());
		assertNotNull(role.getAbility("move"));
		assertNotNull(role.getAbility("attack"));
	}

	@Test
	public void feyTest() {
		role = RoleFactory.make(Role.Fey);
		assertEquals(Role.Fey, role.role());
		assertNotNull(role.getAbility("move"));
		assertNotNull(role.getAbility("attack"));
		assertNotNull(role.getAbility("charm"));
	}

	@Test
	public void kineticistTest() {
		role = RoleFactory.make(Role.Kineticist);
		assertEquals(Role.Kineticist, role.role());
		assertNotNull(role.getAbility("move"));
		assertNotNull(role.getAbility("attack"));
		assertNotNull(role.getAbility("telekinesis"));
	}

	@Test
	public void everyRoleCanBeMade() {
		for (Role value : Role.values()) {
			IAbilitySet result = RoleFactory.make(value);
			assertEquals(value, result.role());
		}
	}
}
