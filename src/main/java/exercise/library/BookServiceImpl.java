package exercise.library;

import java.util.Optional;
import java.util.regex.Pattern;


public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final Pattern isbnPattern;

	public BookServiceImpl( BookRepository bookRepository ) {
		if ( bookRepository == null ) {
			throw new IllegalArgumentException( "bookRepository must not be null" );
		}
		this.bookRepository = bookRepository;
		isbnPattern = Pattern.compile( "ISBN-\\d+" );
	}

	@Override
	public Book retrieveBook( final String isbn ) throws BookNotFoundException {
		checkIsbnFormat( isbn );
		return Optional.ofNullable( bookRepository.retrieveBook( isbn ) ).orElseThrow( BookNotFoundException::new );
	}

	@Override
	public String getBookSummary( final String isbn ) throws BookNotFoundException {
		return null;
	}

	private void checkIsbnFormat( String isbn ) {
		if ( !isbnPattern.matcher( isbn ).matches( ) ) {
			throw new IllegalArgumentException( "The book isbn must begin with 'ISBN-'" );
		}
	}
}
