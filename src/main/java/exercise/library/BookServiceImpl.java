package exercise.library;

import java.util.Optional;


public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final IsbnValidator isbnValidator;
	private final BookSummaryBuilder bookSummaryBuilder;

	public BookServiceImpl( BookRepository bookRepository ) {
		this.bookRepository = bookRepository;
		this.isbnValidator = new IsbnValidator( "ISBN-\\d+$" );
		this.bookSummaryBuilder = new BookSummaryBuilder( "[%s] %s - %s", 10 );
	}

	@Override
	public Book retrieveBook( final String isbn ) throws BookNotFoundException {
		return getBook( isbn );
	}

	@Override
	public String getBookSummary( final String isbn ) throws BookNotFoundException {
		return bookSummaryBuilder.build( getBook( isbn ) );
	}

	private void checkIsbnFormat( String isbn ) {
		if ( !isbnValidator.isValid( isbn ) ) {
			throw new IllegalArgumentException( "The book isbn must begin with 'ISBN-'" );
		}
	}

	private Book getBook( String isbn ) throws BookNotFoundException {
		checkIsbnFormat( isbn );
		return Optional.ofNullable( bookRepository.retrieveBook( isbn ) ).orElseThrow( BookNotFoundException::new );
	}
}
