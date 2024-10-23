package challenge.book.manager.book.application.port.out;

import challenge.book.manager.book.domain.Book;

import java.util.List;
import java.util.UUID;

public interface BookDBPort {

    Book findByBookId(UUID bookUUID);

    List<Book> findAllBooks();

    Book saveBook(Book book);

    List<Book> findAllBooksByTitle(String title);
}
