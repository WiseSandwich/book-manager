package challenge.book.manager.book.application.port.out;

import challenge.book.manager.book.domain.Book;

import java.util.List;

public interface OpenLibraryPort {

    List<Book> searchBookByTitle(String title);
}
