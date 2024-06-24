package rs.ac.uns.acs.nais.ColumnarDatabaseService.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackMapper mapper = Mappers.getMapper(FeedbackMapper.class);


}
