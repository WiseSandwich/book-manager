package challenge.book.manager.book.infrastructure.in.web.payloads;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record BookResponse(UUID id, String title, String authors, LocalDateTime publishYear, List<String> languages) {
}
