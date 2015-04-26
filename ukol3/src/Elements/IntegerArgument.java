package Elements;


public class IntegerArgument extends Argument {
        private int minValue, maxValue;

	public IntegerArgument() {
	}
	
	/**
	 * Nastavi minimalnu hodnotu cisla(integeru).
	 *
	 * @param  value	min. hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMinValue(int value) {
                this.minValue = value;
		return this;
	}
	
	/**
	 * Nastavi maximalnu hodnotu cisla(integeru).
	 *
	 * @param  value	max. hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setMaxValue(int value) {
                this.maxValue = value;
		return this;
	}

    @Override
    public boolean accept(String value) {
            System.out.println("Integer");
            try{
                Integer.parseInt(value);
            }catch(NumberFormatException e) {
                return false;
            }
            return true;
    }

}
