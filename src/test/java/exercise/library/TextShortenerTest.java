package exercise.library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class TextShortenerTest {

	private final TextShortener instance = new TextShortener( 3 );

	@DataProvider( name = "summary" )
	public static Object[][] getIsbnCodes( ) {
		return new Object[][] {
				{ "one two three four five", "one two three..." },
				{ "one two three", "one two three" },
				{ "one two three   ", "one two three" },
				{ "one two three   four", "one two three..." },
				{ "one two three. four", "one two three...." },
				{ "one", "one" },
				{ "one   ", "one   " },
				{ "one - two - three - four", "one - two - three..." },
				{ "one's two three four", "one's two three..." },
				{ "", "" },
				{ null, null }
		};
	}

	@Test( dataProvider = "summary" )
	public void test( String description, String summary ) {
		assertEquals( instance.shorten( description ), summary );
	}
}