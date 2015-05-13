package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringArgTest {

	@Test
	public void testStringArgument() {

		StringArgument argument = new StringArgument("nazov");

		assertFalse(argument.isRequired());
		assertFalse(argument.hasDefaultValue());

		StringArgument defaultArgument = new StringArgument("nazov", "retazec");

		assertTrue(defaultArgument.hasDefaultValue());
		assertEquals("retazec", defaultArgument.getDefaultValue());

		StringArgument minMaxArgument = new StringArgument("nazov", "retazec",
				5, 10);

		assertEquals(5, minMaxArgument.getMinLength());
		assertEquals(10, minMaxArgument.getMaxLength());
	}

	@Test
	public void testSetDefaultValue() {

		StringArgument argument = new StringArgument("nazov");

		assertFalse(argument.hasDefaultValue());

		argument.setDefaultValue("retazec");

		assertTrue(argument.hasDefaultValue());
	}

	/**
	 * Vyhodi vynimku, lebo defaultna hodnota obsahuje retazec dlzky 7 
	 * a minimalna dlzka retazca ma byt 20 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetMinLength() {

		StringArgument argument = new StringArgument("nazov", "retazec");

		argument.setMinLength(20);
	}

	/**
	 * Vyhodi vynimku, lebo defaultna hodnota obsahuje retazec dlzky 7 
	 * a maximalna dlzka retazca ma byt 5 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSetMaxLength() {

		StringArgument argument = new StringArgument("nazov", "retazec");

		argument.setMaxLength(5);
	}

}
