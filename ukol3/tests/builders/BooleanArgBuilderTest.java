package builders;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.BooleanArgument;

public class BooleanArgBuilderTest {

    @Test
    public void testArgumentCreation() {
        BooleanArgument argument = new BooleanArgBuilder()
                .hasDefaultValue(true)
                .create("visible");

        assertTrue(argument.hasDefaultValue());
        assertFalse(argument.isRequired());
    }

    /**
     * Vyhodi vynimku, lebo argument nemoze mat defaultnu hodnotu 
     * a zaroven byt povinna/pozadovana
     */
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentCreationNoNameException() {
        BooleanArgument argument = new BooleanArgBuilder()
                .hasDefaultValue(true)
                .isRequired().create();
    }
}