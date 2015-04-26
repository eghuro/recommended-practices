package Elements;

import java.util.HashSet;
import java.util.Set;

public class Option {
        private final Set<String> SYNONYMS;
        //private final Set<Argument> ARGUMENTS;
        private Argument argument;
        
        private boolean required;
        private String description;
        
	public Option(String name) {
            this.SYNONYMS = new HashSet<>();
            //this.ARGUMENTS = new HashSet<>();
            
            this.SYNONYMS.add(name);
            
            this.required = false;
            this.description = null;
	}
	
	/**
	 * Nastavi ekvivaletne meno (synonymum) pre vlastnost.
	 * Pri kazdom pridani sa prida ekvivaletne meno do mnoziny mien. 
	 *
	 * @param  name	ekvivaletne meno
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Option setEquivalentName(String name) {
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
	public Option setRequired(boolean required) {
                this.required = required;
		return this;
	}
	
	/**
	 * Nastavenie popisu volby pre napovedu.
	 *
	 * @param  description popis volby
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Option setDescription(String description) {
                this.description = description;
		return this;
	}
	
	/**
	 * Nastavenie argumentu pre volbu. 
	 *
	 * @param  argument prijimany argument pre volbu
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Option setArgument(Argument argument) {
                //ARGUMENTS.add(argument);
                this.argument = argument;
		return this;
	}
        
        public Set<String> getNames() {
            return this.SYNONYMS;
        }
        
        public Argument getArgument() {
            return this.argument;
        }
}
