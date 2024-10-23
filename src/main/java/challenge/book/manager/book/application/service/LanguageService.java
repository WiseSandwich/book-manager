package challenge.book.manager.book.application.service;


import challenge.book.manager.book.application.port.in.LanguageUseCase;
import challenge.book.manager.book.application.port.out.LanguageDBPort;
import challenge.book.manager.book.domain.Language;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class LanguageService implements LanguageUseCase {

    private final LanguageDBPort languageDBPort;

    @Override
    public final List<Language> findLanguages(Set<String> names) {

        return languageDBPort.findAllByLanguageName(names);
    }
}
