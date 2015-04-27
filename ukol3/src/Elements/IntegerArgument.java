package Elements;

public class IntegerArgument extends Argument {
    private final int minValue, maxValue;

    public IntegerArgument(IntegerArgumentBuilder builder) {
        super(builder);
        this.minValue = builder.minValue;
        this.maxValue = builder.maxValue;
    }

    @Override
    public boolean accept(String value) {
        try{
            Integer.parseInt(value);
        }catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static class IntegerArgumentBuilder extends Argument.ArgumentBuilder {
        private int minValue, maxValue;
        
        /**
	 * Nastavi minimalnu hodnotu cisla(integeru).
	 *
	 * @param  value	min. hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public IntegerArgumentBuilder setMinValue(int value) {
                this.minValue = value;
		return this;
	}
	
	/**
	 * Nastavi maximalnu hodnotu cisla(integeru).
	 *
	 * @param  value	max. hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public IntegerArgumentBuilder setMaxValue(int value) {
                this.maxValue = value;
		return this;
	}
        
        @Override
        public Argument build() {
            return new IntegerArgument(this);
        }
    }
}
