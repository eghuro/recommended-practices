package builders;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import elements.BooleanArgument;
import elements.EnumeratedArgument;

public class EnumeratedArgBuilderTest {

	Set<String> values;

	@Before
	public void setUp() throws Exception {

		values = new HashSet<String>(Arrays.asList("print", "show"));
	}

	@Test
	public void testArgumentCreationWithValues() {

		EnumeratedArgument argument = EnumeratedArgBuilder.hasValues(values)
				.isRequired().create("data");

		assertFalse(argument.hasDefaultValue());
		assertTrue(argument.isRequired());
	}

	@Test
	public void testArgumentCreationWithValue() {

		EnumeratedArgument argument = EnumeratedArgBuilder.withName("data")
				.hasValue("print").hasDefaultValue("print").create();

		assertTrue(argument.hasDefaultValue());
		assertFalse(argument.isRequired());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgumentCreationNoNameException() {

		BooleanArgument argument = BooleanArgBuilder.hasDefaultValue(true)
				.isRequired().create();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testArgumentCreationBadDefaultException() {

		EnumeratedArgument argument = EnumeratedArgBuilder.hasValues(values)
				.hasDefaultValue("save").create("data");

	}

}
