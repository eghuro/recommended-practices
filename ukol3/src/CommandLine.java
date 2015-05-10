import java.util.HashSet;

import elements.Option;
import elements.Visitable;
import visitors.OptionsPrintVisitor;
import visitors.SearchByNameVisitor;
import visitors.UsagePrintVisitor;

public class CommandLine {

	/** Command line options **/
	private HashSet<Option> options;

	/**
	 * Create command line with empty options
	 */
	public CommandLine() {
		this.options = new HashSet<>();
	}

	/**
	 * Add option to command line
	 * 
	 * @param option
	 * @throws IllegalArgumentException
	 */
	public void addOption(Option option) throws IllegalArgumentException {

		if (hasOptionUniqueNames(option)) {
			throw new IllegalArgumentException(
					"Option name is consistent with other option name.");
		}
		
		this.options.add(option);
	}

	/**
	 * Check if option has unique name/names
	 * 
	 * @param option
	 *            checked option
	 * @return whether option has unique name/names
	 */
	private boolean hasOptionUniqueNames(Option option) {

		if (existsOptionName(option.getNameWithPrefix())) {
			return true;
		}

		if (!option.getNames().isEmpty()) {
			for (String argumentSynonym : option.getNamesWithPrefix()) {
				if (existsOptionName(argumentSynonym)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Check if option name exists
	 * 
	 * @param name
	 *            checked option name
	 * @return whether option name exists
	 */
	private boolean existsOptionName(String name) {

		SearchByNameVisitor searchVisitor = new SearchByNameVisitor(name);

		for (Visitable option : this.options) {
			option.accept(searchVisitor);
		}

		return searchVisitor.optionNameFound();
	}

	/**
	 * Print program usage
	 */
	public void printUsage() {

		UsagePrintVisitor usageVisitor = new UsagePrintVisitor();

		System.out.print("Usage: " + getProgramName());

		for (Visitable option : this.options) {
			option.accept(usageVisitor);
		}

		System.out.println();
		System.out.println("Options: ");

		OptionsPrintVisitor optionsVisitor = new OptionsPrintVisitor();

		for (Visitable option : this.options) {
			option.accept(optionsVisitor);
		}
	}

	/**
	 * Get program name
	 * 
	 * @return
	 */
	private String getProgramName() {

		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		StackTraceElement main = stack[stack.length - 1];
		
		return main.getClassName();
	}

	/**
	 * Get command line options
	 * 
	 * @return command line option
	 */
	public HashSet<Option> getOptions() {
		return this.options;
	}
}