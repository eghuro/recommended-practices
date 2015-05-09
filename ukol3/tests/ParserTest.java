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
		
        Option verbose = OptionBuilder.withName("v")
        								.isRequired()
										.withNameSynonym("verbose")
										.create();
        
		cmdLine.addOption(verbose);
		
        Option size = OptionBuilder.withName("s")
        								.withNameSynonym("size")
						        		.isLongOption()
						        		.hasArgument(IntegerArgBuilder.hasDefaultValue(15)
						        										.acceptMinValue(-5)
						        										.acceptMaxValue(20)
						        										.create("block size"))
										.create();

        cmdLine.addOption(size);
        
        Option width = OptionBuilder.withName("width")
									.withNameSynonym("w")
					        		.hasArgument(IntegerArgBuilder.isRequired()
					        										.acceptMinValue(100)
					        										.acceptMaxValue(100)
					        										.create("width size"))
									.create();    
        
        Option output = OptionBuilder.withName("output")
						        		.hasArgument(EnumeratedArgBuilder.hasValue("show")
						        											.hasValue("print")
						        											.hasDefaultValue("show")
						        											.create("type"))
										.create();
        
        cmdLine.addOption(output);
        
        Option inputFile = OptionBuilder.withName("f")
						        		.hasArgument(StringArgBuilder.isRequired()
					        											.acceptMinLength(7)
					        											.acceptMaxLength(15)
					        											.create("file_name"))
					        			.create();
        
        cmdLine.addOption(inputFile);
        
        Option saveResult = OptionBuilder.withName("save")
							        		.hasArgument(BooleanArgBuilder.isRequired()
							    											.create("save or not"))
							    			.create();

        cmdLine.addOption(saveResult);
        
        parser = new CommandLineParser(cmdLine.getOptions());
	}
	
	@Test
	public void testRequiredOption() {
		String[] arguments = "-verbose -save true".split(" ");
		
        try {
        	parser.parse(arguments);            
        } catch(ParseException exception) {
        	fail(exception.toString());
        }
        
        assertTrue(parser.hasShortOption("v"));
        assertTrue(parser.hasShortOption("verbose"));
        assertEquals("true", parser.getShortOptionValue("save"));  
        assertTrue(parser.getCommonArguments().isEmpty());
	}
	
	@Test
	public void testDefaultValue() {
		String[] arguments = "-v -s -- -input.txt moje".split(" ");
		
        try {
        	parser.parse(arguments);            
        } catch(ParseException exception) {
        	fail(exception.toString());
        }
        
        assertEquals("15", parser.getLongOptionValue("size"));
        assertEquals("15", parser.getLongOptionValue("s"));
        assertEquals(2, parser.getCommonArguments().size()); 
        assertEquals("-input.txt moje", String.join(" ", parser.getCommonArguments()));
	}
	
	@Test(expected=ParseException.class)
	public void testIntegerMinValue() throws ParseException {
		String[] arguments = "-v -s -50".split(" ");
		
		parser.parse(arguments);  	
	}
	
	@Test(expected=ParseException.class)
	public void testIntegerMaxValue() throws ParseException {
		String[] arguments = "-v -s 50".split(" ");
		
		parser.parse(arguments);  	
	}
	
	@Test
	public void testRequiredArgument() {
		String[] arguments = "-v -f input.txt".split(" ");
		
        try {
        	parser.parse(arguments);            
        } catch(ParseException exception) {
        	fail(exception.toString());
        }
        
        assertEquals("input.txt", parser.getShortOptionValue("f"));
	}	
	
	@Test(expected=ParseException.class)
	public void testStringMinLength() throws ParseException {
		String[] arguments = "-v -f t.xt".split(" ");
		
		parser.parse(arguments);  	
	}
	
	@Test(expected=ParseException.class)
	public void testStringMaxLength() throws ParseException {
		String[] arguments = "-v -f verylongfilename.txt".split(" ");
		
		parser.parse(arguments);  	
	}
	
	@Test
	public void testEnumValue() {
		String[] arguments = "-v -output print".split(" ");
		
        try {
        	parser.parse(arguments);            
        } catch(ParseException exception) {
        	fail(exception.toString());
        }
        
        assertEquals("print", parser.getShortOptionValue("output"));
	}
	
	@Test(expected=ParseException.class)
	public void testBadEnumValue() throws ParseException {
		String[] arguments = "-v -output shoow".split(" ");
		
		parser.parse(arguments);  	
	}

	@Test
	public void testBooleanValue() {
		String[] arguments = "-v -save false".split(" ");
		
        try {
        	parser.parse(arguments);            
        } catch(ParseException exception) {
        	fail(exception.toString());
        }
        
        assertEquals("false", parser.getShortOptionValue("save"));	
	}
	
	@Test(expected=ParseException.class)
	public void testBadBooleanValue() throws ParseException {
		String[] arguments = "-v -save ano".split(" ");
		
		parser.parse(arguments);  	
	}

}
