package exercise.library;

class BookSummaryBuilder {

	private final TextShortener textShortener;
	private final String summaryFormat;

	public BookSummaryBuilder( String summaryFormat, int maxDescriptionWords ) {
		assert summaryFormat != null;
		assert maxDescriptionWords > 0;
		this.summaryFormat = summaryFormat;
		this.textShortener = new TextShortener( maxDescriptionWords );
	}

	public String build( Book book ) {
		assert book != null;
		return String.format( summaryFormat,
				book.getIsbn( ),
				book.getTitle( ),
				textShortener.shorten( book.getDescription( ) )
		);
	}
}
