package challenge.book.manager.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ResponseError {
    private LocalDateTime timestamp;
    private int status;
    private String message;

    private List<String> errors;
}
