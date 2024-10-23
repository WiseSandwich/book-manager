package challenge.book.manager.book.infrastructure.in.web.mapper;

import challenge.book.manager.book.domain.Book;
import challenge.book.manager.book.domain.Language;
import challenge.book.manager.book.infrastructure.in.web.payloads.BookRequest;
import challenge.book.manager.book.infrastructure.in.web.payloads.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookResponseMapper {

    @Mapping(source = "languages", target = "languages", qualifiedByName = "fromSetToList")
    BookResponse toResponse(Book book);

    @Mapping(source = "languages", target = "languages", qualifiedByName = "fromListToSet")
    @Mapping(source = "title", target = "title", qualifiedByName = "toLowerCase")
    Book toDomain(BookRequest bookRequest);

    @Named(value = "fromSetToList")
    default List<String> fromSetToList(Set<Language> languages){
        return languages.stream().map(Language::getLanguageName).toList();
    }

    @Named(value = "fromListToSet")
    default Set<Language> fromListToSet(List<String> languages){
        return languages.stream().map(language -> Language.builder().languageName(language).build()).collect(Collectors.toSet());
    }

    @Named(value = "toLowerCase")
    default String toLowerCase(String title) {
        return title.toLowerCase();
    }
}
