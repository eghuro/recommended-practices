package visitors;

import java.util.HashMap;
import java.util.HashSet;

import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;

/**
 * Validate argument for option
 */
public class ValidateVisitor implements Visitor {

    private HashMap<Option, String> optionsValues;

    private HashSet<String> errors = new HashSet<String>();

    public ValidateVisitor(HashMap<Option, String> optionsValues) {
        this.optionsValues = optionsValues;
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(BooleanArgument argument) {
        // do nothing;
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(EnumeratedArgument argument) {
        // do nothing;
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(IntegerArgument argument) {
        // do nothing;
    }

    /**
     * Do nothing
     * @param argument 
     */
    @Override
    public void visit(StringArgument argument) {
        // do nothing;
    }

    /**
     * Validate boolean argument for option
     * @param argument an argument
     * @param option an option
     */
    @Override
    public void visit(BooleanArgument argument, Option option) {
        String optionValue = this.optionsValues.get(option);

        if (argument.isRequired() && optionValue == null) {
            this.errors.add("Argument for option '"
                + option.getNameWithPrefix() + "' is required.");
        } else if (optionValue != null) {
            if (!optionValue.equals("true") && !optionValue.equals("false")) {
                this.errors.add("Argument for option '"
                    + option.getNameWithPrefix() + "' must be true/false.");
            }
        }
    }

    /**
     * Validate enum argument for option
     * @param argument argument
     * @param option option
     */
    @Override
    public void visit(EnumeratedArgument argument, Option option) {
        String optionValue = this.optionsValues.get(option);

        if (argument.isRequired() && optionValue == null) {
            this.errors.add("Argument for option '"
                + option.getNameWithPrefix() + "' is required.");
        } else if (optionValue != null) {
            if (!argument.getValues().contains(optionValue)) {
                this.errors.add("Argument for option '"
                    + option.getNameWithPrefix() + "' must be "
                    + String.join("/", argument.getValues()) + ".");
            }			
        }
    }	

    /**
     * Validate integer argument for option
     * @param argument argument
     * @param option option
     */
    @Override
    public void visit(IntegerArgument argument, Option option) {
        String optionValue = this.optionsValues.get(option);

        if (argument.isRequired() && optionValue == null) {
            this.errors.add("Argument for option '"
                + option.getNameWithPrefix() + "' is required.");
        } else if (optionValue != null) {
            if (!optionValue.matches("^[0-9]*$")) {
                this.errors.add("Argument for option '"
                    + option.getNameWithPrefix() + "' must be integer.");
            } else if (Integer.parseInt(optionValue) < argument.getMinValue()) {
                this.errors.add("Argument for option '" 
                    + option.getNameWithPrefix()
                    + "' must have minimum value " + argument.getMinValue()
                    + ".");
            } else if (Integer.parseInt(optionValue) > argument.getMaxValue()) {
                this.errors.add("Argument for option '"
                    + option.getNameWithPrefix()
                    + "' must have maximum value " + argument.getMaxValue()
                    + ".");
            }
        }
    }

    /**
     * Validate string argument for option
     * @param argument argument
     * @param option option
     */
    @Override
    public void visit(StringArgument argument, Option option) {
        
    }

    /**
     * Validate option
     * @param option option
     */
    @Override
    public void visit(Option option) {
        boolean isOptionUsed = this.optionsValues.containsKey(option);

        if (isOptionUsed) {
            if (option.hasArgument()) {
                option.getArgument().accept(this, option);
            }
        } else if (option.isRequired()) {
            this.errors.add("Option '" + option.getNameWithPrefix()
                + "' is required.");
        }
    }

    /**
     * Get validator errors
     * @return errors
     */
    public HashSet<String> getErrors() {
        return this.errors;
    }
}
