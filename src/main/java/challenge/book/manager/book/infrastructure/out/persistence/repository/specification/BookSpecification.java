package challenge.book.manager.book.infrastructure.out.persistence.repository.specification;

import challenge.book.manager.book.infrastructure.out.persistence.model.BookEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookSpecification {

    public static Specification<BookEntity> findAllByTitle(String title){
        return (book, cq,cb) -> cb.equal(book.get("title"), title);
    }

}
