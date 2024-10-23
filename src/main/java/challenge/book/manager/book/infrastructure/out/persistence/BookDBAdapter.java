package challenge.book.manager.book.infrastructure.out.persistence;

import challenge.book.manager.book.application.port.out.BookDBPort;
import challenge.book.manager.book.application.port.out.OpenLibraryPort;
import challenge.book.manager.book.domain.Book;
import challenge.book.manager.book.infrastructure.out.persistence.mapper.BookDBMapper;
import challenge.book.manager.book.infrastructure.out.persistence.repository.book.BookRepository;
import challenge.book.manager.book.infrastructure.out.persistence.repository.specification.BookSpecification;
import challenge.book.manager.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class BookDBAdapter implements BookDBPort {
    private final BookRepository bookRepository;
    private final BookDBMapper bookDBMapper;

    @Override
    public Book findByBookId(UUID bookUUID) {
        log.info("Finding book {}", bookUUID.toString());
        var bookEntity = bookRepository.findById(bookUUID)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        log.info("Book {} was found ", bookUUID.toString());
        return bookDBMapper.toDomain(bookEntity);
    }

    @Override
    public List<Book> findAllBooks() {
        log.info("Finding book all books");
        var bookEntityList = bookRepository.findAll();
        return bookEntityList.stream().map(bookDBMapper::toDomain).toList();
    }

    @Override
    public Book saveBook(Book book) {
        var bookEntity = bookRepository.save(bookDBMapper.toEntity(book));
        return bookDBMapper.toDomain(bookEntity);
    }

    @Override
    public List<Book> findAllBooksByTitle(String title) {
        var specification = BookSpecification.findAllByTitle(title);
        return bookRepository.findAll(specification).stream()
                .map(bookDBMapper::toDomain)
                .toList();
    }

}
