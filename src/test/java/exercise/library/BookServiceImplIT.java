package exercise.library;

import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class BookServiceImplIT {

	private final BookService instance = new BookServiceImpl( new BookRepositoryImpl( ) );

	@Test
	public void shouldRetrieveHarryPotterIfIsbn001Given( ) throws Exception {
		Book book = instance.retrieveBook( "ISBN-001" );
		assertNotNull( book );
		assertEquals( book.getIsbn( ), "ISBN-001" );
		assertEquals( book.getTitle( ), "Harry Potter and the Deathly Hallows" );
	}

	@Test
	public void shouldEnsureThatIsbnIsWellFormattedWhenRetrieving( ) throws Exception {
		try {
			instance.retrieveBook( "INVALID-TEXT" );
		} catch ( IllegalArgumentException e ) {
			assertEquals( e.getMessage( ), "The book isbn must begin with 'ISBN-'" );
			return;
		}
		fail( "IllegalArgumentException has not been thrown." );
	}

	@Test( expectedExceptions = { BookNotFoundException.class } )
	public void shouldThrowBookNotFoundExceptionIfIsbn777GivenWhenRetrieving( ) throws Exception {
		instance.retrieveBook( "ISBN-777" );
	}

	@Test
	public void shouldEnsureThatIsbnIsWellFormattedWhenAskingForSummary( ) throws Exception {
		try {
			instance.getBookSummary( "INVALID-TEXT" );
		} catch ( IllegalArgumentException e ) {
			assertEquals( e.getMessage( ), "The book isbn must begin with 'ISBN-'" );
			return;
		}
		fail( "IllegalArgumentException has not been thrown." );
	}

	@Test( expectedExceptions = { BookNotFoundException.class } )
	public void shouldThrowBookNotFoundExceptionIfBookDoesNotExistWhenAskingForSummary( ) throws Exception {
		instance.getBookSummary( "ISBN-777" );
	}

	@Test
	public void shouldReturnSummary_isbn001( ) throws Exception {
		String summary = instance.getBookSummary( "ISBN-001" );
		assertEquals( summary, "[ISBN-001] Harry Potter and the Deathly Hallows - Sorcery and Magic." );
	}

	@Test
	public void shouldReturnSummary_isbn002( ) throws Exception {
		String summary = instance.getBookSummary( "ISBN-002" );
		assertEquals( summary,
				"[ISBN-002] The Player of Games - Jernau Morat Gurgeh. The Player of Games. Master of every..." );
	}

	@Test
	public void shouldReturnSummary_isbn003( ) throws Exception {
		String summary = instance.getBookSummary( "ISBN-003" );
		assertEquals( summary,
				"[ISBN-003] Genius: Richard Feynman and Modern Physics - A brilliant interweaving of Richard Feynman's colourful life and a..." );
	}

}