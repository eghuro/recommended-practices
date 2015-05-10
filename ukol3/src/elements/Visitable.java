package elements;

import visitors.Visitor;

public interface Visitable {

	public void accept(Visitor visitor);
	
}