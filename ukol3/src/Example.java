import elements.IntegerArgument;
import elements.Option;
import java.util.Arrays;


/**
 * Priklad pouziti knihovny.
 * @author Alexander Mansurov <alexander.mansurov@gmail.com>
 */
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

        Option verbose = new Option.OptionBuilder("v")
                .setEquivalentName("verbose")
                .setDescription("be verbose").build();
        line.registerOption(verbose);

        Option size = new Option.OptionBuilder("s")
                .setRequired(true)
                .setEquivalentName("size")
                .setDescription("size")
                .setArgument(
                    new IntegerArgument
                            .IntegerArgumentBuilder()
                            .setDefaultValue("42")
                            .build())
                .build();
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