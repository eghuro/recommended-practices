package Elements;

import java.util.HashSet;
import java.util.Set;


public class EnumeratedArgument extends Argument {
        private final Set<String> VALUES;
	public EnumeratedArgument(EnumeratedArgumentBuilder builder) {
            super(builder);
            this.VALUES = builder.VALUES;
	}
        
    @Override
    public boolean accept(String value) {
        return VALUES.contains(value);
    }
    
    public static class EnumeratedArgumentBuilder extends Argument.ArgumentBuilder {
        private final Set<String> VALUES;
        
        public EnumeratedArgumentBuilder() {
            this.VALUES = new HashSet<>();
        }
        
        /**
	 * Nastavi mozne vymenovane(enum) hodnoty. 
	 *
	 * @param  value	hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public EnumeratedArgumentBuilder setPossibleValue(String value) {
                VALUES.add(value);
		return this;
	}
        
        @Override
        public Argument build() {
            return new EnumeratedArgument(this);
        }
        
    }

}
