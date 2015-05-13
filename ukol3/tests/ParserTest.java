import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import builders.BooleanArgBuilder;
import builders.EnumeratedArgBuilder;
import builders.IntegerArgBuilder;
import builders.OptionBuilder;
import builders.StringArgBuilder;
import elements.Option;
import exceptions.ParseException;

public class ParserTest {

	CommandLineParser parser;

	@Before
	public void setUp() throws Exception {
            CommandLine cmdLine = new CommandLine();

            Option verbose = new OptionBuilder().withName("v").isRequired()
                            .withNameSynonym("verbose").create();

            cmdLine.addOption(verbose);

            Option size = new OptionBuilder()
                        .withName("s")
                        .withNameSynonym("size")
                        .hasArgument(
                            new IntegerArgBuilder().hasDefaultValue(15)
                                .acceptMinValue(-5).acceptMaxValue(20)
                                .create("block size")).create();

            cmdLine.addOption(size);

            Option width = new OptionBuilder()
                            .withName("width")
                            .withNameSynonym("w")
                            .hasArgument(
                                new IntegerArgBuilder().isRequired()
                                        .acceptMinValue(100)
                                        .acceptMaxValue(100)
                                        .create("width size"))
                            .create();

            cmdLine.addOption(width);

            Option output = new OptionBuilder()
                            .withName("output")
                            .hasArgument(
                                new EnumeratedArgBuilder().hasValue("show")
                                        .hasValue("print")
                                        .hasDefaultValue("show")
                                        .create("type"))
                            .create();

            cmdLine.addOption(output);

            Option inputFile = new OptionBuilder()
                            .withName("f")
                            .hasArgument(
                                new StringArgBuilder().isRequired()
                                        .acceptMinLength(7)
                                        .acceptMaxLength(15)
                                        .create("file_name"))
                            .create();

            cmdLine.addOption(inputFile);

            Option saveResult = new OptionBuilder()
                            .withName("save")
                            .hasArgument(
                                new BooleanArgBuilder()
                                    .isRequired().create("save or not"))
                            .create();

            cmdLine.addOption(saveResult);

            parser = new CommandLineParser(cmdLine.getOptions());
	}
	
	@Test
	public void testRequiredOption() {
            String[] arguments = "--verbose --save true".split(" ");

            try {
                parser.parse(arguments);
            } catch (ParseException exception) {
                fail(exception.toString());
            }

            assertTrue(parser.hasOption("v"));
            assertTrue(parser.hasOption("verbose"));
            assertEquals("true", parser.getOptionValue("save"));
            assertTrue(parser.getCommonArguments().isEmpty());
	}

	@Test
	public void testDefaultValue() {
            String[] arguments = "-v -s -- -input.txt moje".split(" ");

            try {
                parser.parse(arguments);
            } catch (ParseException exception) {
                fail(exception.toString());
            }

            assertEquals("15", parser.getOptionValue("size"));
            assertEquals("15", parser.getOptionValue("s"));
            assertEquals(2, parser.getCommonArguments().size());
            assertEquals("-input.txt moje",
                            String.join(" ", parser.getCommonArguments()));
	}
	
	@Test
	public void testOptionWithEqual() {
            String[] arguments = "-v --width=100 -- -s 10 alpha bravo".split(" ");

            try {
                parser.parse(arguments);
            } catch (ParseException exception) {
                fail(exception.toString());
            }

            assertTrue(parser.hasOption("v"));
            assertTrue(parser.hasOption("width"));
            assertEquals("100", parser.getOptionValue("width"));		
            assertEquals(4, parser.getCommonArguments().size());		
	}	

	/**
	 * Vyhodi vynimku, lebo volba "s" moze obsahovat
	 * integer minimalnej hodnoty -5 
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testIntegerMinValue() throws ParseException {
            String[] arguments = "-v -s -50".split(" ");

            parser.parse(arguments);
	}

	/**
	 * Vyhodi vynimku, lebo volba "s" moze obsahovat integer 
	 * maximalnej hodnoty 20
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testIntegerMaxValue() throws ParseException {
            String[] arguments = "-v -s 50".split(" ");

            parser.parse(arguments);
	}

	@Test
	public void testRequiredArgument() {
            String[] arguments = "-v -f input.txt".split(" ");

            try {
                    parser.parse(arguments);
            } catch (ParseException exception) {
                    fail(exception.toString());
            }

            assertEquals("input.txt", parser.getOptionValue("f"));
	}

	@Test(expected = ParseException.class)
	public void testStringMinLength() throws ParseException {
            String[] arguments = "-v -f t.xt".split(" ");

            parser.parse(arguments);
	}

	/**
	 * Vyhodi vynimku, lebo volba "f" moze obsahovat string 
	 * maximalnej dlzky 15 znakov
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testStringMaxLength() throws ParseException {
            String[] arguments = "-v -f verylongfilename.txt".split(" ");

            parser.parse(arguments);
	}

	@Test
	public void testEnumValue() {
            String[] arguments = "-v --output print".split(" ");

            try {
                    parser.parse(arguments);
            } catch (ParseException exception) {
                    fail(exception.toString());
            }

            assertEquals("print", parser.getOptionValue("output"));
	}

	/**
	 * Vyhodi vynimku, lebo volba "output" nema enum hodnotu shoow
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testBadEnumValue() throws ParseException {
            String[] arguments = "-v --output shoow".split(" ");

            parser.parse(arguments);
	}
	
	@Test
	public void testBooleanValue() {
            String[] arguments = "-v --save false".split(" ");

            try {
                    parser.parse(arguments);
            } catch (ParseException exception) {
                    fail(exception.toString());
            }

            assertEquals("false", parser.getOptionValue("save"));
	}

	/**
	 * Vyhodi vynimku, lebo volba "save" moze byt true/false
	 * @throws ParseException
	 */
	@Test(expected = ParseException.class)
	public void testBadBooleanValue() throws ParseException {
            String[] arguments = "-v --save ano".split(" ");

            parser.parse(arguments);
	}

}
