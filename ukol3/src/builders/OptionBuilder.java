package builders;

import java.util.HashSet;
import java.util.Set;

import elements.Argument;
import elements.Option;

public final class OptionBuilder {

    /** Option name **/
    private String name;

    /** Option synonyms **/
    private Set<String> nameSynonyms;

    /** Option description **/
    private String description;

    /** Is option required? **/
    private boolean required;

    /** Option argument object **/
    private Argument argument;

    public OptionBuilder() {
        reset();
    }

    /**
     * Reset builder variables
     */
    private void reset() {
        this.name = null;
        this.nameSynonyms = null;
        this.description = null;
        this.required = false;
        this.argument = null;
    }

    /**
     * Set the name of the next option
     * 
     * @param name option name
     * @return modified self
     */
    public OptionBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Set the description of the next option
     * 
     * @param description option description
     * @return modified self
     */
    public OptionBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Add name synonym of the next option
     * 
     * @param name option name synonym
     * @return modified self
     */
    public OptionBuilder withNameSynonym(String name) {
        if (this.nameSynonyms == null) {
            this.nameSynonyms = new HashSet<>();
        }

        this.nameSynonyms.add(name);

        return this;
    }

    /**
     * The next option will be required
     * 
     * @return modified self
     */
    public OptionBuilder isRequired() {
        this.required = true;
        return this;
    }

    public OptionBuilder hasArgument(Argument argument) {
        this.argument = argument;
        return this;
    }

    /**
     * Create Option instance with desired parameters
     * 
     * @return created option
     * @throws IllegalArgumentException Option name can't be empty.
     */
    public Option create() throws IllegalArgumentException {
        Option option = null;

        option = new Option(this.name);

        if (this.nameSynonyms != null) {
            option.addSynonyms(this.nameSynonyms);
        }

        if (this.description != null) {
            option.setDescription(this.description);
        }

        if (this.required) {
            option.setRequired();
        }

        if (this.argument != null) {
            option.setArgument(this.argument);
        }

        return option;
    }

    /**
     * Create Option instance with desired parameters
     * 
     * @param optionName option name
     * @return created option
     * @throws IllegalArgumentException Option name can't be empty.
     */
    public Option create(String optionName) 
            throws IllegalArgumentException {
        this.name = optionName;
        return create();
    }
}
