package challenge.book.manager.book.infrastructure.in.web.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BookRequest {

    @NotNull(message = "title field is required")
    String title;

    @NotNull(message = "authors field is required")
    String authors;

    @NotNull(message = "publishYear field is required")
    @PastOrPresent
    LocalDateTime publishYear;

    @NotEmpty(message = "languages field is required")
    List<String> languages;
}
