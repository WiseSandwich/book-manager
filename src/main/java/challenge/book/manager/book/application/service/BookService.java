package challenge.book.manager.book.application.service;

import challenge.book.manager.book.application.port.in.BookUseCase;
import challenge.book.manager.book.application.port.in.LanguageUseCase;
import challenge.book.manager.book.application.port.out.BookDBPort;
import challenge.book.manager.book.application.port.out.OpenLibraryPort;
import challenge.book.manager.book.domain.Book;
import challenge.book.manager.book.domain.Language;
import challenge.book.manager.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService implements BookUseCase {

    private final BookDBPort bookDBPort;
    private final LanguageUseCase languageUseCase;
    private final OpenLibraryPort openLibraryPort;

    @Override
    public Book findBook(String bookId) {
        var uuidBook = UUID.fromString(bookId);
        return bookDBPort.findByBookId(uuidBook);
    }

    @Override
    public List<Book> findAllBook() {
        return bookDBPort.findAllBooks();
    }

    @Override
    public Book saveBook(Book book) {
        validateLanguages(book.getLanguages(), book);
        validateBook(book);
        return bookDBPort.saveBook(book);
    }

    @Override
    public List<Book> findAllBooksByTitle(String title) {
        List<Book> bookList = new ArrayList<>();
        // here i comment the implementation of using feign to call the openlibrary api to find the book and if this is not found, search in the db
        // a function will be better than this but the api is not working and i dont want to make the code dirty for a api that is shutting down right now
        //bookList = openLibraryPort.searchBookByTitle(title);
        if (bookList.isEmpty()) {
            bookList = bookDBPort.findAllBooksByTitle(title);
        }
        return bookList;
    }

    private void validateLanguages(Set<Language> languageSet, Book book) {
        var languages = languageUseCase.findLanguages(languageSet.stream().map(Language::getLanguageName).collect(Collectors.toSet()));
        if (ObjectUtils.isEmpty(languages)) {
            throw new ResourceNotFoundException("Languages not found ");
        }
        book.setLanguages(new HashSet<>(languages));
    }

    private void validateBook(Book book) {
        List<Book> bookList = bookDBPort.findAllBooksByTitle(book.getTitle().toLowerCase());
        if(!ObjectUtils.isEmpty(bookList)){
            bookList.stream()
                    .filter(bookDB -> Objects.equals(bookDB.getTitle(), book.getTitle().toLowerCase()))
                    .forEach(bookDb -> {
                        book.setId(bookDb.getId());
                        if (!bookDb.getLanguages().containsAll(book.getLanguages())) {
                            book.getLanguages().addAll(bookDb.getLanguages());
                        }
                    });
        }
    }
}
