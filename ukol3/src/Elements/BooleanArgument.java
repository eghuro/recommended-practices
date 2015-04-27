package Elements;


public class BooleanArgument extends Argument {

    private BooleanArgument(BooleanArgumentBuilder builder) {
        super(builder);
    }

    @Override
    public boolean accept(String value) {
        return value.toLowerCase().equals("true");
    }
    
    protected static class BooleanArgumentBuilder extends Argument.ArgumentBuilder {
        protected BooleanArgumentBuilder() {}
        
        @Override
        public Argument build() {
            return new BooleanArgument(this);
        }
        
    }

}
