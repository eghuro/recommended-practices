package Elements;


public class StringArgument extends Argument {

	public StringArgument(String name) {
		super(name);
	}
	
	/**
	 * Nastavi minimalnu dlzku retazca (stringu) - argumentu.
	 *
	 * @param  minLength	min. dlzka
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMinLength(String minLength) {
		return null;
	}
	
	/**
	 * Nastavi maximalnu dlzku retazca (stringu) - argumentu.
	 *
	 * @param  maxLength	max. dlzka
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMaxLength(String maxLength) {
		return null;
	}

}
