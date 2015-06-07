package exercise.library;

import java.util.regex.Pattern;


class IsbnValidator {

	private final Pattern isbnPattern;

	IsbnValidator( String regex ) {
		this.isbnPattern = Pattern.compile( regex );
	}

	boolean isValid( String isbn ) {
		return isbnPattern.matcher( isbn ).matches( );
	}
}
