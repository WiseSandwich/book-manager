package challenge.book.manager.book.infrastructure.in.web;

import challenge.book.manager.book.application.port.in.BookUseCase;
import challenge.book.manager.book.infrastructure.in.web.mapper.BookResponseMapper;
import challenge.book.manager.book.infrastructure.in.web.payloads.BookRequest;
import challenge.book.manager.book.infrastructure.in.web.payloads.BookResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

    private final BookUseCase bookUseCase;
    private final BookResponseMapper mapper;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String bookId) {
        var response = bookUseCase.findBook(bookId);
        return ResponseEntity.ok(mapper.toResponse(response));
    }

    @GetMapping("")
    public ResponseEntity<List<BookResponse>> getAllBook() {
        var responseList = bookUseCase.findAllBook();
        return ResponseEntity.ok(responseList.stream().map(mapper::toResponse).toList());
    }

    @PostMapping("")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookRequest bookRequest) {
        var response = bookUseCase.saveBook(mapper.toDomain(bookRequest));
        return ResponseEntity.ok(mapper.toResponse(response));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> getAllBookByTitle(@RequestParam("title") String bookTitle){
        var responseList = bookUseCase.findAllBooksByTitle(bookTitle.toLowerCase());
        return ResponseEntity.ok(responseList.stream().map(mapper::toResponse).toList());
    }
}
