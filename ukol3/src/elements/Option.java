package elements;

import java.util.HashSet;
import java.util.Set;

import visitors.Visitor;

public class Option implements Visitable {

    /** Option short prefix **/
    public static final String SHORT_PREFIX = "-";

    /** Option long prefix **/
    public static final String LONG_PREFIX = "--";

    /** Option name **/
    private String name = "";

    /** Option synonyms **/
    private Set<String> synonyms = new HashSet<>();

    /** Option description **/
    private String description = "";

    /** Is option required? **/
    private boolean required = false;

    /** Option argument object **/
    private Argument argument = null;

    /**
     * Constructor for Option
     * 
     * @param name option name
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    public Option(String name) throws IllegalArgumentException {
        setName(name);
    }

    /**
     * Constructor for Option
     * 
     * @param name option name
     * @param description option description
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    public Option(String name, String description)
                throws IllegalArgumentException {
        setName(name);
        setDescription(description);
    }

    /**
     * Constructor for Option
     * 
     * @param name option name
     * @param description option description
     * @param required is option required
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    public Option(String name, String description, boolean required)
                    throws IllegalArgumentException {
        setName(name);
        setDescription(description);
        setRequired(required);
    }

    /**
     * Constructor for Option
     * 
     * @param name option name
     * @param description option description
     * @param required is option required
     * @param argument option Argument(object)
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    public Option(String name, String description, boolean required,
                    Argument argument) throws IllegalArgumentException {
        setName(name);
        setDescription(description);
        setRequired(required);
        setArgument(argument);
    }

    /**
     * Set option name
     * 
     * @param name Option name
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    private void setName(String name) throws IllegalArgumentException {
        nameValidation(name);
        this.name = name;
    }

    /**
     * Get option name
     * 
     * @return option name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get option name with prefix
     * 
     * @return option name with prefix
     */
    public String getNameWithPrefix() {
        return Option.createOptionNameWithPrefix(this.name);
    }

    /**
     * Set option name synonym
     * 
     * @param name option name
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    public void addSynonym(String name) throws IllegalArgumentException {
        nameValidation(name);
        synonyms.add(name);
    }

    /**
     * Set option name synonyms
     * 
     * @param nameSynonyms set of name synonyms
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name.
     */
    public void addSynonyms(Set<String> nameSynonyms) 
            throws IllegalArgumentException {
        for (String otherName : nameSynonyms) {
            addSynonym(otherName);
        }
    }

    /**
     * Validate option name
     * 
     * @param name option name
     * @throws IllegalArgumentException Option name can't be empty or invalid 
     * option name - accepts letters, numbers, spaces and underscores.
     */
    private void nameValidation(String name) throws IllegalArgumentException {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException("Option name can't be empty.");
        }

        if (!name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$")) {
            throw new IllegalArgumentException(
                "Invalid option name - accepts letters, numbers, spaces and underscores.");
        }
    }

    /**
     * Get option names
     * 
     * @return set of names for this option
     */
    public Set<String> getNames() {
        return this.synonyms;
    }

    /**
     * Get option names with prefix
     * 
     * @return set of names for this option with prefix
     */
    public Set<String> getNamesWithPrefix() {
        Set<String> namesWithPrefix = new HashSet<>();

        for (String argumentSynonym : this.synonyms) {
            String synonymWithPrefix = Option
                .createOptionNameWithPrefix(argumentSynonym);
            namesWithPrefix.add(synonymWithPrefix);
        }

        return namesWithPrefix;
    }

    /**
     * Set option description
     * 
     * @param description description of the option
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get description of the option
     * 
     * @return description of the option
     */
    public String getDesription() {
        return this.description;
    }

    /**
     * Option will be required
     */
    public void setRequired() {
        setRequired(true);
    }

    /**
     * Set required option
     * 
     * @param required will be option required?
     */
    public void setRequired(boolean required) {
        this.required = required;
    }

    /**
     * Whether is option required
     * 
     * @return is option required?
     */
    public boolean isRequired() {
        return this.required;
    }

    /**
     * Set option Argument(object)
     * 
     * @param argument option argument
     * @throws IllegalArgumentException Option argument can't be null object.
     */
    public void setArgument(Argument argument) throws IllegalArgumentException {
        if (argument == null) {
            throw new IllegalArgumentException(
                "Option argument can't be null object.");
        }

        this.argument = argument;
    }

    /**
     * Get Argument instance for this option
     * 
     * @return Argument for this option
     */
    public Argument getArgument() {
        return this.argument;
    }

    /**
     * Whether has option argument
     * 
     * @return has this option argument?
     */
    public boolean hasArgument() {
        return (this.argument != null);
    }

    /**
     * Create option name with prefix
     * 
     * @param optionName processed option name
     * @return option name with prefix
     */
    public static String createOptionNameWithPrefix(String optionName) {
        if (optionName.length() > 1) {
            return Option.LONG_PREFIX + optionName;
        } else {
            return Option.SHORT_PREFIX + optionName;
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}