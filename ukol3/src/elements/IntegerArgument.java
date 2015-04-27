package elements;

/**
 *
 * @author Alexander Mansurov <alexander.mansurov@gmail.com>
 */
public final class IntegerArgument extends Argument {
    private final int minValue, maxValue;

    /**
     * Construct new IntegerArgument.
     * @param builder builder
     */
    public IntegerArgument(final IntegerArgumentBuilder builder) {
        super(builder);
        this.minValue = builder.minValue;
        this.maxValue = builder.maxValue;
    }

    @Override
    public boolean accept(final String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * IntegerArgumentBuilder.
     */
    public static class IntegerArgumentBuilder extends
            Argument.ArgumentBuilder {
        private int minValue, maxValue;

        /**
        * Nastavi minimalnu hodnotu cisla(integeru).
        *
        * @param value min. hodnota
        * @return vrati sam seba
        */
        public final IntegerArgumentBuilder setMinValue(final int value) {
            this.minValue = value;
            return this;
        }

        /**
	* Nastavi maximalnu hodnotu cisla(integeru).
	*
	* @param  value	max. hodnota
	* @return vrati sam seba (kvoli chain of responsibility)
	 */
	public final IntegerArgumentBuilder setMaxValue(final int value) {
            this.maxValue = value;
            return this;
	}

        /**
         *
         * @return integer argument
         */
        @Override
        public final Argument build() {
            return new IntegerArgument(this);
        }
    }
}
