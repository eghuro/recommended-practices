package visitors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import elements.Argument;
import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;

public class OptionsPrintVisitor implements Visitor {

	@Override
	public void visit(BooleanArgument argument) {
		List<String> argumentConditions = new ArrayList<String>();
		
		argumentConditions.add("true/false");
		
		if (argument.hasDefaultValue()) {
			String defaultValue = (argument.getDefaultValue())? "true" : "false";
			
			argumentConditions.add("default value: " +  defaultValue);			
		}
		
		printArgument(argument, argumentConditions);		
	}

	@Override
	public void visit(EnumeratedArgument argument) {
		List<String> argumentConditions = new ArrayList<String>();
		
		Set<String> enumeratedValues = argument.getValues();
		
		argumentConditions.add("use value: " + String.join("/", enumeratedValues));
		
		if (argument.hasDefaultValue()) {			
			argumentConditions.add("default value: " +  argument.getDefaultValue());			
		}
		
		printArgument(argument, argumentConditions);
	}

	@Override
	public void visit(IntegerArgument argument) {	
		List<String> argumentConditions = new ArrayList<String>();
		
		if (argument.getMinValue() > Integer.MIN_VALUE) {
			argumentConditions.add("min. value: "  + argument.getMinValue());
		}
		
		if (argument.getMaxValue() < Integer.MAX_VALUE) {
			argumentConditions.add("max. value: "  + argument.getMaxValue());
		}
		
		if (argument.hasDefaultValue()) {
			argumentConditions.add("default value: "  + argument.getDefaultValue());
		}
		
		printArgument(argument, argumentConditions);		
	}

	@Override
	public void visit(StringArgument argument) {
		List<String> argumentConditions = new ArrayList<String>();
		
		if (argument.getMinLength() > 0) {
			argumentConditions.add("min. length: "  + argument.getMinLength());
		}
		
		if (argument.getMaxLength() < Integer.MAX_VALUE) {
			argumentConditions.add("max. length: "  + argument.getMaxLength());
		}
		
		if (argument.hasDefaultValue()) {
			argumentConditions.add("default value: "  + argument.getDefaultValue());
		}
		
		printArgument(argument, argumentConditions);		
	}

	@Override
	public void visit(Option option) {
		System.out.print(" ");
			
		System.out.print(option.getNameWithPrefix());
		
		if (option.hasArgument()) {
			option.getArgument().accept(this);
		}
		
		System.out.println("\t" + option.getDesription());
		
		if (!option.getNames().isEmpty()) {
			for (String argumentSynonym: option.getNamesWithPrefix()) {
				System.out.println("  " + argumentSynonym);				
	    	}
		}		
	}
	
	private void printArgument(Argument argument, List<String> argumentConditions) {
		System.out.print(" ");

		if (!argument.isRequired()) {
			System.out.print("[");
		}
		
		System.out.print("<" + argument.getName() + ">");
		
		if (!argumentConditions.isEmpty()) {
			System.out.print(" { " + String.join(", ", argumentConditions) + " }");
		}
		
		if (!argument.isRequired()) {
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

}
