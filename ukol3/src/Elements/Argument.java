package Elements;

public abstract class Argument {
        // private final String NAME;
        
        private String description;
        private String defaultValue;
        private String separator;
        private boolean acceptList;
        private int minVal;
        private int maxVal;
    
	/**
	 * Vytvori argument 
         // s danym nazvom. 
	 *
	 // * @param  name	nazov argumentu
	 */	
	public Argument() {
	}
	
	/**
	 * Nastavi popis pre argument. 
	 *
	 * @param  description	popis argumentu
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument setDescription(String description) {
                this.description = description;
		return this;
	}
	
	/**
	 * Nastavi defaultnu hodnotu pre argument. 
	 *
	 * @param  value	defaultna hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument setDefaultValue(String value) {
                this.defaultValue = value;
		return this;
	}
        
        public String getDefaultValue() {
            return this.defaultValue;
        }
	
	/**
	 * Nastavi, ze ci argument akceptuje niekolko(zoznam) hodnot oddelenych 
	 * oddelovacom (napr: 1,2,4). 
	 *
	 * @param  accept	akceptovat zoznam hodnot?
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument acceptListOfValues(boolean accept) {
                this.acceptList = accept;
		return this;
	}
	
	/**
	 * Nastavi separator pre zoznam hodnot (napr: ciarku). 
	 *
	 * @param  separator	znak, ktory sa pouzije ako separator
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */	
	public Argument setListSeparator(String separator) {
                this.separator = separator;
		return this;
	}	
	
	/**
	 * Nastavi minimalny pocet hodnot v zozname. 
	 *
	 * @param  number	min. pocet hodnot
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument minListValues(int number) {
                this.minVal = number;
		return this;
	}
	
	/**
	 * Nastavi maximalny pocet hodnot v zozname. 
	 *
	 * @param  number	max. pocet hodnot
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument maxListValues(int number) {
                this.maxVal = number;
		return this;
	}
        
        public abstract boolean accept(String value);
	
}
