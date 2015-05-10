package builders;

import elements.IntegerArgument;

public class IntegerArgBuilder {

	/** Argument name **/
	private static String name = null;

	/** Argument is required **/
	protected static boolean required = false;

	/** Argument has default value **/
	protected static boolean hasDefaultValue = false;

	/** Argument default value **/
	private static int defaultValue = 0;

	/** Argument min value **/
	private static int minValue = Integer.MIN_VALUE;

	/** Argument max value **/
	private static int maxValue = Integer.MAX_VALUE;

	/** IntegerArgBuilder instance **/
	private static IntegerArgBuilder instance = new IntegerArgBuilder();

	private IntegerArgBuilder() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Reset builder variables
	 */
	private static void reset() {
		IntegerArgBuilder.name = null;
		IntegerArgBuilder.required = false;
		IntegerArgBuilder.hasDefaultValue = false;
		IntegerArgBuilder.defaultValue = 0;
		IntegerArgBuilder.minValue = Integer.MIN_VALUE;
		IntegerArgBuilder.maxValue = Integer.MAX_VALUE;
	}

	/**
	 * Set the name of the next argument
	 * 
	 * @param name
	 *            argument name
	 * @return self (IntegerArgBuilder)
	 */
	public static IntegerArgBuilder withName(String name) {
		IntegerArgBuilder.name = name;
		return instance;
	}

	/**
	 * The next argument will be required
	 * 
	 * @return self (IntegerArgBuilder)
	 */
	public static IntegerArgBuilder isRequired() {
		IntegerArgBuilder.required = true;
		return instance;
	}

	/**
	 * Set argument default value
	 * 
	 * @param defaultValue
	 *            argument default value
	 * @return self (IntegerArgBuilder)
	 */
	public static IntegerArgBuilder hasDefaultValue(int defaultValue) {
		IntegerArgBuilder.hasDefaultValue = true;
		IntegerArgBuilder.defaultValue = defaultValue;
		return instance;
	}

	/**
	 * Set argument minimum required value
	 * 
	 * @param minValue
	 *            minimum required value
	 * @return self (IntegerArgBuilder)
	 */
	public static IntegerArgBuilder acceptMinValue(int minValue) {
		IntegerArgBuilder.minValue = minValue;
		return instance;
	}

	/**
	 * Set argument maximum required value
	 * 
	 * @param maxValue
	 *            maximum required value
	 * @return self (IntegerArgBuilder)
	 */
	public static IntegerArgBuilder acceptMaxValue(int maxValue) {
		IntegerArgBuilder.maxValue = maxValue;
		return instance;
	}

	/**
	 * Create IntegerArgument(object) with desired parameters
	 * 
	 * @return created argument
	 * @throws IllegalArgumentException
	 */
	public static IntegerArgument create() throws IllegalArgumentException {

		IntegerArgument argument = null;

		try {
			
			argument = new IntegerArgument(IntegerArgBuilder.name);

			if (IntegerArgBuilder.required) {
				argument.setRequired();
			}

			if (IntegerArgBuilder.hasDefaultValue) {
				argument.setDefaultValue(IntegerArgBuilder.defaultValue);
			}

			if (IntegerArgBuilder.minValue > Integer.MIN_VALUE) {
				argument.setMinValue(IntegerArgBuilder.minValue);
			}

			if (IntegerArgBuilder.maxValue < Integer.MAX_VALUE) {
				argument.setMaxValue(IntegerArgBuilder.maxValue);
			}
			
		} finally {
			
			reset();
			
		}

		return argument;
	}

	/**
	 * Create IntegerArgument(object) with desired parameters
	 * 
	 * @param argumentName
	 *            argument name
	 * @return created argument
	 * @throws IllegalArgumentException
	 */
	public static IntegerArgument
			create(String argumentName) throws IllegalArgumentException {

		IntegerArgBuilder.name = argumentName;

		return create();
	}

}