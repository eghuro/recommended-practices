package Elements;


public class StringArgument extends Argument {
        private final int minLength, maxLength;
        
	private StringArgument(StringArgumentBuilder builder) {
            super(builder);
            this.maxLength = builder.maxLength;
            this.minLength = builder.minLength;
	}

        @Override
        public boolean accept(String value) {
            return value.startsWith("\"") && value.endsWith("\"");
        }
        
        public static class StringArgumentBuilder extends Argument.ArgumentBuilder {
            private int minLength, maxLength;
            
            protected StringArgumentBuilder() {}
            
            /**
            * Nastavi minimalnu dlzku retazca (stringu) - argumentu.
            *
            * @param  minLength	min. dlzka
            * @return vrati sam seba (kvoli chain of responsibility)
            */
           public StringArgumentBuilder setMinLength(int minLength) {
                   this.minLength = minLength;
                   return this;
           }
           
           /**
            * Nastavi maximalnu dlzku retazca (stringu) - argumentu.
            *
            * @param  maxLength	max. dlzka
            * @return vrati sam seba (kvoli chain of responsibility)
            */
           public StringArgumentBuilder setMaxLength(int maxLength) {
                   this.maxLength = maxLength;
                   return this;
            }
        
            @Override
            public Argument build() {
                return new StringArgument(this);
            }
            
        }
}
