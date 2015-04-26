import Elements.Option;


public class CommandLine {

	public CommandLine() {
		
	}
	
	/**
	 * Zaregistrovanie vytvorenej vlastnosti.
	 *
	 * @param  option	option vytvorena vlasnost
	 * @see Option
	 */	
	public void registerOption(Option option) {
		
	}
	
	/**
	 * Vrati popis pouzitia (Helper) vlastnosti a argumentov.
	 *
	 * @return text pouzitia
	 */	
	public String getUsage() {
		return null;
	}
	
	/**
	 * Parsuje volby a argumenty prikazoveho riadka.
	 * 
	 * @param args volby z prikazoveho riadka
	 * @return vrati parsovany prikazovy riadok
	 * @see ParsedCommandLine
	 */
	public ParsedCommandLine parse(String[] args) {
		return null;
	}
}
