import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import elements.AllElementsTests;
import builders.AllBuildersTests;


@RunWith(Suite.class)
@SuiteClasses({ ParserTest.class, AllBuildersTests.class, 
	AllElementsTests.class })
public class AllTests {

}
