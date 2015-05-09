import elements.Option;
import exceptions.ParseException;
import builders.IntegerArgBuilder;
import builders.OptionBuilder;

public final class Example {

    public static void main(final String[] args) {
        CommandLine cmdLine = new CommandLine();

        Option verbose = OptionBuilder.withName("v")
                						.withNameSynonym("verbose")
                						.withDescription("Output message about ...")
                						.create();
        
        cmdLine.addOption(verbose);

        Option size = OptionBuilder.withName("size")
        							.isLongOption()
        							.isRequired()
        							.withDescription("Size of ...")
        							.hasArgument(IntegerArgBuilder.hasDefaultValue(42)
        															.create("BLOCK SIZE"))
        							.create();
        
        cmdLine.addOption(size);

        CommandLineParser parser = new CommandLineParser(cmdLine.getOptions());
        
        try {
        	parser.parse(args);
        	
            String hasOptionVerbose;
            
            if (parser.hasShortOption("verbose")) {
                hasOptionVerbose = "true";
            } else {
                hasOptionVerbose = "false";
            }
            
            System.out.println("verbose = " + hasOptionVerbose);
            System.out.println("size = " + parser.getLongOptionValue("size"));    
            System.out.println("args = " + String.join(" ", parser.getCommonArguments()));
            
        } catch(ParseException exception) {
        	System.out.println(exception);
        	cmdLine.printUsage();
        }
    }
}