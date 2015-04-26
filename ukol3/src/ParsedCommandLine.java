import Elements.Option;

public class ParsedCommandLine {

	public ParsedCommandLine() {
		
	}
	
	/**
	 * Zisti, ze ci bolo v prikazovom riadku dana vlastnost.
	 *
	 * @param  optionName	nazov vlastnosti
	 * @return info, ze ci sa nachadza vlasnost v prikazovom riadku
	 */	
	public boolean hasOption(String optionName) {
		return false;
	}

	/**
	 * Vrati hodnotu argumentu pre vlastnost.
	 *
	 * @param  optionName	nazov vlastnosti
	 * @return hodnota argumentu pre danu vlastnost z prikazoveho riadku
	 */		
	public String getOptionValue(String optionName) {
		return null;
	}

	/**
	 * Vrati uspech, ked sa podarilo v poriadku rozparsovat prikazovy riadok.
	 *
	 * @return  uspech parsovania
	 */		
	public boolean success() {
		return false;
	}
	
	/**
	 * Vrati zoznam chyb, ked sa nepodarilo v poriadku 
	 * rozparsovat prikazovy riadok.
	 *
	 * @return  zoznam chyb
	 */	  
	public String getErrors() {
		return null;
	}
	
	/**
	 * Vrati bezne argumenty uvedene na konci prikazoveho riadka, 
	 * ktore neboli priradene k ziadnej vlastnosti.
	 *
	 * @return  zoznam argumentov
	 */	       
	public String[] getCommonArguments() {
		return null;
	}

}
