package challenge.book.manager.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Language {

    private Long id;

    private String languageName;

    private Set<Book> books = new HashSet<>();
}
