package Elements;


public class BooleanArgument extends Argument {

    private BooleanArgument(BooleanArgumentBuilder builder) {
        super(builder);
    }

    @Override
    public boolean accept(String value) {
        return value.toLowerCase().equals("true");
    }
    
    public static class BooleanArgumentBuilder extends Argument.ArgumentBuilder {   
        @Override
        public Argument build() {
            return new BooleanArgument(this);
        }   
    }
}
