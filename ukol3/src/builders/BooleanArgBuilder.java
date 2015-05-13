package builders;

import elements.BooleanArgument;

public class BooleanArgBuilder {
    
    /** Argument name **/
    private static String name = null;

    /** Argument is required **/
    protected static boolean required = false;

    /** Argument has default value **/
    protected static boolean hasDefaultValue = false;

    /** Argument default value **/
    private static boolean defaultValue = false;

    /** BooleanArgBuilder instance **/
    private static BooleanArgBuilder instance = new BooleanArgBuilder();

    private BooleanArgBuilder() {
            // Exists only to defeat instantiation.
    }

    /**
     * Reset builder variables
     */
    private static void reset() {
            BooleanArgBuilder.name = null;
            BooleanArgBuilder.required = false;
            BooleanArgBuilder.hasDefaultValue = false;
            BooleanArgBuilder.defaultValue = false;
    }

    /**
     * Set the name of the next argument
     * 
     * @param name
     *            argument name
     * @return self (BooleanArgBuilder)
     */
    public static BooleanArgBuilder withName(String name) {
            BooleanArgBuilder.name = name;
            return instance;
    }

    /**
     * The next argument will be required
     * 
     * @return self (BooleanArgBuilder)
     */
    public static BooleanArgBuilder isRequired() {
            BooleanArgBuilder.required = true;
            return instance;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue
     *            argument default value
     * @return self (BooleanArgBuilder)
     */
    public static BooleanArgBuilder hasDefaultValue(boolean defaultValue) {
            BooleanArgBuilder.hasDefaultValue = true;
            BooleanArgBuilder.defaultValue = defaultValue;
            return instance;
    }

    /**
     * Create BooleanArgument(object) with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException
     */
    public static BooleanArgument create() throws IllegalArgumentException {
        BooleanArgument argument = null;

        try {
            argument = new BooleanArgument(BooleanArgBuilder.name);

            if (BooleanArgBuilder.required) {
                argument.setRequired();
            }

            if (BooleanArgBuilder.hasDefaultValue) {
                argument.setDefaultValue(BooleanArgBuilder.defaultValue);
            }
        } finally {		
            reset();	
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
    public static BooleanArgument create(String argumentName) 
            throws IllegalArgumentException {

        BooleanArgBuilder.name = argumentName;
        return create();
    }
}
