package challenge.book.manager.book.application.port.in;

import challenge.book.manager.book.domain.Book;

import java.util.List;

public interface BookUseCase {

    Book findBook(String bookId);

    List<Book> findAllBook();

    Book saveBook(Book domain);

    List<Book> findAllBooksByTitle(String title);
}
