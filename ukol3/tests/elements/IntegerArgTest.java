package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntegerArgTest {

	@Test
	public void testConstructors() {

		IntegerArgument argument = new IntegerArgument("SIZE");

		assertFalse(argument.isRequired());
		assertFalse(argument.hasDefaultValue());

		IntegerArgument defaultArgument = new IntegerArgument("SIZE", 10);

		assertTrue(defaultArgument.hasDefaultValue());
		assertEquals(10, defaultArgument.getDefaultValue());

		IntegerArgument conditionArgument = new IntegerArgument("SIZE", 15, 10,
				20);

		assertEquals(15, conditionArgument.getDefaultValue());
		assertEquals(10, conditionArgument.getMinValue());
		assertEquals(20, conditionArgument.getMaxValue());
	}

	@Test
	public void testSetDefaultValue() {

		IntegerArgument argument = new IntegerArgument("SIZE");

		assertFalse(argument.hasDefaultValue());

		argument.setDefaultValue(-10);

		assertTrue(argument.hasDefaultValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDefaultValueException() {

		IntegerArgument argument = new IntegerArgument("SIZE", 15, 10, 20);

		argument.setDefaultValue(-10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMinValue() {

		IntegerArgument argument = new IntegerArgument("SIZE", 10);
		argument.setMinValue(20);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetMaxValue() {

		IntegerArgument argument = new IntegerArgument("SIZE", 20);
		argument.setMaxValue(-10);
	}

}
