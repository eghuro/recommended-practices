package Builders;

import Elements.BooleanArgument;
import Elements.EnumeratedArgument;
import Elements.IntegerArgument;
import Elements.StringArgument;

@Deprecated
public class ArgumentBuilder {

	public ArgumentBuilder() {
	
	}
	
	/**
	 * Vytvori argument, ktory prijima retazec(string).
	 *
	 * @return  objekt argumentu typu string
	 * @see StringArgument
	 */	
	public static StringArgument stringType() {
		return null;
	}
	
	/**
	 * Vytvori argument, ktory prijima cislo(integer).
	 *
	 * @return  objekt argumentu typu integer
	 * @see IntegerArgument
	 */	
	public static IntegerArgument integerType() {
		return null;
	}
	
	/**
	 * Vytvori argument, ktory prijima vymenovane hodnoty.
	 *
	 * @return  objekt argumentu typu vymenovanych hodnot
	 * @see EnumeratedArgument
	 */	
	public static EnumeratedArgument enumType() {
		return null;
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
