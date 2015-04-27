import Builders.ArgumentBuilder;
import Builders.OptionBuilder;
import Elements.Option;
import java.util.Arrays;


public class Example {

	public static void main(String[] args) {
		CommandLine line = new CommandLine();

                Option verbose = new Option.OptionBuilder("v").setEquivalentName("verbose").build();
		
		line.registerOption(verbose);
		
                Option size = new Option.OptionBuilder("s").setEquivalentName("size")
                        .setArgument(ArgumentBuilder.integerType()
                        .setDefaultValue("42")).build();
				
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
