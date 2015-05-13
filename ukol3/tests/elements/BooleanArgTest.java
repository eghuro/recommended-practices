package elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class BooleanArgTest {
    @Test
    public void testConstructors() {
        BooleanArgument argument = new BooleanArgument("printable");

        assertFalse(argument.isRequired());
        assertFalse(argument.hasDefaultValue());

        BooleanArgument defaultArgument = new BooleanArgument("printable", true);

        assertTrue(defaultArgument.hasDefaultValue());
        assertEquals(true, defaultArgument.getDefaultValue());
    }

    @Test
    public void testSetRequired() {
        BooleanArgument argument = new BooleanArgument("printable");

        assertFalse(argument.isRequired());

        argument.setRequired();

        assertTrue(argument.isRequired());
    }

    /**
     * Vyhodi vynimku, lebo argument nemoze mat defaultnu hodnotu 
     * a zaroven byt povinna/pozadovana
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetRequiredException() {
        BooleanArgument argument = new BooleanArgument("printable", true);

        argument.setRequired();
    }

    /**
     * Vyhodi vynimku, lebo argument nemoze byt povinna/pozadovana 
     * a zaroven mat defaultnu hodnotu
     */
    @Test
    public void testSetDefaultValue() {
        BooleanArgument argument = new BooleanArgument("printable");

        assertFalse(argument.hasDefaultValue());

        argument.setDefaultValue(false);

        assertTrue(argument.hasDefaultValue());
    }
}
