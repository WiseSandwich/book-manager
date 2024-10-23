package challenge.book.manager.book.application.port.in;

import challenge.book.manager.book.domain.Language;

import java.util.List;
import java.util.Set;

public interface LanguageUseCase {

    List<Language> findLanguages(Set<String> names);
}
