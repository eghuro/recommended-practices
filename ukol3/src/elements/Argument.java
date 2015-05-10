package elements;

public abstract class Argument implements VisitableArgument {

	/** Argument name for usage printing **/
	protected String name = null;

	/** Argument is required **/
	protected boolean required = false;

	/** Argument has default value **/
	protected boolean hasDefaultValue = false;

	/**
	 * Create argument with specific name
	 * 
	 * @param name
	 *            argument name
	 * @throws IllegalArgumentException
	 */
	public Argument(String name) throws IllegalArgumentException {

		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Argument name can't be empty.");
		}

		if (!name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$")) {
			throw new IllegalArgumentException(
					"Invalid argument name - accepts letters, numbers, spaces and underscores.");
		}

		this.name = name;
	}

	/**
	 * Get argument name
	 * 
	 * @return argument name
	 */
	public String getName() {

		return this.name;
	}

	/**
	 * Set required argument
	 * 
	 * @throws IllegalArgumentException
	 */
	public void setRequired() throws IllegalArgumentException {

		setRequired(true);
	}

	/**
	 * Set required argument
	 * 
	 * @param required
	 *            will be option required?
	 * @throws IllegalArgumentException
	 */
	public void setRequired(boolean required) throws IllegalArgumentException {

		if (this.hasDefaultValue) {
			throw new IllegalArgumentException(
					"Can't set argument as required argument, because argument has default value.");
		}
		
		this.required = required;
	}

	/**
	 * Set argument default value
	 * 
	 * @throws IllegalArgumentException
	 */
	public void setDefaultValue() throws IllegalArgumentException {

		if (this.required) {
			throw new IllegalArgumentException(
					"Can't set argument default value, because argument is set as required argument.");
		}
		
	}

	/**
	 * Whether is option required
	 * 
	 * @return is option required?
	 */
	public boolean isRequired() {
		return this.required;
	}

	/**
	 * Whether has option default value
	 * 
	 * @return has option default value?
	 */
	public boolean hasDefaultValue() {
		return hasDefaultValue;
	}

	/**
	 * Get argument string value
	 * 
	 * @return argument string value
	 */
	public abstract String getDefaulValueToString();
}
