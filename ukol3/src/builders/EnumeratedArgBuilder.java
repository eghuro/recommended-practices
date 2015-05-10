package builders;

import java.util.HashSet;
import java.util.Set;

import elements.EnumeratedArgument;

public class EnumeratedArgBuilder {

	/** Argument name **/
	private static String name = null;

	/** Argument is required **/
	protected static boolean required = false;

	/** Argument has default value **/
	protected static boolean hasDefaultValue = false;

	/** Argument default value **/
	private static String defaultValue = null;

	/** List of values **/
	private static Set<String> values = null;

	/** EnumeratedArgBuilder instance **/
	private static EnumeratedArgBuilder instance = new EnumeratedArgBuilder();

	private EnumeratedArgBuilder() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Reset builder variables
	 */
	private static void reset() {
		EnumeratedArgBuilder.name = null;
		EnumeratedArgBuilder.required = false;
		EnumeratedArgBuilder.hasDefaultValue = false;
		EnumeratedArgBuilder.defaultValue = null;
		EnumeratedArgBuilder.values = null;
	}

	/**
	 * Set the name of the next argument
	 * 
	 * @param name
	 *            argument name
	 * @return self (EnumeratedArgBuilder)
	 */
	public static EnumeratedArgBuilder withName(String name) {
		EnumeratedArgBuilder.name = name;
		return instance;
	}

	/**
	 * The next argument will be required
	 * 
	 * @return self (EnumeratedArgBuilder)
	 */
	public static EnumeratedArgBuilder isRequired() {
		EnumeratedArgBuilder.required = true;
		return instance;
	}

	/**
	 * Set argument default value
	 * 
	 * @param defaultValue
	 *            argument default value
	 * @return self (EnumeratedArgBuilder)
	 */
	public static EnumeratedArgBuilder hasDefaultValue(String defaultValue) {
		EnumeratedArgBuilder.hasDefaultValue = true;
		EnumeratedArgBuilder.defaultValue = defaultValue;
		return instance;
	}

	/**
	 * Set argument enumerated value
	 * 
	 * @param value
	 *            argument enumerated value
	 * @return self (EnumeratedArgBuilder)
	 */
	public static EnumeratedArgBuilder hasValue(String value) {

		if (EnumeratedArgBuilder.values == null) {
			EnumeratedArgBuilder.values = new HashSet<>();
		}

		EnumeratedArgBuilder.values.add(value);

		return instance;
	}

	/**
	 * Set argument enumerated list of values
	 * 
	 * @param enumerated
	 *            list of values
	 * @return self (EnumeratedArgBuilder)
	 */
	public static EnumeratedArgBuilder hasValues(Set<String> values) {

		if (EnumeratedArgBuilder.values == null) {
			EnumeratedArgBuilder.values = new HashSet<>();
		}

		EnumeratedArgBuilder.values.addAll(values);

		return instance;
	}

	/**
	 * Create EnumeratedArgument(object) with desired parameters
	 * 
	 * @return created argument
	 * @throws IllegalArgumentException
	 */
	public static EnumeratedArgument create() throws IllegalArgumentException {

		EnumeratedArgument argument = null;

		try {
			
			argument = new EnumeratedArgument(EnumeratedArgBuilder.name, values);

			if (EnumeratedArgBuilder.required) {
				argument.setRequired();
			}

			if (EnumeratedArgBuilder.hasDefaultValue) {
				argument.setDefaultValue(EnumeratedArgBuilder.defaultValue);
			}
			
		} finally {
			
			reset();
			
		}

		return argument;
	}

	/**
	 * Create EnumeratedArgBuilder(object) with desired parameters
	 * 
	 * @param argumentName
	 *            argument name
	 * @return created argument
	 * @throws IllegalArgumentException
	 */
	public static EnumeratedArgument
			create(String argumentName) throws IllegalArgumentException {

		EnumeratedArgBuilder.name = argumentName;

		return create();
	}

}
