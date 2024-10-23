package challenge.book.manager.book.infrastructure.out.web.feign.client;

import challenge.book.manager.book.domain.Book;
import jakarta.websocket.server.PathParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "ApiOpenLibraryClient", url= "${api.url.openlibrary}")
public interface ApiOpenLibraryClient {

    //here is going to be the call of openlibrary api, but is still don't work
    //todo implement call of openlibrary api for search

    @PostMapping("/search.json")
    List<Book> findBook(@PathParam("title") String title);
}
