package challenge.book.manager.book.infrastructure.out.web;

import challenge.book.manager.book.application.port.out.OpenLibraryPort;
import challenge.book.manager.book.domain.Book;
import challenge.book.manager.book.infrastructure.out.web.feign.client.ApiOpenLibraryClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OpenLibraryAdapter implements OpenLibraryPort {

    private ApiOpenLibraryClient apiOpenLibraryClient;

    @Override
    public List<Book> searchBookByTitle(String title) {
        return apiOpenLibraryClient.findBook(title);
    }
}
