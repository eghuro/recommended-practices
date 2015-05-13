import elements.Option;
import exceptions.ParseException;
import builders.IntegerArgBuilder;
import builders.OptionBuilder;

public final class Example {
    public static void main(final String[] args) {
        CommandLine cmdLine = new CommandLine();
        
        Option verbose = new OptionBuilder()
                .withName("v")
                .withNameSynonym("verbose")
                .withDescription("Output message about ...")
                .create();

        cmdLine.addOption(verbose);

        Option size = new OptionBuilder()
                .withName("size")
                .withNameSynonym("s")
                .isRequired()
                .withDescription("Size of ...")
                .hasArgument(
                    new IntegerArgBuilder()
                        .hasDefaultValue(42)
                        .create("BLOCK SIZE"))
                .create();

        cmdLine.addOption(size);

        CommandLineParser parser = new CommandLineParser(cmdLine.getOptions());

        try {
            parser.parse(args);

            String hasOptionVerbose;

            if (parser.hasOption("verbose")) {
                hasOptionVerbose = "true";
            } else {
                hasOptionVerbose = "false";
            }

            System.out.println("verbose = " + hasOptionVerbose);
            System.out.println("size = " + parser.getOptionValue("size"));
            System.out.println("args = "
                    + String.join(" ", parser.getCommonArguments()));
        } catch (ParseException exception) {
            System.out.println(exception);
            cmdLine.printUsage();	
        }
    }
}