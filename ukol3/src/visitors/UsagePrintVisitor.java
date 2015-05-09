package visitors;

import elements.Argument;
import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;

public class UsagePrintVisitor implements Visitor {

	@Override
	public void visit(BooleanArgument argument) {
		printArgument(argument);
	}

	@Override
	public void visit(EnumeratedArgument argument) {
		printArgument(argument);
	}

	@Override
	public void visit(IntegerArgument argument) {
		printArgument(argument);
	}

	@Override
	public void visit(StringArgument argument) {
		printArgument(argument);
	}

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

	@Override
	public void visit(BooleanArgument argument, Option option) {
		// do nothing		
	}

	@Override
	public void visit(EnumeratedArgument argument, Option option) {
		// do nothing		
	}

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
