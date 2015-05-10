package elements;

import java.util.HashSet;
import java.util.Set;

import visitors.Visitor;

public class EnumeratedArgument extends Argument {

	/** Argument enumerated values **/
	private Set<String> values = null;

	/** Argument default value **/
	private String defaultValue = null;

	/**
	 * Create argument with specific name and enumerated values
	 * 
	 * @param name
	 *            argument name
	 * @param values
	 *            argument enumerated values
	 * @throws IllegalArgumentException
	 */
	public EnumeratedArgument(String name, Set<String> values)
			throws IllegalArgumentException {

		super(name);
		addValues(values);
	}

	/**
	 * Create argument with specific name, enumerated values and default value
	 * 
	 * @param name
	 *            argument name
	 * @param values
	 *            argument enumerated values
	 * @param defaultValue
	 *            argument default value			
	 * @throws IllegalArgumentException
	 */
	public EnumeratedArgument(String name, Set<String> values,
			String defaultValue) throws IllegalArgumentException {

		super(name);
		addValues(values);
		setDefaultValue(defaultValue);
	}

	/**
	 * Add enumerated value to argument
	 * 
	 * @param value
	 *            enumerated value
	 */
	public void addValue(String value) {
		validateValue(value);
		this.values.add(value);
	}

	/**
	 * Add list of enumerated values
	 * 
	 * @param values
	 *            list of enumerated values
	 */
	public void addValues(Set<String> values) {
		
		for (String value : values) {
			addValue(value);
		}
		
	}

	/**
	 * Get list of enumerated values
	 * 
	 * @return list of enumerated values
	 */
	public Set<String> getValues() {
		return this.values;
	}

	/**
	 * Validate enumerated value before adding
	 * 
	 * @param value
	 *            enumerated value
	 * @throws IllegalArgumentException
	 */
	private void validateValue(String value) throws IllegalArgumentException {

		if (value == null || value.length() == 0) {
			throw new IllegalArgumentException(
					"Enumerated value can't be empty.");
		}

		if (!value.matches("^[A-Za-z0-9_]*[A-Za-z0-9][A-Za-z0-9_]*$")) {
			throw new IllegalArgumentException(
					"Invalid enumerated value - accepts letters, numbers and underscores.");
		}

		if (this.values == null) {
			this.values = new HashSet<>();
		}

		if (this.values.contains(value)) {
			throw new IllegalArgumentException("Duplicate enumerated value.");
		}
		
	}

	/**
	 * Set default enumerated value
	 * 
	 * @param defaultValue
	 *            default enumerated value
	 * @throws IllegalArgumentException
	 */
	public void
			setDefaultValue(String defaultValue) throws IllegalArgumentException {

		super.setDefaultValue();

		if (!this.values.contains(defaultValue)) {
			throw new IllegalArgumentException(
					"Default value isn't in the list of values.");
		}

		this.defaultValue = defaultValue;
		this.hasDefaultValue = true;
	}

	/**
	 * Get argument default value
	 * 
	 * @return argument default value
	 */
	public String getDefaultValue() {
		return this.defaultValue;
	}

	@Override
	public String getDefaulValueToString() {
		return this.defaultValue;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void accept(Visitor visitor, Option option) {

		visitor.visit(this, option);
	}

}
