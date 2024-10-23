package challenge.book.manager.book.application.port.out;

import challenge.book.manager.book.domain.Language;

import java.util.List;
import java.util.Set;

public interface LanguageDBPort {

    List<Language> findAllByLanguageName(Set<String> languageName);

}
