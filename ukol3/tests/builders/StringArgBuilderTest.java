package builders;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.IntegerArgument;
import elements.StringArgument;

public class StringArgBuilderTest {

	@Test
	public void testArgumentCreation() {

		StringArgument argument = new StringArgBuilder().withName("premenna")
				.hasDefaultValue("retazec").create();

		assertTrue(argument.hasDefaultValue());
		assertFalse(argument.isRequired());
	}

	/**
	 * Vyhodi vynimku, lebo argument nemoze mat defaultnu hodnotu 
	 * a zaroven byt povinna/pozadovana
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testArgumentCreationNoNameException() {

		StringArgument argument = new StringArgBuilder().hasDefaultValue("retazec")
				.isRequired().create();
	}

	/**
	 * Vyhodi vynimku, lebo defaltna hodnota ma dlzku 7 
	 * a minimalna pozadovana dlzka retazca ma byt 15
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testArgumentMinLengthException() {

		StringArgument argument = new StringArgBuilder().hasDefaultValue("retazec")
				.acceptMinLength(15).create("premenna");
	}

	/**
	 * Vyhodi vynimku, lebo defaltna hodnota ma dlzku 7 
	 * a maximalna pozadovana dlzka retazca ma byt 15
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testArgumentMaxLengthException() {

		StringArgument argument = new StringArgBuilder().hasDefaultValue("retazec")
				.acceptMaxLength(5).isRequired().create("premenna");
	}

}
