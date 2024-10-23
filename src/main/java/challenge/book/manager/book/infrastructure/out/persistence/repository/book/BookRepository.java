package challenge.book.manager.book.infrastructure.out.persistence.repository.book;

import challenge.book.manager.book.infrastructure.out.persistence.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    Optional<BookEntity> findById(UUID bookUUID);
}
