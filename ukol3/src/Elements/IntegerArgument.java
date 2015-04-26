package Elements;


public class IntegerArgument extends Argument {

	public IntegerArgument(String name) {
		super(name);
	}
	
	/**
	 * Nastavi minimalnu hodnotu cisla(integeru).
	 *
	 * @param  value	min. hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMinValue(String value) {
		return null;
	}
	
	/**
	 * Nastavi maximalnu hodnotu cisla(integeru).
	 *
	 * @param  value	max. hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMaxValue(String value) {
		return null;
	}

}
