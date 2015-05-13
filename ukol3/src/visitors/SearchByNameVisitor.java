package visitors;

import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;

/**
 * Lookup option by name
 */
public class SearchByNameVisitor implements Visitor {
    private final String optionSearchName;
    
    private boolean optionNameExists;
    private Option foundOption;

    public SearchByNameVisitor(String optionSearchName) {
        this.optionSearchName = optionSearchName;
        reset();
    }

    /**
     * Reset 
     */
    public void reset() {
        optionNameExists = false;
        foundOption = null;
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(BooleanArgument argument) {
        // do nothing
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(EnumeratedArgument argument) {
        // do nothing
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(IntegerArgument argument) {
        // do nothing
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(StringArgument argument) {
        // do nothing
    }

    /**
     * Process an option and alter internal state if looking for this particular
     * option name.
     * @param option option to process
     */
    @Override
    public void visit(Option option) {
        boolean found = false;

        found |= this.optionSearchName.equals(option.getNameWithPrefix());

        for (String argumentSynonym : option.getNamesWithPrefix()) {
            found |= this.optionSearchName.equals(argumentSynonym);
        }

        this.optionNameExists |= found;

        if (found) {
            foundOption = option;
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

    /**
     * Do nothing
     * @param argument
     * @param option 
     */
    @Override
    public void visit(StringArgument argument, Option option) {
        // do nothing
    }

    /**
     * If option name was already found
     * @return Option name was already found
     */
    public boolean optionNameFound() {
        return this.optionNameExists;
    }

    /**
     * Access to the found option
     * @return the option found or null if not found
     * @see SearchByNameVisitor.optionNameFound()
     */
    public Option getFoundOption() {
        return this.foundOption;
    }
}