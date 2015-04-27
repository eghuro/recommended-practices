package elements;

import java.util.HashSet;
import java.util.Set;

/**
 * EnumeratedArgument.
 * @author Alexander Mansurov <alexander.mansurov@gmail.com>
 */
public final class EnumeratedArgument extends Argument {
    private final Set<String> VALUES;

    /**
     *
     * @param builder builder
     */
    public EnumeratedArgument(final EnumeratedArgumentBuilder builder) {
        super(builder);
        this.VALUES = builder.VALUES;
    }

    @Override
    public boolean accept(final String value) {
        return VALUES.contains(value);
    }

    /**
     * EnumeratedArgumentBuilder.
     */
    public static class EnumeratedArgumentBuilder extends
            Argument.ArgumentBuilder {
        private final Set<String> VALUES;

        /**
         * EnumeratedArgumentBuilder.
         */
        public EnumeratedArgumentBuilder() {
            this.VALUES = new HashSet<>();
        }

        /**
         * Nastavi mozne vymenovane (enum) hodnoty.
         *
         * @param  value hodnota
         * @return vrati sam seba
         */
        public final EnumeratedArgumentBuilder setPossibleValue(final String
                value) {
            VALUES.add(value);
            return this;
        }

        /**
         *
         * @return EnumeratedArgument
         */
        @Override
        public final Argument build() {
            return new EnumeratedArgument(this);
        }
    }

}
