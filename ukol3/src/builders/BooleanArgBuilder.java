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
     * @return modified self
     */
    public BooleanArgBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * The next argument will be required
     * 
     * @return modified self
     */
    public BooleanArgBuilder isRequired() {
        this.required = true;
        return this;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue argument default value
     * @return modified self
     */
    public BooleanArgBuilder hasDefaultValue(boolean defaultValue) {
        this.hasDefaultValue = true;
        this.defaultValue = defaultValue;
        return this;
    }

    /**
     * Create BooleanArgument instance with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException Can't set argument default value, 
     * because argument is set as required argument.
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
     * Create BooleanArgument instance with desired parameters
     * 
     * @param argumentName argument name
     * @return created argument
     * @throws IllegalArgumentException Can't set argument default value, 
     * because argument is set as required argument.
     */
    public BooleanArgument create(String argumentName) 
            throws IllegalArgumentException {

        this.name = argumentName;
        return create();
    }
}
