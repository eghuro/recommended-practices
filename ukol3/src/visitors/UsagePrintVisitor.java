package visitors;

import elements.Argument;
import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;

/**
 * Prints usage
 */
public class UsagePrintVisitor implements Visitor {
    
    /**
     * Add boolean argument into usage
     * @param argument argument to add
     */
    @Override
    public void visit(BooleanArgument argument) {
        printArgument(argument);
    }

    /**
     * Add enum argument into usage
     * @param argument argument to add
     */
    @Override
    public void visit(EnumeratedArgument argument) {
        printArgument(argument);
    }

    /**
     * Add integer argument into usage
     * @param argument argument to add
     */
    @Override
    public void visit(IntegerArgument argument) {
        printArgument(argument);
    }

    /**
     * Add string argument into usage.
     * @param argument argument to add
     */
    @Override
    public void visit(StringArgument argument) {
        printArgument(argument);
    }

    /**
     * Add option into usage
     * @param option option to add
     */
    @Override
    public void visit(Option option) {
        System.out.print(" ");

        if (!option.isRequired()) {
            System.out.print("[");
        }

        System.out.print(option.getNameWithPrefix());

        if (option.hasArgument()) {
            option.getArgument().accept(this);
        }

        if (!option.isRequired()) {
            System.out.print("]");
        }
    }

    /**
     * Do nothing
     * @param argument
     * @param option 
     */
    @Override
    public void visit(BooleanArgument argument, Option option) {
        // do nothing
    }

    /**
     * Do nothing
     * @param argument
     * @param option 
     */
    @Override
    public void visit(EnumeratedArgument argument, Option option) {
        // do nothing
    }

    /**
     * Do nothing
     * @param argument
     * @param option 
     */
    @Override
    public void visit(IntegerArgument argument, Option option) {
        // do nothing
    }

    @Override
    public void visit(StringArgument argument, Option option) {
        // do nothing
    }

    private void printArgument(Argument argument) {
        System.out.print(" ");

        if (!argument.isRequired()) {
                System.out.print("[");
        }

        System.out.print("<" + argument.getName() + ">");

        if (!argument.isRequired()) {
                System.out.print("]");
        }
    }

}