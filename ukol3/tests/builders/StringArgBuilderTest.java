package builders;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.IntegerArgument;
import elements.StringArgument;

public class StringArgBuilderTest {

	@Test
	public void testArgumentCreation() {
		StringArgument argument = StringArgBuilder.withName("premenna")
													.hasDefaultValue("retazec")
													.create();
		
		assertTrue(argument.hasDefaultValue());
		assertFalse(argument.isRequired());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArgumentCreationNoNameException() {
		StringArgument argument = StringArgBuilder.hasDefaultValue("retazec")
													.isRequired()
													.create();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArgumentMinLengthException() {
		StringArgument argument = StringArgBuilder.hasDefaultValue("retazec")
													.acceptMinLength(15)
													.isRequired()
													.create("premenna");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArgumentMaxLengthException() {
		StringArgument argument = StringArgBuilder.hasDefaultValue("retazec")
													.acceptMaxLength(5)
													.isRequired()
													.create("premenna");
	}

}
