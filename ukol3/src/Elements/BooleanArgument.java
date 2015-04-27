package Elements;


public class BooleanArgument extends Argument {

	public BooleanArgument() {
	}

    @Override
    public boolean accept(String value) {
        return value.toLowerCase().equals("true");
    }

}
