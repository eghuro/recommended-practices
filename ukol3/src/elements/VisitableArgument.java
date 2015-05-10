package elements;

import visitors.Visitor;

public interface VisitableArgument extends Visitable {

	public void accept(Visitor visitor, Option option);
	
}
