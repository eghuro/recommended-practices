package builders;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BooleanArgBuilderTest.class, EnumeratedArgBuilderTest.class,
		IntegerArgBuilderTest.class, StringArgBuilderTest.class })
public class AllBuildersTests {

}
