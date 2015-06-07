package exercise.library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class IsbnValidatorTest {

	private final IsbnValidator instance = new IsbnValidator( "ISBN-\\d+$" );

	@DataProvider( name = "isbn" )
	public static Object[][] getIsbnCodes( ) {
		return new Object[][] {
				{"", false},
				{"001", false},
				{"ISBN-", false},
				{"isbn-001", false},
				{"ISBN 001", false},
				{"ISBN", false},
				{"xxx", false},
				{"ISBN-00A", false},
				{"ISBN-001", true},
				{"ISBN-1", true},
				{"ISBN-999999999999", true}
		};
	}

	@Test(dataProvider = "isbn")
	public void test( String isbn, boolean shouldBeValid ) {
		assertEquals( instance.isValid( isbn ), shouldBeValid );
	}
}
