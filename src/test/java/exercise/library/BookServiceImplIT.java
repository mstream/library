package exercise.library;

import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class BookServiceImplIT {

    private final BookService instance = new BookServiceImpl( new BookRepositoryImpl() );

    @Test
    public void shouldRetrieveHarryPotterIfIsbn001Given() throws Exception {
        Book book = instance.retrieveBook("ISBN-001");
        assertNotNull( book );
        assertEquals( book.getIsbn( ), "ISBN-001" );
        assertEquals( book.getTitle( ), "Harry Potter and the Deathly Hallows" );
    }

    @Test
    public void shouldEnsureThatIsbnIsWellFormattedWhenRetrieving() throws Exception {
        try {
            instance.retrieveBook("INVALID-TEXT");
        } catch (IllegalArgumentException e) {
            assertEquals( e.getMessage( ), "The book isbn must begin with 'ISBN-'" );
            return;
        }
        fail( "IllegalArgumentException has not been thrown." );
    }

    @Test(expectedExceptions = {BookNotFoundException.class})
    public void shouldThrowBookNotFoundExceptionIfIsbn777GivenWhenRetrieving() throws Exception {
        instance.retrieveBook("ISBN-777");
    }

}