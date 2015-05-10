package visitors;

import elements.BooleanArgument;
import elements.EnumeratedArgument;
import elements.IntegerArgument;
import elements.Option;
import elements.StringArgument;

public class SearchByNameVisitor implements Visitor {

	private String optionSearchName;

	private boolean optionNameExists = false;

	private Option foundOption = null;

	public SearchByNameVisitor(String optionSearchName) {

		this.optionSearchName = optionSearchName;
	}

	public void reset() {
		optionNameExists = false;
		foundOption = null;
	}

	@Override
	public void visit(BooleanArgument argument) {
		// do nothing
	}

	@Override
	public void visit(EnumeratedArgument argument) {
		// do nothing
	}

	@Override
	public void visit(IntegerArgument argument) {
		// do nothing
	}

	@Override
	public void visit(StringArgument argument) {
		// do nothing
	}

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

	public boolean optionNameFound() {
		return this.optionNameExists;
	}

	public Option getFoundOption() {

		return this.foundOption;
	}

}
