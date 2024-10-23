package challenge.book.manager.book.infrastructure.out.persistence.mapper;

import challenge.book.manager.book.domain.Book;
import challenge.book.manager.book.infrastructure.out.persistence.model.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {LanguageDBMapper.class}
)
public interface BookDBMapper {

    @Mapping(source = "languages", target = "languages")
    Book toDomain(BookEntity bookEntity);

    @Mapping(source = "languages", target = "languages")
    BookEntity toEntity(Book book);

}
