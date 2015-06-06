package exercise.library;

public interface BookService
{
    public Book retrieveBook(String isbn) throws BookNotFoundException;
    public String getBookSummary(String isbn) throws BookNotFoundException;
}
