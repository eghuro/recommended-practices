package Elements;

public abstract class Argument {
	
	/**
	 * Vytvori argument s danym nazvom. 
	 *
	 * @param  name	nazov argumentu
	 */	
	public Argument(String name) {

	}
	
	/**
	 * Nastavi popis pre argument. 
	 *
	 * @param  description	popis argumentu
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument setDescription(String description) {
		return null;
	}
	
	/**
	 * Nastavi defaultnu hodnotu pre argument. 
	 *
	 * @param  value	defaultna hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument setDefaultValue(String value) {
		return null;
	}
	
	/**
	 * Nastavi, ze ci argument akceptuje niekolko(zoznam) hodnot oddelenych 
	 * oddelovacom (napr: 1,2,4). 
	 *
	 * @param  accept	akceptovat zoznam hodnot?
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument acceptListOfValues(boolean accept) {
		return null;
	}
	
	/**
	 * Nastavi separator pre zoznam hodnot (napr: ciarku). 
	 *
	 * @param  separator	znak, ktory sa pouzije ako separator
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument setListSeparator(String separator) {
		return null;
	}	
	
	/**
	 * Nastavi minimalny pocet hodnot v zozname. 
	 *
	 * @param  number	min. pocet hodnot
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument minListValues(int number) {
		return null;
	}
	
	/**
	 * Nastavi maximalny pocet hodnot v zozname. 
	 *
	 * @param  number	max. pocet hodnot
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument maxListValues(int number) {
		return null;
	}
	
}
