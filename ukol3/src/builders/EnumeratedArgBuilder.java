package builders;

import java.util.HashSet;
import java.util.Set;

import elements.EnumeratedArgument;

public class EnumeratedArgBuilder {
    /** Argument name **/
    private String name;

    /** Argument is required **/
    protected boolean required;

    /** Argument has default value **/
    protected boolean hasDefaultValue;

    /** Argument default value **/
    private String defaultValue;

    /** List of values **/
    private Set<String> values;

    public EnumeratedArgBuilder() {
        reset();
    }

    /**
     * Reset builder variables
     */
    private void reset() {
        this.name = null;
        this.required = false;
        this.hasDefaultValue = false;
        this.defaultValue = null;
        this.values = null;
    }

    /**
     * Set the name of the next argument
     * 
     * @param name argument name
     * @return modified self
     */
    public EnumeratedArgBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The next argument will be required
     * 
     * @return modified self
     */
    public EnumeratedArgBuilder isRequired() {
        this.required = true;
        return this;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue argument default value
     * @return modified self
     */
    public EnumeratedArgBuilder hasDefaultValue(String defaultValue) {
        this.hasDefaultValue = true;
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Set argument enumerated value
     * 
     * @param value argument enumerated value
     * @return modified self
     */
    public EnumeratedArgBuilder hasValue(String value) {
        if (this.values == null) {
            this.values = new HashSet<>();
        }

        this.values.add(value);

        return this;
    }

    /**
     * Set argument enumerated list of values
     * 
     * @param enumerated list of values
     * @return modified self
     */
    public EnumeratedArgBuilder hasValues(Set<String> values) {
        if (this.values == null) {
            this.values = new HashSet<>();
        }

        this.values.addAll(values);

        return this;
    }

    /**
     * Create EnumeratedArgument instance with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException
     */
    public EnumeratedArgument create() throws IllegalArgumentException {
            EnumeratedArgument argument = null;

            argument = new EnumeratedArgument(this.name, values);

            if (this.required) {
                argument.setRequired();
            }

            if (this.hasDefaultValue) {
                argument.setDefaultValue(this.defaultValue);
            }

            return argument;
    }

    /**
     * Create EnumeratedArgBuilder instance with desired parameters
     * 
     * @param argumentName argument name
     * @return created argument
     * @throws IllegalArgumentException
     */
    public EnumeratedArgument create(String argumentName) 
            throws IllegalArgumentException {
        this.name = argumentName;
        return create();
    }
}