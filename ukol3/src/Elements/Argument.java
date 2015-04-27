package Elements;

public abstract class Argument {
        private final String description;
        private final String defaultValue;
        private final String separator;
        private final boolean acceptList;
        private final int minVal;
        private final int maxVal;
    
	/**
	 * Vytvori argument  s danym nazvom. 
	 *
	 // * @param  name	nazov argumentu
         *  @param builder
	 */	
	protected Argument(ArgumentBuilder builder) {
            this.defaultValue = builder.defaultValue;
            this.acceptList = builder.acceptList;
            this.separator = builder.separator;
            this.maxVal = builder.maxVal;
            this.minVal = builder.minVal;
            this.description = builder.description;
	}
	
        public abstract boolean accept(String value);
        
        public String getDefaultValue() {
               return this.defaultValue;
           }
	
        public static abstract class ArgumentBuilder {
            private String description;
            private String defaultValue;
            private String separator;
            private boolean acceptList;
            private int minVal;
            private int maxVal;
            
            protected ArgumentBuilder(){}
            
            public abstract Argument build();
        
            /**
            * Nastavi popis pre argument. 
            *
            * @param  description	popis argumentu
            * @return vrati sam seba (kvoli chain of responsibility)
            */	
           public ArgumentBuilder setDescription(String description) {
                   this.description = description;
                   return this;
           }

           /**
            * Nastavi defaultnu hodnotu pre argument. 
            *
            * @param  value	defaultna hodnota
            * @return vrati sam seba (kvoli chain of responsibility)
            */	
           public ArgumentBuilder setDefaultValue(String value) {
                   this.defaultValue = value;
                   return this;
           }
           
           /**
            * Nastavi, ze ci argument akceptuje niekolko(zoznam) hodnot oddelenych 
            * oddelovacom (napr: 1,2,4). 
            *
            * @param  accept	akceptovat zoznam hodnot?
            * @return vrati sam seba (kvoli chain of responsibility)
            */	
           public ArgumentBuilder acceptListOfValues(boolean accept) {
                   this.acceptList = accept;
                   return this;
           }

           /**
            * Nastavi separator pre zoznam hodnot (napr: ciarku). 
            *
            * @param  separator	znak, ktory sa pouzije ako separator
            * @return vrati sam seba (kvoli chain of responsibility)
            */	
           public ArgumentBuilder setListSeparator(String separator) {
                   this.separator = separator;
                   return this;
           }	

           /**
            * Nastavi minimalny pocet hodnot v zozname. 
            *
            * @param  number	min. pocet hodnot
            * @return vrati sam seba (kvoli chain of responsibility)
            */
           public ArgumentBuilder minListValues(int number) {
                   this.minVal = number;
                   return this;
           }

           /**
            * Nastavi maximalny pocet hodnot v zozname. 
            *
            * @param  number	max. pocet hodnot
            * @return vrati sam seba (kvoli chain of responsibility)
            */
           public ArgumentBuilder maxListValues(int number) {
                   this.maxVal = number;
                   return this;
           }
        }
}
