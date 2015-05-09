package elements;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BooleanArgTest.class, EnumeratedArgTest.class,
		IntegerArgTest.class, StringArgTest.class })
public class AllElementsTests {

}
