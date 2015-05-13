package elements;

import visitors.Visitor;

public class IntegerArgument extends Argument {

    /** Argument default value **/
    int defaultValue = 0;

    /** Argument min. value **/
    int minValue = Integer.MIN_VALUE;

    /** Argument max. value **/
    int maxValue = Integer.MAX_VALUE;

    /**
     * Create argument with specific name
     * 
     * @param name argument name
     */
    public IntegerArgument(String name) {
        super(name);
    }

    /**
     * Create argument with specific name and default value
     * 
     * @param name  argument name
     * @param defaultValue argument default value
     * @throws IllegalArgumentException default value for required option
     */
    public IntegerArgument(String name, int defaultValue)
                    throws IllegalArgumentException {
        super(name);
        setDefaultValue(defaultValue);
    }

    /**
     * Create argument with specific name, default value, min. and max. value
     * 
     * @param name argument name
     * @param defaultValue argument default value
     * @param minValue argument max. value
     * @param maxValue argument min. value
     * @throws IllegalArgumentException range error or default value for
     * required option
     */
    public IntegerArgument(String name, int defaultValue, int minValue,
                    int maxValue) throws IllegalArgumentException {
        super(name);
        setDefaultValue(defaultValue);
        setMinValue(minValue);
        setMaxValue(maxValue);
    }

    /**
     * Set default value
     * 
     * @param defaultValue default value
     * @throws IllegalArgumentException Can't set argument default value, 
     * because argument is set as required argument.
     */
    public void setDefaultValue(int defaultValue) 
            throws IllegalArgumentException {
        super.setDefaultValue();

        validateDefaultValue(defaultValue, this.minValue, this.maxValue);

        this.defaultValue = defaultValue;
        this.hasDefaultValue = true;
    }

    /**
     * Get argument default value
     * 
     * @return argument default value
     */
    public int getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Set argument min. value
     * 
     * @param minValue argument min. value
     * @throws IllegalArgumentException Minimum value can't be greater than 
     * maximum value.
     */
    public void setMinValue(int minValue) throws IllegalArgumentException {
        if (minValue > this.maxValue) {
            throw new IllegalArgumentException(
                "Minimum value can't be greater than maximum value.");
        }

        if (this.hasDefaultValue) {
            validateDefaultValue(this.defaultValue, minValue, this.maxValue);
        }

        this.minValue = minValue;
    }

    /**
     * Get argument min. value
     * 
     * @return argument min. value
     */
    public int getMinValue() {
        return this.minValue;
    }

    /**
     * Set argument max. value
     * 
     * @param maxValue argument max. value
     * @throws IllegalArgumentException Maximum value can't be lesser than 
     * minimum value.
     */
    public void setMaxValue(int maxValue) throws IllegalArgumentException {
        if (maxValue < this.minValue) {
            throw new IllegalArgumentException(
                "Maximum value can't be lesser than minimum value.");
        }

        if (this.hasDefaultValue) {
            validateDefaultValue(this.defaultValue, this.minValue, maxValue);
        }

        this.maxValue = maxValue;
    }

    /**
     * Get argument max. value
     * 
     * @return argument max. value
     */
    public int getMaxValue() {
            return this.maxValue;
    }

    /**
     * Validate default, min. and max. value before setting
     * 
     * @param defaultValue default value
     * @param minValue min value
     * @param maxValue max. value
     * @throws IllegalArgumentException Default value can't be lesser than 
     * minimum value nor greater than maximum value.
     */
    protected void validateDefaultValue(int defaultValue, int minValue,
            int maxValue) throws IllegalArgumentException {
        if (defaultValue < minValue) {
            throw new IllegalArgumentException(
                "Default value can't be lesser than minimum value.");
        }

        if (defaultValue > maxValue) {
            throw new IllegalArgumentException(
                "Default value can't be greater than maximum value.");
        }	
    }

    @Override
    public String getDefaulValueToString() {
        return Integer.toString(this.defaultValue);
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