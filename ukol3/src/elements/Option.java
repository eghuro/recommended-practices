package elements;

import java.util.HashSet;
import java.util.Set;

/**
 * Option.
 * @author alex
 */
public final class Option {
    private final Set<String> SYNONYMS;
    private final String NAME;
    private final boolean REQUIERD;
    private final String DESCRIPTION;
    private final Argument ARGUMENT;

    /**
     * @param builder builder.
     */
    private Option(final OptionBuilder builder) {
        this.NAME = builder.NAME;
        this.ARGUMENT = builder.argument;
        this.DESCRIPTION = builder.description;
        this.REQUIERD = builder.required;
        this.SYNONYMS = builder.SYNONYMS;
    }

    /**
     * @return set of names for this option
     */
    public Set<String> getNames() {
        return this.SYNONYMS;
    }

    /**
     * @return Argument for this option
     */
    public Argument getArgument() {
        return this.ARGUMENT;
    }

    /**
     * @return is this option required?
     */
    public boolean isRequired() {
        return this.REQUIERD;
    }

    /**
     * @return description of the option
     */
    public String getDesription() {
        return this.DESCRIPTION;
    }

    @Override
    public String toString() {
        return this.NAME;
    }

    public static class OptionBuilder {
        private final Set<String> SYNONYMS;
        private final String NAME;
        private boolean required;
        private String description;
        private Argument argument;

        public OptionBuilder(String name) {
            this.NAME = name;
            this.SYNONYMS = new HashSet<>();
            this.SYNONYMS.add(name);
            this.argument = null;
            this.description = "";
            this.required = false;
        }

        /**
         * @return Built option
         */
        public final Option build() {
            return new Option(this);
        }

        /**
         * Nastavi ekvivaletne meno (synonymum) pre vlastnost.
	 * Pri kazdom pridani sa prida ekvivaletne meno do mnoziny mien.
	 *
	 * @param  name	ekvivaletne meno
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
        public final OptionBuilder setEquivalentName(final String name) {
            SYNONYMS.add(name);
            return this;
        }

        /**
	 * Nastavenie volby ako povinna. V pripade, ze volba nebude mat
	 * argumenty, tak vyhodi varovanie, kedze nema zmysel aby bola
	 * volba bez argumentov povinna.
	 *
	 * @param  isRequired je volba povinna?
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
        public final OptionBuilder setRequired(final boolean isRequired) {
            this.required = isRequired;
            return this;
        }

        /**
	 * Nastavenie popisu volby pre napovedu.
	 *
	 * @param  newDescription popis volby
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
        public final OptionBuilder setDescription(final String newDescription) {
            this.description = newDescription;
            return this;
        }

        /**
	 * Nastavenie argumentu pre volbu.
	 *
	 * @param  newArgument prijimany ARGUMENT pre volbu
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
        public final OptionBuilder setArgument(final Argument newArgument) {
            this.argument = newArgument;
            return this;
        }
    }
}