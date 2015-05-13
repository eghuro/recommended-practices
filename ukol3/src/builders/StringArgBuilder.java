package builders;

import elements.StringArgument;

public class StringArgBuilder {

    /** Argument name **/
    private String name;

    /** Argument is required **/
    protected boolean required;

    /** Argument has default value **/
    protected boolean hasDefaultValue;

    /** Argument default value **/
    private String defaultValue;

    /** Argument value minimum length **/
    private int valueMinLength;

    /** Argument value maximum length **/
    private int valueMaxLength;

    public StringArgBuilder() {
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
        this.valueMinLength = 0;
        this.valueMaxLength = Integer.MAX_VALUE;
    }

    /**
     * Set the name of the next argument
     * 
     * @param name argument name
     * @return self (StringArgBuilder)
     */
    public StringArgBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The next argument will be required
     * 
     * @return self (StringArgBuilder)
     */
    public StringArgBuilder isRequired() {
        this.required = true;
        return this;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue argument default value
     * @return self (StringArgBuilder)
     */
    public StringArgBuilder hasDefaultValue(String defaultValue) {
        this.hasDefaultValue = true;
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Set argument minimum required length
     * 
     * @param minLength minimum required length
     * @return self (StringArgBuilder)
     */
    public StringArgBuilder acceptMinLength(int minLength) {
        this.valueMinLength = minLength;
        return this;
    }

    /**
     * Set argument maximum required length
     * 
     * @param maxLength maximum required length
     * @return self (StringArgBuilder)
     */
    public StringArgBuilder acceptMaxLength(int maxLength) {
        this.valueMaxLength = maxLength;
        return this;
    }

    /**
     * Create StringArgument(object) with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException
     */
    public StringArgument create() throws IllegalArgumentException {
        StringArgument argument = null;

        argument = new StringArgument(this.name);

        if (this.required) {
            argument.setRequired();
        }

        if (this.hasDefaultValue) {
            argument.setDefaultValue(this.defaultValue);
        }

        if (this.valueMinLength > 0) {
            argument.setMinLength(this.valueMinLength);
        }

        if (this.valueMaxLength < Integer.MAX_VALUE) {
            argument.setMaxLength(this.valueMaxLength);
        }

        return argument;
    }

    /**
     * Create StringArgument(object) with desired parameters
     * 
     * @param argumentName argument name
     * @return created argument
     * @throws IllegalArgumentException
     */
    public StringArgument create(String argumentName) 
            throws IllegalArgumentException {
        this.name = argumentName;
        return create();
    }
}