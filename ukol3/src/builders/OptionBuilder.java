package builders;

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
     * Reset builder variables
     */
    private static void reset() {
        OptionBuilder.name = null;
        OptionBuilder.nameSynonyms = null;
        OptionBuilder.description = null;
        OptionBuilder.required = false;
        OptionBuilder.argument = null;
    }

    /**
     * Set the name of the next option
     * 
     * @param name option name
     * @return self (OptionBuilder)
     */
    public static OptionBuilder withName(String name) {
        OptionBuilder.name = name;
        return instance;
    }

    /**
     * Set the description of the next option
     * 
     * @param description option description
     * @return self (OptionBuilder)
     */
    public static OptionBuilder withDescription(String description) {
        OptionBuilder.description = description;
        return instance;
    }

    /**
     * Add name synonym of the next option
     * 
     * @param name option name synonym
     * @return self (OptionBuilder)
     */
    public static OptionBuilder withNameSynonym(String name) {
        if (OptionBuilder.nameSynonyms == null) {
            OptionBuilder.nameSynonyms = new HashSet<>();
        }

        OptionBuilder.nameSynonyms.add(name);

        return instance;
    }

    /**
     * The next option will be required
     * 
     * @return self (OptionBuilder)
     */
    public static OptionBuilder isRequired() {
        OptionBuilder.required = true;
        return instance;
    }

    public static OptionBuilder hasArgument(Argument argument) {
        OptionBuilder.argument = argument;
        return instance;
    }

    /**
     * Create Option(object) with desired parameters
     * 
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
        } finally {
            reset();	
        }

        return option;
    }

    /**
     * Create Option(object) with desired parameters
     * 
     * @param optionName option name
     * @return created option
     * @throws IllegalArgumentException
     */
    public static Option create(String optionName) 
            throws IllegalArgumentException {
        OptionBuilder.name = optionName;
        return create();
    }
}
