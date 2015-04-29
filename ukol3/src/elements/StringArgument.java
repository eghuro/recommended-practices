package elements;

/**
 * StringArgument.
 * @author Alexander Mansurov <alexander.mansurov@gmail.com>
 */
public final class StringArgument extends Argument {
    private final int minLength, maxLength;

    /**
     * @param builder builder.
     */
    private StringArgument(final StringArgumentBuilder builder) {
        super(builder);
        this.maxLength = builder.maxLength;
        this.minLength = builder.minLength;
    }

    @Override
    public boolean accept(final String value) {
        return value.startsWith("\"") && value.endsWith("\"");
    }

    /**
     * StringArgumentBuilder.
     */
    public static class StringArgumentBuilder extends Argument.ArgumentBuilder {
        private int minLength, maxLength;

        /**
        * Nastavi minimalnu dlzku retazca (stringu) - argumentu.
        *
        * @param  newMinLength min. dlzka
        * @return vrati sam seba
        */
       public final StringArgumentBuilder setMinLength(final int newMinLength) {
            this.minLength = newMinLength;
            return this;
        }

        /**
        * Nastavi maximalnu dlzku retazca (stringu) - argumentu.
        *
        * @param  newMaxLength max. dlzka
        * @return vrati sam seba (kvoli chain of responsibility)
        */
       public final StringArgumentBuilder setMaxLength(final int newMaxLength) {
            this.maxLength = newMaxLength;
            return this;
        }

       /**
        * @return Build the argument
        */
        @Override
        public final Argument build() {
            return new StringArgument(this);
        }
    }
}