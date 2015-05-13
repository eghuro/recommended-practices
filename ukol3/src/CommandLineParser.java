import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import visitors.SearchByNameVisitor;
import visitors.ValidateVisitor;
import elements.Option;
import elements.Visitable;
import exceptions.ParseException;

public class CommandLineParser {

	/** Command line options **/
	private HashSet<Option> options;

	/** Command line common argument - not specified **/
	private HashSet<String> commonArguments = new HashSet<>();

	/** Options values from command line **/
	private HashMap<Option, String> optionsValues = 
			new HashMap<Option, String>();

	/**
	 * Create command line parser with selected options
	 * 
	 * @param options
	 */
	public CommandLineParser(HashSet<Option> options) {
		this.options = options;
	}

	/**
	 * Parse command line arguments
	 * 
	 * @param arguments
	 *            command line arugments
	 * @throws ParseException
	 */
	public void parse(String[] arguments) throws ParseException {
		
		final String VALUE_DELIMITER = "=";
		final String COMMON_ARGUMENTS_DELIMTIER = "--";
		
		if (arguments == null) {
			arguments = new String[0];
		}
		
		List<String> argumentsList = 
				new ArrayList<String>(Arrays.asList(arguments));
		
		boolean isArgumentCommon = false;
		
		while (!argumentsList.isEmpty()) {
			String argument = argumentsList.remove(0); 
			
			if (isArgumentCommon) {
				
				commonArguments.add(argument);
				
			}
			else if (argument.equals(COMMON_ARGUMENTS_DELIMTIER)) {
				
				isArgumentCommon = true;
				
			}
			else {
				
				if (argument.indexOf(VALUE_DELIMITER) > 0) {
					String[] argumentParts = argument.split(VALUE_DELIMITER, 2);
					argument = argumentParts[0];
					argumentsList.add(0, argumentParts[1]);
				}
				
				Option argumentOption = getOptionByArgument(argument);
				
				boolean isNextArgumentValue = false;
				
				if (!argumentsList.isEmpty()) {
					
					String nextArgument = argumentsList.get(0);
					
					if (!nextArgument.equals(COMMON_ARGUMENTS_DELIMTIER)) {
						
						if (nextArgument.indexOf(VALUE_DELIMITER) > 0) {
						
							String[] nextArgumentParts = 
									nextArgument.split(VALUE_DELIMITER, 2);
							
							nextArgument = nextArgumentParts[0];
						
						}
						
						isNextArgumentValue = !isArgumentOption(nextArgument);
					}
					
				}
				
				if (argumentOption != null && isNextArgumentValue 
						&& argumentOption.hasArgument()) {
					
					this.optionsValues.put(argumentOption, 
							argumentsList.remove(0));
				}
				else if (argumentOption != null && !isNextArgumentValue) {
					this.optionsValues.put(argumentOption, null);
				}
				else {
					throw new ParseException("Unexpected option/argument: "
							+ argument);
				}
				
			}			
			
		}

		validateArguments();
	}

	/**
	 * Validate arguments from command line
	 * 
	 * @throws ParseException
	 */
	private void validateArguments() throws ParseException {

		ValidateVisitor validateVisitor = new ValidateVisitor(
				this.optionsValues);

		for (Visitable option : this.options) {
			option.accept(validateVisitor);
		}

		if (!validateVisitor.getErrors().isEmpty()) {
			throw new ParseException(String.join("\n",
					validateVisitor.getErrors()));
		}
	}

	/**
	 * Get Option(object) by argument name
	 * 
	 * @param argument
	 *            searched option name
	 * @return Option(object) or null
	 */
	private Option getOptionByArgument(String argument) {

		SearchByNameVisitor searchVisitor = new SearchByNameVisitor(argument);

		for (Visitable option : this.options) {
			option.accept(searchVisitor);
		}

		if (searchVisitor.optionNameFound()) {
			return searchVisitor.getFoundOption();
		} else {
			return null;
		}
	}
	
	/**
	 * Check if argument name from command line is option name
	 * @param argument 
	 *            argument name from command line
	 * @return whether it is option name
	 */
	private boolean isArgumentOption(String argument) {
		return (getOptionByArgument(argument) != null);
	}

	/**
	 * Check if option with specific name was in command line arguments
	 * 
	 * @param optionName
	 *            option name
	 * @return whether it was in command line arguments
	 */
	public boolean hasOption(String optionName) {

		String optionNameWithPrefix = Option
				.createOptionNameWithPrefix(optionName);
		
		Set<Option> parsedOptions = this.optionsValues.keySet();

		SearchByNameVisitor searchVisitor = new SearchByNameVisitor(
				optionNameWithPrefix);

		for (Visitable option : parsedOptions) {
			option.accept(searchVisitor);
		}

		return searchVisitor.optionNameFound();
	}

	/**
	 * Get option argument value
	 * 
	 * @param optionName
	 *            option name
	 * @return option argument value
	 */
	public String getOptionValue(String optionName) {

		String optionNameWithPrefix = Option
				.createOptionNameWithPrefix(optionName);
		
		String optionValue = null;
		
		Set<Option> parsedOptions = this.optionsValues.keySet();

		SearchByNameVisitor searchVisitor = new SearchByNameVisitor(
				optionNameWithPrefix);

		for (Visitable option : parsedOptions) {
			option.accept(searchVisitor);
		}

		if (searchVisitor.optionNameFound()) {			
			optionValue = this.optionsValues
					.get(searchVisitor.getFoundOption());
		}

		if (optionValue == null) {
			
			searchVisitor.reset();

			for (Visitable option : this.options) {
				option.accept(searchVisitor);
			}

			if (searchVisitor.optionNameFound()) {
				optionValue = searchVisitor.getFoundOption().getArgument()
						.getDefaulValueToString();
			}
		}

		return optionValue;
	}

	/**
	 * Get command line common arguments
	 * 
	 * @return command line common arguments
	 */
	public Set<String> getCommonArguments() {
		return this.commonArguments;
	}

}
