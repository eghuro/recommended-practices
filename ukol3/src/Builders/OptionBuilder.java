package Builders;
import Elements.Argument;
import Elements.BooleanArgument;
import Elements.Option;


public class OptionBuilder {

	private OptionBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Vytvori vlastnost, ktora bude akceptovatelna 
	 * z prikazoveho riadka.
	 *
	 * @param	name nazov(pomenovanie) vlastnosti
	 * @return novy objekt danej vlastnosti
	 * @see Option
	 */
	public static Option create(String name) {
		return new Option(name);		
	}

}
