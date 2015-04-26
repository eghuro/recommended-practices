package Elements;

public class Option {

	public Option(String name) {

	}
	
	/**
	 * Nastavi ekvivaletne meno (synonymum) pre vlastnost.
	 * Pri kazdom pridani sa prida ekvivaletne meno do mnoziny mien. 
	 *
	 * @param  name	ekvivaletne meno
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Option setEquivalentName(String name) {
		return null;
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
		return null;
	}
	
	/**
	 * Nastavenie popisu volby pre napovedu.
	 *
	 * @param  description popis volby
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Option setDescription(String description) {
		return null;
	}
	
	/**
	 * Nastavenie argumentu pre volbu. 
	 *
	 * @param  argument prijimany argument pre volbu
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Option setArgument(Argument argument) {
		return null;
	}
	
	
	
	

}
