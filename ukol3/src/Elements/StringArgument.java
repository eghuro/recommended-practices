package Elements;


public class StringArgument extends Argument {
        private int minLength, maxLength;
        
	public StringArgument() {
	}
	
	/**
	 * Nastavi minimalnu dlzku retazca (stringu) - argumentu.
	 *
	 * @param  minLength	min. dlzka
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMinLength(int minLength) {
                this.minLength = minLength;
		return this;
	}
	
	/**
	 * Nastavi maximalnu dlzku retazca (stringu) - argumentu.
	 *
	 * @param  maxLength	max. dlzka
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMaxLength(int maxLength) {
                this.maxLength = maxLength;
		return this;
	}

        @Override
        public boolean accept(String value) {
            return value.startsWith("\"") && value.endsWith("\"");
        }
}
