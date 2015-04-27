package Elements;

import java.util.HashSet;
import java.util.Set;


public class EnumeratedArgument extends Argument {
        private final Set<String> VALUES;
	public EnumeratedArgument() {
                this.VALUES = new HashSet<>();
	}
	
	/**
	 * Nastavi mozne vymenovane(enum) hodnoty. 
	 *
	 * @param  value	hodnota
	 * @return vrati sam seba (kvoli chain of responsibility)
	 */
	public Argument setPossibleValue(String value) {
                VALUES.add(value);
		return this;
	}

    @Override
    public boolean accept(String value) {
        return VALUES.contains(value);
    }

}
