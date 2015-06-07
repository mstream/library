package exercise.library;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class BookSummaryBuilderTest {

	private BookSummaryBuilder instance;

	@Test
	public void shouldBuildSummary( ) throws Exception {
		instance = new BookSummaryBuilder( "%s:%s:%s", 2 );
		Book exampleBook = new Book( "ISBN-123", "Title", "Description description description description." );
		String summary = instance.build( exampleBook );
		assertEquals( summary, "ISBN-123:Title:Description description..." );
	}

	@Test
	public void shouldHandleMissingBookInfo( ) throws Exception {
		instance = new BookSummaryBuilder( "%s:%s:%s", 2 );
		Book exampleBook = new Book( "ISBN-123", "Title", null );
		instance.build( exampleBook );
	}
}