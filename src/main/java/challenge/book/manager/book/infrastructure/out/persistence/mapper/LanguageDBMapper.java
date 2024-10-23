package challenge.book.manager.book.infrastructure.out.persistence.mapper;

import challenge.book.manager.book.domain.Language;
import challenge.book.manager.book.infrastructure.out.persistence.model.LanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LanguageDBMapper {

    @Mapping(target = "books", ignore = true)
    Language toDomain(LanguageEntity languageEntity);


}
