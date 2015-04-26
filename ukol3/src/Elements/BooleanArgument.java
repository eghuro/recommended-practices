package Elements;


public class BooleanArgument extends Argument {

	public BooleanArgument() {
	}

    @Override
    public boolean accept(String value) {
        System.out.println("Boolean");
        return value.toLowerCase().equals("true");
    }

}
