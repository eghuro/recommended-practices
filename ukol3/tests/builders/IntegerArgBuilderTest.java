package builders;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.BooleanArgument;
import elements.IntegerArgument;

public class IntegerArgBuilderTest {

	@Test
	public void testArgumentCreation() {

		IntegerArgument argument = IntegerArgBuilder.hasDefaultValue(15)
				.create("SIZE");

		assertTrue(argument.hasDefaultValue());
		assertFalse(argument.isRequired());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgumentMinLengthException() {

		IntegerArgument argument = IntegerArgBuilder.withName("SIZE")
				.hasDefaultValue(15).acceptMinValue(30).isRequired().create();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgumentCreationNoNameException() {

		IntegerArgument argument = IntegerArgBuilder.hasDefaultValue(15)
				.isRequired().create();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgumentMaxLengthException() {

		IntegerArgument argument = IntegerArgBuilder.hasDefaultValue(15)
				.acceptMaxValue(5).isRequired().create("SIZE");
	}
}
