package builders;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.BooleanArgument;

public class BooleanArgBuilderTest {

	@Test
	public void testArgumentCreation() {
		BooleanArgument argument = BooleanArgBuilder.hasDefaultValue(true)
													.create("visible");
		
		assertTrue(argument.hasDefaultValue());
		assertFalse(argument.isRequired());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testArgumentCreationNoNameException() {
		BooleanArgument argument = BooleanArgBuilder.hasDefaultValue(true)
													.isRequired()
													.create();
	}	

}
