package elements;

import visitors.Visitor;

public class BooleanArgument extends Argument {

	/** Argument default value **/
	private boolean defaultValue = false;

	/**
	 * Create argument with specific name
	 * 
	 * @param name
	 *            argument name
	 * @throws IllegalArgumentException
	 */
	public BooleanArgument(String name) throws IllegalArgumentException {
		super(name);
	}

	/**
	 * Create argument with specific name and default value
	 * 
	 * @param name
	 *            argument name
	 * @param defaultValue
	 *            default value
	 * @throws IllegalArgumentException
	 */
	public BooleanArgument(String name, boolean defaultValue)
			throws IllegalArgumentException {

		super(name);
		setDefaultValue(defaultValue);
	}

	/**
	 * Set argument default value
	 * 
	 * @param defaultValue
	 *            argument default value
	 * @throws IllegalArgumentException
	 */
	public void
			setDefaultValue(boolean defaultValue) throws IllegalArgumentException {

		super.setDefaultValue();
		
		this.defaultValue = defaultValue;
		this.hasDefaultValue = true;
	}

	/**
	 * Get argument default value
	 * 
	 * @return argument default value
	 */
	public boolean getDefaultValue() {
		return this.defaultValue;
	}

	@Override
	public String getDefaulValueToString() {
		return (this.defaultValue) ? "true" : "false";
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
