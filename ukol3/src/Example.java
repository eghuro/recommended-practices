import Elements.Argument;
import Elements.IntegerArgument;
import Elements.Option;
import java.util.Arrays;


public class Example {

	public static void main(String[] args) {
		CommandLine line = new CommandLine();

                Option verbose = new Option.OptionBuilder("v").setEquivalentName("verbose")
                        .setDescription("be verbose").build();
		
		line.registerOption(verbose);
		
                Option size = new Option.OptionBuilder("s").setRequired(true)
                        .setEquivalentName("size").setDescription("size")
                        .setArgument(new IntegerArgument.IntegerArgumentBuilder().setDefaultValue("42").build())
                        .build();
		line.registerOption(size);
		
		ParsedCommandLine parsedOptions = line.parse(args);  
		
		if (parsedOptions.success()) {
                    
                        String hasOptionVerbose = (parsedOptions.hasOption("verbose") ? "true" : "false");
                        
			System.out.println("verbose =" + hasOptionVerbose );
			
			System.out.println("size =" + parsedOptions.getOptionValue("size") );
			
			System.out.println("args =" + Arrays.toString(parsedOptions.getCommonArguments()) );
		}
		else {
			System.out.println(parsedOptions.getErrors());
			System.out.println(line.getUsage());
		}
		
		
		
	}

}
