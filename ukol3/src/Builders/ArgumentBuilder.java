package Builders;

import Elements.BooleanArgument;
import Elements.EnumeratedArgument;
import Elements.IntegerArgument;
import Elements.StringArgument;

public class ArgumentBuilder {

	private ArgumentBuilder() {
	
	}
	
	/**
	 * Vytvori argument, ktory prijima retazec(string).
	 *
	 * @return  objekt argumentu typu string
	 * @see StringArgument
	 */	
	public static StringArgument stringType() {
		return new StringArgument();
	}
	
	/**
	 * Vytvori argument, ktory prijima cislo(integer).
	 *
	 * @return  objekt argumentu typu integer
	 * @see IntegerArgument
	 */	
	public static IntegerArgument integerType() {
		return new IntegerArgument();
	}
	
	/**
	 * Vytvori argument, ktory prijima vymenovane hodnoty.
	 *
	 * @return  objekt argumentu typu vymenovanych hodnot
	 * @see EnumeratedArgument
	 */	
	public static EnumeratedArgument enumType() {
		return new EnumeratedArgument();
	}	
	
	/**
	 * Vytvori argument, ktory prijima boolean hodnoty.
	 *
	 * @return  objekt argumentu typu boolean
	 * @see BooleanArgument
	 */
	public static BooleanArgument booleanType() {
		return null;
	}
	
	

}