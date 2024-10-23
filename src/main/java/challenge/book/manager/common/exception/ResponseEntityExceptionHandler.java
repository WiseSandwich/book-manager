package challenge.book.manager.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseError> badRequestResponseExceptions(Exception ex) {
        List<String> errors = new ArrayList<>();

        if(ex instanceof MethodArgumentNotValidException validException){
            validException.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        }else if(ex instanceof BadRequestException badRequestException){
            errors.add(badRequestException.getMessage());
        }else {
            errors.add("An unexpected error ocurred");
        }

        errors.forEach(error -> log.info("Bad Request Exception: {}", error));

        return ResponseEntity.status(BAD_REQUEST).body(new ResponseError(LocalDateTime.now(), BAD_REQUEST.value(), BAD_REQUEST.getReasonPhrase(), errors));
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ResponseError> resourceNotFoundResponseExceptions(Exception ex) {
        log.warn("Resource Not Found Exception: {}",ex.getMessage());

        return ResponseEntity.status(NOT_FOUND).body(new ResponseError(LocalDateTime.now(), NOT_FOUND.value(), NOT_FOUND.getReasonPhrase(), List.of(ex.getMessage())));
    }
}
