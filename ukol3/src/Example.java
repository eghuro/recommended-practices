import Builders.ArgumentBuilder;
import Builders.OptionBuilder;
import Elements.Option;


public class Example {

	public static void main(String[] args) {
		CommandLine line = new CommandLine();

		Option verbose = OptionBuilder.create("v")
										.setEquivalentName("verbose");
		
		line.registerOption(verbose);
		
		Option size = OptionBuilder.create("s")
									.setEquivalentName("size")
									.setArgument(ArgumentBuilder.integerType()
																	.setDefaultValue("42"));
				
		line.registerOption(size);
		
		ParsedCommandLine parsedOptions = line.parse(args);  
		
		if (parsedOptions.success()) {
			
			String hasOptionVerbose = (parsedOptions.hasOption("verbose"))? "true" : "false";
			System.out.println("verbose =" + hasOptionVerbose );
			
			System.out.println("size =" + parsedOptions.getOptionValue("size") );
			
			System.out.println("args =" + parsedOptions.getCommonArguments() );
		}
		else {
			System.out.println(parsedOptions.getErrors());
			System.out.println(line.getUsage());
		}
		
		
		
	}

}
