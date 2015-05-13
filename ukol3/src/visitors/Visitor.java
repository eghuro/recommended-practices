package visitors;

import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;


/**
 * Visitor pattern
 */
public interface Visitor {

	public void visit(BooleanArgument argument);

	public void visit(EnumeratedArgument argument);

	public void visit(IntegerArgument argument);

	public void visit(StringArgument argument);

	public void visit(BooleanArgument argument, Option option);

	public void visit(EnumeratedArgument argument, Option option);

	public void visit(IntegerArgument argument, Option option);

	public void visit(StringArgument argument, Option option);

	public void visit(Option option);

}
