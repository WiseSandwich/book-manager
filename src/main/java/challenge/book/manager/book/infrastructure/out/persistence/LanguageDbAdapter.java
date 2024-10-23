package challenge.book.manager.book.infrastructure.out.persistence;

import challenge.book.manager.book.application.port.out.LanguageDBPort;
import challenge.book.manager.book.domain.Language;
import challenge.book.manager.book.infrastructure.out.persistence.mapper.LanguageDBMapper;
import challenge.book.manager.book.infrastructure.out.persistence.repository.book.LanguageRepository;
import challenge.book.manager.common.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class LanguageDbAdapter implements LanguageDBPort {

    private final LanguageRepository languageRepository;
    private final LanguageDBMapper mapper;

    @Override
    public List<Language> findAllByLanguageName(Set<String> languageName) {
        var languageEntities = languageRepository.findAllByLanguageNameIn(languageName)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found"));
        return languageEntities.stream().map(mapper::toDomain).toList() ;
    }
}
