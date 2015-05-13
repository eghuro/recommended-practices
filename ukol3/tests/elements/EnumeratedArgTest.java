package elements;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class EnumeratedArgTest {
	Set<String> values;

	@Before
	public void setUp() throws Exception {
            values = new HashSet<String>(Arrays.asList("print", "show"));
	}

	@Test
	public void testConstructors() {
            EnumeratedArgument argument = new EnumeratedArgument("data", values);

            assertFalse(argument.isRequired());
            assertFalse(argument.hasDefaultValue());

            argument.setRequired();

            assertTrue(argument.isRequired());

            EnumeratedArgument defaultArgument = new EnumeratedArgument("data",
                            values, "show");

            assertTrue(defaultArgument.hasDefaultValue());
            assertEquals("show", defaultArgument.getDefaultValue());
	}

	@Test
	public void testAddValue() {
            EnumeratedArgument argument = new EnumeratedArgument("data", values);

            argument.addValue("save");
            argument.setDefaultValue("save");

            assertTrue(argument.hasDefaultValue());
	}

	@Test
	public void testAddValues() {
            EnumeratedArgument argument = new EnumeratedArgument("data", values);

            Set<String> otherValues = new HashSet<String>(Arrays.asList("save",
                            "send"));

            argument.addValues(otherValues);
            argument.setDefaultValue("send");

            assertTrue(argument.hasDefaultValue());
	}

	/**
	 * Vyhodi vynimku, lebo argument obsahuje defaultnu hodnotu, 
	 * ktora nie je definovana
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetDefaultValue() {
            EnumeratedArgument argument = new EnumeratedArgument("data", values);
            argument.setDefaultValue("showAndPrint");
	}
}
