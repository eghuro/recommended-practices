package builders;

import elements.StringArgument;

public class StringArgBuilder {

    /** Argument name **/
    private static String name = null;

    /** Argument is required **/
    protected static boolean required = false;

    /** Argument has default value **/
    protected static boolean hasDefaultValue = false;

    /** Argument default value **/
    private static String defaultValue = null;

    /** Argument value minimum length **/
    private static int valueMinLength = 0;

    /** Argument value maximum length **/
    private static int valueMaxLength = Integer.MAX_VALUE;

    /** StringArgBuilder instance **/
    private static StringArgBuilder instance = new StringArgBuilder();

    private StringArgBuilder() {
            // Exists only to defeat instantiation.
    }

    /**
     * Reset builder variables
     */
    private static void reset() {
        StringArgBuilder.name = null;
        StringArgBuilder.required = false;
        StringArgBuilder.hasDefaultValue = false;
        StringArgBuilder.defaultValue = null;
        StringArgBuilder.valueMinLength = 0;
        StringArgBuilder.valueMaxLength = Integer.MAX_VALUE;
    }

    /**
     * Set the name of the next argument
     * 
     * @param name argument name
     * @return self (StringArgBuilder)
     */
    public static StringArgBuilder withName(String name) {
        StringArgBuilder.name = name;
        return instance;
    }

    /**
     * The next argument will be required
     * 
     * @return self (StringArgBuilder)
     */
    public static StringArgBuilder isRequired() {
        StringArgBuilder.required = true;
        return instance;
    }

    /**
     * Set argument default value
     * 
     * @param defaultValue
     *            argument default value
     * @return self (StringArgBuilder)
     */
    public static StringArgBuilder hasDefaultValue(String defaultValue) {
        StringArgBuilder.hasDefaultValue = true;
        StringArgBuilder.defaultValue = defaultValue;
        return instance;
    }

    /**
     * Set argument minimum required length
     * 
     * @param minLength minimum required length
     * @return self (StringArgBuilder)
     */
    public static StringArgBuilder acceptMinLength(int minLength) {
        StringArgBuilder.valueMinLength = minLength;
        return instance;
    }

    /**
     * Set argument maximum required length
     * 
     * @param maxLength maximum required length
     * @return self (StringArgBuilder)
     */
    public static StringArgBuilder acceptMaxLength(int maxLength) {
        StringArgBuilder.valueMaxLength = maxLength;
        return instance;
    }

    /**
     * Create StringArgument(object) with desired parameters
     * 
     * @return created argument
     * @throws IllegalArgumentException
     */
    public static StringArgument create() throws IllegalArgumentException {
        StringArgument argument = null;

        try {
            argument = new StringArgument(StringArgBuilder.name);

            if (StringArgBuilder.required) {
                argument.setRequired();
            }

            if (StringArgBuilder.hasDefaultValue) {
                argument.setDefaultValue(StringArgBuilder.defaultValue);
            }

            if (StringArgBuilder.valueMinLength > 0) {
                argument.setMinLength(StringArgBuilder.valueMinLength);
            }

            if (StringArgBuilder.valueMaxLength < Integer.MAX_VALUE) {
                argument.setMaxLength(StringArgBuilder.valueMaxLength);
            }	
        } finally {
            reset();
        }

        return argument;
    }

    /**
     * Create StringArgument(object) with desired parameters
     * 
     * @param argumentName
     *            argument name
     * @return created argument
     * @throws IllegalArgumentException
     */
    public static StringArgument create(String argumentName) 
            throws IllegalArgumentException {
        StringArgBuilder.name = argumentName;
        return create();
    }
}