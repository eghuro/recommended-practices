import elements.IntegerArgument;
import elements.Option;

import java.util.Arrays;

import builders.OptionBuilder;

public final class Example {
    /**
     * Utility class - bez verejneho konstruktoru.
     */
    private Example() {
    }

    /**
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        CommandLine line = new CommandLine();

        Option verbose = OptionBuilder.withName("v")
                						.addNameSynonym("verbose")
                						.withDescription("be verbose")
                						.create();
        line.registerOption(verbose);

        Option size = OptionBuilder.withName("size")
        							.setRequired()
        							.withDescription("size")
        							.setArgument(
        										new IntegerArgument
        											.IntegerArgumentBuilder()
        											.setDefaultValue("42")
        											.build())
        							.create();
        line.registerOption(size);

        ParsedCommandLine parsedOptions = line.parse(args);
        if (parsedOptions.success()) {
            String hasOptionVerbose;
            if (parsedOptions.hasOption("verbose")) {
                hasOptionVerbose = "true";
            } else {
                hasOptionVerbose = "false";
            }

            System.out.println("verbose =" + hasOptionVerbose);
            System.out.println("size =" + parsedOptions.getOptionValue("size"));
            System.out.println("args =" + Arrays.toString(parsedOptions
                    .getCommonArguments()));
        } else {
            System.out.println(parsedOptions.getErrors());
            System.out.println(line.getUsage());
        }
    }
}