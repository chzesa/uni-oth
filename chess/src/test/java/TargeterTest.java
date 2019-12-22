package oht.chess.shared;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.HashSet;

import oht.chess.util.TargetSet;
import oht.chess.util.Targeter;
import oht.chess.util.Tcoord;

public class TargeterTest {
	Targeter t;
	TargetSet a;
	static final Tcoord a1 = new Tcoord(0, 1);
	static final Tcoord a2 = new Tcoord(0, 2);
	static final Tcoord a3 = new Tcoord(0, 3);

	TargetSet b;
	static final Tcoord b1 = new Tcoord(2, 1);
	static final Tcoord b2 = new Tcoord(5, 2);

	TargetSet c;
	static final Tcoord c1 = new Tcoord(2, -1);

	@Before
	public void setUp() {
		HashSet<Tcoord> setA = new HashSet<>();
		setA.add(a1);
		setA.add(a2);
		setA.add(a3);
		a = new TargetSet(setA, 1);

		HashSet<Tcoord> setB = new HashSet<>();
		setB.add(b1);
		setB.add(b2);
		b = new TargetSet(setB, 1);

		HashSet<Tcoord> setC = new HashSet<>();
		setC.add(c1);
		c = new TargetSet(setC, 1);

		t = new Targeter(a);
	}

	@Test
	public void pushAddsSet() {
		assertEquals(1, t.size());
		t.push(b);
		assertEquals(2, t.size());
	}

	@Test
	public void popRemovesSet() {
		t.push(b);
		assertEquals(b, t.pop());
		assertEquals(1, t.size());
	}

	@Test
	public void popFailsSetTooSmall() {
		assertNull(t.pop());
	}

	@Test
	public void isCompleteReflectsSetsStatus() {
		assertFalse(t.isComplete());
		t.toggle(a1);
		assertTrue(t.isComplete());
		t.push(b);
		assertFalse(t.isComplete());
		t.toggle(b1);
		assertTrue(t.isComplete());
	}

	@Test(expected = IllegalArgumentException.class)
	public void constructorFailOnNullParam() {
		new Targeter(null);
	}

	@Test
	public void cantPushNullSet() {
		t.push(null);
		assertEquals(1, t.size());
	}

	@Test
	public void toggleModifiesCurrentSet() {
		t.toggle(a1);
		assertTrue(a.contains(a1));
	}

	@Test
	public void nullGetOnOob() {
		assertNull(t.get(-1, 0));
		assertNull(t.get(0, -1));
		assertNull(t.get(0, 1));
		assertNull(t.get(1, 0));
	}

	@Test
	public void testGet() {
		t.toggle(a1);
		assertEquals(a1, t.get(0, 0));
		t.push(b);
		t.toggle(b2);
		assertEquals(b2, t.get(1, 0));
	}

	@Test
	public void testSizeOfIndex() {
		assertEquals(0, t.size(1));
		t.push(b);
		t.toggle(b1);
		assertEquals(0, t.size(0));
		assertEquals(1, t.size(1));
	}
}