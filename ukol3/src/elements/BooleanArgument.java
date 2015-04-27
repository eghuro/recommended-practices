package elements;

/**
 *
 * @author Alexander Mansurov <alexander.mansurov@gmail.com>
 */
public final class BooleanArgument extends Argument {

    /**
     *
     * @param builder builder
     */
    private BooleanArgument(final BooleanArgumentBuilder builder) {
        super(builder);
    }

    @Override
    public boolean accept(final String value) {
        return value.toLowerCase().equals("true");
    }

    /**
     * BooleanArgumentBuilder.
     */
    public static class BooleanArgumentBuilder extends
            Argument.ArgumentBuilder {
        /**
         * Build.
         * @return Argument
         */
        @Override
        public final Argument build() {
            return new BooleanArgument(this);
        }
    }
}
