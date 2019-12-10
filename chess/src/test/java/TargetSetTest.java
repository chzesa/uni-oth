import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Set;
import java.util.HashSet;

import oht.chess.util.TargetSet;
import oht.chess.util.Tcoord;

public class TargetSetTest {
	ArrayList<Tcoord> coords;
	Set<Tcoord> cset;
	TargetSet set;

	@Before
	public void setUp() {
		cset = new HashSet<>();
		coords = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			cset.add(new Tcoord(i, i));
			coords.add(new Tcoord(i, i));
		}

		set = new TargetSet(cset, 1, 10);
	}

	@Test
	public void constructorSetsValues() {
		TargetSet t = new TargetSet(cset, 1, 2);

		assertEquals(1, t.minSize());
		assertEquals(2, t.maxSize());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorFailsOnNegativeMinNumber() {
		new TargetSet(cset, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorFailsOnNegativeMaxNumber() {
		new TargetSet(cset, 0, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorFailsOnMinTargetsGreaterThanMaxTargets() {
		new TargetSet(cset, 2, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorFailsOnNullSet() {
		new TargetSet(null, 1, 2);
	}

	@Test
	public void toggleAddsNewTarget() {
		set.toggle(coords.get(0));
		assertEquals(1, set.size());
		assertEquals(coords.get(0), set.get(0));
	}

	@Test
	public void toggleUsesCorrectInsertionOrder() {
		set.toggle(coords.get(0));
		set.toggle(coords.get(1));

		assertEquals(coords.get(0), set.get(0));
		assertEquals(coords.get(1), set.get(1));
	}

	@Test
	public void toggleRemovesPresentValues() {
		set.toggle(coords.get(0));
		set.toggle(coords.get(0));

		assertEquals(0, set.size());
		assertNull(set.get(0));
	}

	@Test
	public void togglePreservesInsertioOnrderOnRemove() {
		set.toggle(coords.get(0));
		set.toggle(coords.get(1));

		set.toggle(coords.get(0));

		assertEquals(coords.get(1), set.get(0));
		assertEquals(1, set.size());
		assertNull(set.get(1));
	}

	@Test
	public void toggleRemovesLastInSet() {
		set.toggle(coords.get(0));
		set.toggle(coords.get(1));

		set.toggle(coords.get(1));

		assertEquals(coords.get(0), set.get(0));
		assertEquals(1, set.size());
		assertNull(set.get(1));
	}

	@Test
	public void setIsCompleteWhenBoundsFilled() {
		set = new TargetSet(cset, 0, 0);

		assertTrue(set.isComplete());
	}

	@Test
	public void setIsInitiallyIncomplete() {
		assertFalse(set.isComplete());
	}

	@Test
	public void setBecomesCompleteOnMinimumBound() {
		set.toggle(coords.get(0));
		assertTrue(set.isComplete());
	}

	@Test
	public void cannotAddCoordNotInTargetSet() {
		Tcoord c = new Tcoord(1, 0);
		set.toggle(c);
		assertEquals(0, set.size());
	}

	@Test
	public void toggleReturnsChange() {
		assertTrue(set.toggle(coords.get(0)));
		assertTrue(set.toggle(coords.get(0)));
		assertFalse(set.toggle(new Tcoord(0, 1)));
	}

	@Test
	public void cannotAddCoordinateBeyondMaxCapacity() {
		TargetSet t = new TargetSet(cset, 0, 0);

		assertFalse(t.toggle(coords.get(0)));
		assertEquals(0, t.size());
	}

	@Test
	public void setPreservesInsertionOrderMany() {
		Random rand = new Random(985234);

		ArrayList<Tcoord> reference = new ArrayList<>();

		for (int n = 0; n < 10000; n++) {
			Tcoord coord = coords.get(rand.nextInt(coords.size()));
			int index = reference.indexOf(coord);

			if (index == -1) {
				assertTrue(set.toggle(coord));
				reference.add(coord);
			} else {
				assertTrue(set.toggle(coord));
				reference.remove(index);
			}

			for (int i = 0; i < reference.size(); i++) {
				assertEquals(reference.get(i), set.get(i));
			}

			assertEquals(reference.size(), set.size());
			assertNull(set.get(reference.size()));
		}
	}
}