import oht.chess.shared.Role;
import oht.chess.game.Entity;
import oht.chess.game.Game;
import oht.chess.shared.Chesspiece;
import oht.chess.shared.Faction;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import oht.chess.ability.AbilityUseChecker;

public class AbilityUseCheckerTest {
	Game game;
	
	@Before
	public void setUp() {
		game = new Game(3, 3);
	}

	@Test
	public void testCanAttackSingleTarget() {
		// ###
		// #H#
		// X##

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 1, 1);
		Entity e = game.get(0, 0);

		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("attack"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testCanNotAttackTargetFriendly() {
		// ###
		// #F#
		// X##

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 1, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("attack"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testCanNotAttackNoTarget() {
		// ###
		// ###
		// X##

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("attack"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testCanMove() {
		// ###
		// #F#
		// X##

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 1, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("move"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testCanMoveMultipleDirections0() {
		// ###
		// #F#
		// XF#

		game.spawn(Chesspiece.King, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 1, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 1, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("move"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testCanMoveMultipleDirections1() {
		// ###
		// F##
		// X##
		game.spawn(Chesspiece.King, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("move"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testCanNotMoveFriendlyInWay() {
		// ###
		// F##
		// X##

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("move"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testCanNotMoveHostileInWay() {
		// ###
		// F##
		// X##

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Base, Faction.Black, 0, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("move"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testCanNotMoveDstOob() {
		// X##
		// ###
		// ###

		game.spawn(Chesspiece.Pawn, Role.Base, Faction.White, 0, 2);
		Entity e = game.get(0, 2);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("move"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testTelekinesisNoTarget() {
		// ###
		// ###
		// X##

		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.White, 0, 0);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("telekinesis"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testTelekinesisFriendlyTarget() {
		// ###
		// ##F
		// #X#

		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.White, 1, 0);
		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.White, 2, 1);
		Entity e = game.get(1, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("telekinesis"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testTelekinesisNoSpace() {
		// ###
		// #H#
		// X##

		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.White, 0, 0);
		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.Black, 1, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("telekinesis"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testTelekinesisHostileTarget() {
		// ###
		// ##H
		// #X#

		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.White, 1, 0);
		game.spawn(Chesspiece.Pawn, Role.Kineticist, Faction.Black, 2, 1);
		Entity e = game.get(1, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("telekinesis"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testCharmNoTarget() {
		// ###
		// ###
		// X##

		game.spawn(Chesspiece.Pawn, Role.Fey, Faction.White, 0, 0);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("charm"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testCharmNoSecondaryTarget() {
		// ###
		// #H#
		// X##

		game.spawn(Chesspiece.Pawn, Role.Fey, Faction.White, 0, 0);
		game.spawn(Chesspiece.Rook, Role.Fey, Faction.Black, 1, 1);
		Entity e = game.get(0, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("charm"), e, game);
		assertFalse(test.result());
	}

	@Test
	public void testCharmCanUseFriendlySecondary() {
		// ###
		// #H#
		// #XF

		game.spawn(Chesspiece.Rook, Role.Fey, Faction.White, 1, 0);
		game.spawn(Chesspiece.Pawn, Role.Fey, Faction.Black, 1, 1);
		game.spawn(Chesspiece.Pawn, Role.Fey, Faction.White, 2, 0);
		Entity e = game.get(1, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("charm"), e, game);
		assertTrue(test.result());
	}

	@Test
	public void testCharmCanUseHostileSecondary() {
		// ###
		// #H#
		// #XH

		game.spawn(Chesspiece.Rook, Role.Fey, Faction.White, 1, 0);
		game.spawn(Chesspiece.Pawn, Role.Fey, Faction.Black, 1, 1);
		game.spawn(Chesspiece.Pawn, Role.Fey, Faction.Black, 2, 0);
		Entity e = game.get(1, 0);
		AbilityUseChecker test = new AbilityUseChecker(e.getAbility("charm"), e, game);
		assertTrue(test.result());
	}
}