package builders;

import elements.IntegerArgument;

public class IntegerArgBuilder {

    /** Argument name **/
    private String name;

    /** Argument is required **/
    protected boolean required;

    /** Argument has default value **/
    protected boolean hasDefaultValue;

    /** Argument default value **/
    private int defaultValue;

    /** Argument min value **/
    private int minValue;

    /** Argument max value **/
    private int maxValue;

    public IntegerArgBuilder() {
        reset();
    }

    /**
     * Reset builder variables
     */
    private void reset() {
        this.name = null;
        this.required = false;
        this.hasDefaultValue = false;
        this.defaultValue = 0;
        this.minValue = Integer.MIN_VALUE;
        this.maxValue = Integer.MAX_VALUE;
    }

    /**
     * Set the name of the next argument
     * 
     * @param name argument name
     * @return modified self
     */
    public IntegerArgBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The next argument will be required
     * 
     * @return modified self
     */
    public IntegerArgBuilder isRequired() {
        this.required = true;
        return this;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue argument default value
     * @return modified self
     */
    public IntegerArgBuilder hasDefaultValue(int defaultValue) {
        this.hasDefaultValue = true;
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Set argument minimum required value
     * 
     * @param minValue minimum required value
     * @return modified self
     */
    public IntegerArgBuilder acceptMinValue(int minValue) {
        this.minValue = minValue;
        return this;
    }

    /**
     * Set argument maximum required value
     * 
     * @param maxValue maximum required value
     * @return modified self
     */
    public IntegerArgBuilder acceptMaxValue(int maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    /**
     * Create IntegerArgument instance with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException Can't set argument default value, 
     * because argument is set as required argument or range error.
     */
    public IntegerArgument create() throws IllegalArgumentException {
        IntegerArgument argument = null;
        
        argument = new IntegerArgument(this.name);

        if (this.required) {
            argument.setRequired();
        }

        if (this.hasDefaultValue) {
            argument.setDefaultValue(this.defaultValue);
        }

        if (this.minValue > Integer.MIN_VALUE) {
            argument.setMinValue(this.minValue);
        }

        if (this.maxValue < Integer.MAX_VALUE) {
            argument.setMaxValue(this.maxValue);
        }

        return argument;
    }

    /**
     * Create IntegerArgument instance with desired parameters
     * 
     * @param argumentName argument name
     * @return created argument
     * @throws IllegalArgumentException Can't set argument default value, 
     * because argument is set as required argument or range error.
     */
    public IntegerArgument create(String argumentName) 
            throws IllegalArgumentException {
        this.name = argumentName;
        return create();
    }
}