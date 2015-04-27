package Elements;

import java.util.HashSet;
import java.util.Set;

public class Option {
        private final Set<String> SYNONYMS;
        private final String NAME;
        private final boolean REQUIERD;
        private final String DESCRIPTION;
        private final Argument ARGUMENT;
        
        private Option(OptionBuilder builder) {
            this.NAME = builder.NAME;
            this.ARGUMENT = builder.argument;
            this.DESCRIPTION = builder.description;
            this.REQUIERD = builder.required;
            this.SYNONYMS = builder.SYNONYMS;
        }
        
        public Set<String> getNames() {
            return this.SYNONYMS;
        }
        
        public Argument getArgument() {
            return this.ARGUMENT;
        }
        
        public boolean isRequired() {
            return this.REQUIERD;
        }
        
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
            
            public Option build() {
                return new Option(this);
            }
            
            /**
	 * Nastavi ekvivaletne meno (synonymum) pre vlastnost.
	 * Pri kazdom pridani sa prida ekvivaletne meno do mnoziny mien. 
	 *
	 * @param  name	ekvivaletne meno
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
            public OptionBuilder setEquivalentName(String name) {
                SYNONYMS.add(name);
                return this;
            }
            
            /**
	 * Nastavenie volby ako povinna. V pripade, ze volba nebude mat 
	 * argumenty, tak vyhodi varovanie, kedze nema zmysel aby bola 
	 * volba bez argumentov povinna.
	 *
	 * @param  required je volba povinna?
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
            public OptionBuilder setRequired(boolean required) {
                this.required = required;
                return this;
            }
            
            /**
	 * Nastavenie popisu volby pre napovedu.
	 *
	 * @param  description popis volby
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
            public OptionBuilder setDescription(String description) {
                this.description = description;
                return this;
            }
            
            /**
	 * Nastavenie argumentu pre volbu. 
	 *
	 * @param  argument prijimany ARGUMENT pre volbu
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
            public OptionBuilder setArgument(Argument argument) {
                this.argument = argument;
                return this;
            }
        }
}
