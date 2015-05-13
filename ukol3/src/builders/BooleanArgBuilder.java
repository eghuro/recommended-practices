package builders;

import elements.BooleanArgument;

public class BooleanArgBuilder {
    
    /** Argument name **/
    private String name;

    /** Argument is required **/
    protected boolean required;

    /** Argument has default value **/
    protected boolean hasDefaultValue;

    /** Argument default value **/
    private boolean defaultValue;

    public BooleanArgBuilder() {
        reset();
    }

    /**
     * Reset builder variables
     */
    private void reset() {
        this.name = null;
        this.required = false;
        this.hasDefaultValue = false;
        this.defaultValue = false;
    }

    /**
     * Set the name of the next argument
     * 
     * @param name argument name
     * @return self (BooleanArgBuilder)
     */
    public BooleanArgBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The next argument will be required
     * 
     * @return self (BooleanArgBuilder)
     */
    public BooleanArgBuilder isRequired() {
        this.required = true;
        return this;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue argument default value
     * @return self (BooleanArgBuilder)
     */
    public BooleanArgBuilder hasDefaultValue(boolean defaultValue) {
        this.hasDefaultValue = true;
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Create BooleanArgument(object) with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException
     */
    public BooleanArgument create() throws IllegalArgumentException {
        BooleanArgument argument = null;

        argument = new BooleanArgument(this.name);

        if (this.required) {
            argument.setRequired();
        }

        if (this.hasDefaultValue) {
            argument.setDefaultValue(this.defaultValue);
        }
        
        return argument;
    }

    /**
     * Create BooleanArgument(object) with desired parameters
     * 
     * @param argumentName argument name
     * @return created argument
     * @throws IllegalArgumentException
     */
    public BooleanArgument create(String argumentName) 
            throws IllegalArgumentException {

        this.name = argumentName;
        return create();
    }
}
