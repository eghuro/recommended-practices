package builders;

import static org.junit.Assert.*;

import org.junit.Test;

import elements.IntegerArgument;

public class IntegerArgBuilderTest {

    @Test
    public void testArgumentCreation() {
            IntegerArgument argument = new IntegerArgBuilder()
                    .hasDefaultValue(15)
                    .create("SIZE");

            assertTrue(argument.hasDefaultValue());
            assertFalse(argument.isRequired());
    }

    /**
     * Vyhodi vynimku, lebo argument ma nastavenu 
     * minimalnu hodnotu 30 a defaultnu hodnotu 15
     */
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMinLengthException() {
            IntegerArgument argument = new IntegerArgBuilder()
                    .withName("SIZE")
                    .hasDefaultValue(15).acceptMinValue(30).create();
    }

    /**
     * Vyhodi vynimku, lebo argument nemoze mat defaultnu hodnotu 
     * a zaroven byt povinna/pozadovana
     */
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentCreationNoNameException() {
            IntegerArgument argument = new IntegerArgBuilder()
                    .hasDefaultValue(15)
                    .isRequired().create();
    }

    /**
     * Vyhodi vynimku, lebo defaultna hodnota je nastavena na 15 a 
     * maximalna povolena hodnota je 5
     */
    @Test(expected = IllegalArgumentException.class)
    public void testArgumentMaxLengthException() {
            IntegerArgument argument = new IntegerArgBuilder()
                    .hasDefaultValue(15)
                    .acceptMaxValue(5).create("SIZE");
    }
}
