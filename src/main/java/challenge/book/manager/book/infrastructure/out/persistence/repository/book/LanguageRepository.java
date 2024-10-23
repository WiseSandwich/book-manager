package challenge.book.manager.book.infrastructure.out.persistence.repository.book;

import challenge.book.manager.book.infrastructure.out.persistence.model.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Long> {

    Optional<List<LanguageEntity>> findAllByLanguageNameIn(Set<String> languageName);
}
