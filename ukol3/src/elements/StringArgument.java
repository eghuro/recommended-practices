package elements;

import visitors.Visitor;

public class StringArgument extends Argument {

    /** Argument default value **/
    String defaultValue = null;

    /** Argument min length **/
    int minLength = 0;

    /** Argument max length **/
    int maxLength = Integer.MAX_VALUE;

    /**
     * Create argument with specific name
     * 
     * @param name argument name
     */
    public StringArgument(String name) {
        super(name);
    }

    /**
     * Create argument with specific name and default value
     * 
     * @param name argument name
     * @param defaultValue default value
     * @throws IllegalArgumentException Default value length can't be lesser 
     * than minimum length nor be greater than maximum length. Minimum length 
     * can't be negative number nor can't be greater than maximum length. 
     * Maximum length can't be lesser than minimum length nor can't be lesser 
     * than 1.
     */
    public StringArgument(String name, String defaultValue)
                    throws IllegalArgumentException {
        super(name);
        setDefaultValue(defaultValue);
    }

    /**
     * Create argument with specific name, default value, min. and max. string
     * length
     * 
     * @param name argument name
     * @param defaultValue argument default value
     * @param minLength argument string min. length
     * @param maxLength argument string max. length
     * @throws IllegalArgumentException Default value length can't be lesser 
     * than minimum length nor be greater than maximum length. Minimum length 
     * can't be negative number nor can't be greater than maximum length. 
     * Maximum length can't be lesser than minimum length nor can't be lesser 
     * than 1.
     */
    public StringArgument(String name, String defaultValue, int minLength,
                    int maxLength) throws IllegalArgumentException {
        super(name);
        setDefaultValue(defaultValue);
        setMinLength(minLength);
        setMaxLength(maxLength);
    }

    /**
     * Set default value
     * 
     * @param defaultValue default value
     * @throws IllegalArgumentException Default value length can't be lesser 
     * than minimum length nor be greater than maximum length.
     */
    public void setDefaultValue(String defaultValue) throws IllegalArgumentException {
        super.setDefaultValue();

        validateDefaultValue(defaultValue, this.minLength, this.maxLength);

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

    /**
     * Set argument string min. length
     * 
     * @param minLength argument string min. length
     * @throws IllegalArgumentException Minimum length can't be negative number
     * nor can't be greater than maximum length
     */
    public void setMinLength(int minLength) throws IllegalArgumentException {
        if (minLength < 0) {
            throw new IllegalArgumentException(
                        "Minimum length can't be negative number.");
        }

        if (minLength > this.maxLength) {
            throw new IllegalArgumentException(
                        "Minimum length can't be greater than maximum length.");
        }

        if (this.hasDefaultValue) {
            validateDefaultValue(this.defaultValue, minLength, this.maxLength);
        }

        this.minLength = minLength;
    }

    /**
     * Get argument string min. length
     * 
     * @return argument string min. length
     */
    public int getMinLength() {
        return this.minLength;
    }

    /**
     * Set argument string max. length
     * 
     * @param maxLength  argument string max. length
     * @throws IllegalArgumentException Maximum length can't be lesser than 
     * minimum length nor can't be lesser than 1.
     */
    public void setMaxLength(int maxLength) throws IllegalArgumentException {
        if (maxLength < this.minLength) {
            throw new IllegalArgumentException(
                "Maximum length can't be lesser than minimum length.");
        }

        if (maxLength < 1) {
            throw new IllegalArgumentException(
                            "Maximum length can't be lesser than 1.");
        }

        if (this.hasDefaultValue) {
            validateDefaultValue(this.defaultValue, this.minLength, maxLength);
        }

        this.maxLength = maxLength;
    }

    /**
     * Get argument string max. length
     * 
     * @return argument string max. length
     */
    public int getMaxLength() {
            return this.maxLength;
    }

    /**
     * Validate default value length with min. and max. value length before
     * setting
     * 
     * @param defaultValue argument default value
     * @param minLength argument string min. length
     * @param maxLength argument string max. length
     * @throws IllegalArgumentException Default value length can't be lesser 
     * than minimum length nor be greater than maximum length.
     */
    protected void validateDefaultValue(String defaultValue, int minLength,
                    int maxLength) throws IllegalArgumentException {
        if (defaultValue.length() < minLength) {
            throw new IllegalArgumentException(
                    "Default value length can't be lesser than minimum length.");
        }

        if (defaultValue.length() > maxLength) {
            throw new IllegalArgumentException(
                    "Default value length can't be greater than maximum length.");
        }	
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