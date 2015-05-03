package builders;

import java.nio.file.attribute.AclEntry.Builder;
import java.util.HashSet;
import java.util.Set;

import elements.Argument;
import elements.Option;

public final class OptionBuilder {
	
	/** Option name **/
	private static String name = null;
	
	/** Option synonyms **/
	private static Set<String> nameSynonyms = null;
	
	/** Option description **/
	private static String description = null;
	
	/** Is option required? **/
	private static boolean required = false;
	
	/** Option argument object **/
	private static Argument argument = null;
	
	/** OptionBuilder instance **/
	private static OptionBuilder instance = new OptionBuilder();

	private OptionBuilder() {
		// Exists only to defeat instantiation.
	}
	
	/**
	 * Resets builder variables
	 */
	private static void reset() {
		OptionBuilder.name = null;
		OptionBuilder.nameSynonyms = null;
		OptionBuilder.description = null;
		OptionBuilder.required = false;
		OptionBuilder.argument = null;	
	}
	
	/**
	 * Sets the name of the next option
	 * @param name option name
	 * @return self (OptionBuilder)
	 */
	public static OptionBuilder withName(String name) {
		OptionBuilder.name = name;
		
		addNameSynonym(name);
		
		return instance;
	}
	
	/**
	 * Sets the description of the next option
	 * @param description option description
	 * @return self (OptionBuilder)
	 */
	public static OptionBuilder withDescription(String description) {
		OptionBuilder.description = description;
		return instance;
	}
	
	/**
	 * Add name synonym of the next option
	 * @param name option name synonym
	 * @return self (OptionBuilder)
	 */
	public static OptionBuilder addNameSynonym(String name) {
		if (OptionBuilder.nameSynonyms == null) {
			OptionBuilder.nameSynonyms = new HashSet<>();
		}
		
		OptionBuilder.nameSynonyms.add(name);
		
		return instance;
	}
	
	/**
	 * The next option will be required
	 * @return self (OptionBuilder)
	 */
	public static OptionBuilder setRequired() {
		return setRequired(true);
	}
	
	/**
	 * Set required of the next option
	 * @param required will be option required?
	 * @return self (OptionBuilder)
	 */
	public static OptionBuilder setRequired(boolean required) {
		OptionBuilder.required = required;
		return instance;
	}
	
	/**
	 * Set argument object of the next option
	 * @param argument option argument object
	 * @return self (OptionBuilder)
	 */
	public static OptionBuilder setArgument(Argument argument) {
		OptionBuilder.argument = argument;
		return instance;
	}
	
	/**
	 * Create Option(object) with desired parameters
	 * @return created option
	 * @throws IllegalArgumentException
	 */
	public static Option create() throws IllegalArgumentException {
		Option option = null;
		
		try {
			option = new Option(OptionBuilder.name);
			
			if (OptionBuilder.nameSynonyms != null) {
				option.addSynonyms(OptionBuilder.nameSynonyms);
			}
			
			if (OptionBuilder.description != null) {
				option.setDescription(OptionBuilder.description);
			}
			
			if (OptionBuilder.required) {
				option.setRequired();
			}
			
			if (OptionBuilder.argument != null) {
				option.setArgument(OptionBuilder.argument);
			}
		}
		finally {
			reset();
		}		
		
		return option;
	}

}
