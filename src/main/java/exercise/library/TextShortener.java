package exercise.library;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


class TextShortener {

	private final int wordsNumber;
	private final Pattern pattern;

	TextShortener( int wordsNumber ) {
		assert wordsNumber > 0;
		this.wordsNumber = wordsNumber;
		this.pattern = Pattern.compile( "[\\w'.]+" );
	}

	String shorten( String text ) {
		if ( text == null ) {
			return null;
		}
		if ( text.trim( ).length( ) == 0 ) {
			return "";
		}
		Matcher matcher = pattern.matcher( text );
		if ( matcher.matches( ) ) {
			return text;
		}
		int words = wordsNumber - 1;
		while ( words > 0 && matcher.find( ) ) {
			words--;
		}
		if ( words > 0 ) {
			return text;
		}
		int end = matcher.end( );
		String shortenedText = text.substring( 0, end ).trim( );
		if ( text.substring( end, text.length( ) ).trim( ).length( ) == 0 ) {
			return shortenedText;
		}
		return shortenedText + "...";
	}

}
