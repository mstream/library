package exercise.library;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

import static org.testng.Assert.*;


public class BookServiceImplTest {

	private BookService instance;

	@Test
	public void shouldRetrieveBookByIsbn( ) throws Exception {
		Book exampleBook = new Book( "ISBN-001", "Some Title", "Some description." );
		Map<String, Book> books = Collections.singletonMap( exampleBook.getIsbn( ), exampleBook );
		instance = new BookServiceImpl( books::get );
		Book book = instance.retrieveBook( "ISBN-001" );
		assertSame( book, exampleBook );
	}

	@Test
	public void shouldEnsureThatIsbnIsWellFormattedWhenRetrieving( ) throws Exception {
		instance = new BookServiceImpl( isbn -> null );
		try {
			instance.retrieveBook( "001" );
		} catch ( IllegalArgumentException e ) {
			assertEquals( e.getMessage( ), "The book isbn must begin with 'ISBN-'" );
			return;
		}
		fail( "IllegalArgumentException has not been thrown." );
	}

	@Test( expectedExceptions = { BookNotFoundException.class } )
	public void shouldThrowBookNotFoundExceptionIfBookDoesNotExistWhenRetrieving( ) throws Exception {
		instance = new BookServiceImpl( isbn -> null );
		instance.retrieveBook( "ISBN-001" );
	}

	@Test
	public void shouldEnsureThatIsbnIsWellFormattedWhenAskingForSummary( ) throws Exception {
		instance = new BookServiceImpl( isbn -> null );
		try {
			instance.getBookSummary( "001" );
		} catch ( IllegalArgumentException e ) {
			assertEquals( e.getMessage( ), "The book isbn must begin with 'ISBN-'" );
			return;
		}
		fail( "IllegalArgumentException has not been thrown." );
	}

	@Test( expectedExceptions = { BookNotFoundException.class } )
	public void shouldThrowBookNotFoundExceptionIfBookDoesNotExistWhenAskingForSummary( ) throws Exception {
		instance = new BookServiceImpl( isbn -> null );
		instance.getBookSummary( "ISBN-001" );
	}

	@Test
	public void shouldReturnSummaryByIsbn( ) throws Exception {
		Book exampleBook = new Book( "ISBN-001", "Some Title", "Some description." );
		Map<String, Book> books = Collections.singletonMap( exampleBook.getIsbn( ), exampleBook );
		instance = new BookServiceImpl( books::get );
		String summary = instance.getBookSummary( "ISBN-001" );
		assertEquals( summary, "[ISBN-001] Some Title - Some description." );
	}

	@Test
	public void shouldShortenReviewToTenWordsWhenAskingForSummary( ) throws Exception {
		Book exampleBook = new Book( "ISBN-001", "Some Title",
				"One Two Three, Four Five Six. Seven Eight Nine, Ten Eleven Twelve." );
		Map<String, Book> books = Collections.singletonMap( exampleBook.getIsbn( ), exampleBook );
		instance = new BookServiceImpl( books::get );
		String summary = instance.getBookSummary( "ISBN-001" );
		assertEquals( summary, "[ISBN-001] Some Title - One Two Three, Four Five Six. Seven Eight Nine, Ten..." );
	}

}