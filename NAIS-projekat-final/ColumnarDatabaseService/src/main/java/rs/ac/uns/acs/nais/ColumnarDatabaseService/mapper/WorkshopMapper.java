//package rs.ac.uns.acs.nais.ColumnarDatabaseService.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.Mappings;
//import org.mapstruct.factory.Mappers;
//import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
//import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;
//
//@Mapper(componentModel = "spring")
//public interface WorkshopMapper {
//
//    WorkshopMapper mapper = Mappers.getMapper(WorkshopMapper.class);
//
//    @Mappings({
//            @Mapping(target = "workshop_id", source = "workshop.workshop_id"),
//            @Mapping(target = "category", source = "workshop.category"),
//            @Mapping(target = "date", source = "workshop.date"),
//            @Mapping(target = "is_online", source = "workshop.is_online"),
//            @Mapping(target = "price", source = "workshop.price"),
//            @Mapping(target = "psychologist_id", source = "workshop.psychologist_id"),
//            @Mapping(target = "description", source = "workshop.description"),
//            @Mapping(target = "startTime", source = "workshop.startTime"),
//            @Mapping(target = "endTime", source = "workshop.endTime"),
//            @Mapping(target = "name", source = "workshop.name"),
//            @Mapping(target = "max_attendees", source = "workshop.max_attendees")
//    })
//    WorkshopDTO workshopToWorkshopDTO(Workshop workshop);
//
//    @Mappings({
//            @Mapping(target = "workshop_id", source = "dto.workshop_id"),
//            @Mapping(target = "category", source = "dto.category"),
//            @Mapping(target = "date", source = "dto.date"),
//            @Mapping(target = "is_online", source = "dto.is_online"),
//            @Mapping(target = "price", source = "dto.price"),
//            @Mapping(target = "psychologist_id", source = "dto.psychologist_id"),
//            @Mapping(target = "description", source = "dto.description"),
//            @Mapping(target = "startTime", source = "dto.startTime"),
//            @Mapping(target = "endTime", source = "dto.endTime"),
//            @Mapping(target = "name", source = "dto.name"),
//            @Mapping(target = "max_attendees", source = "dto.max_attendees")
//    })
//    Workshop workshopDTOtoWorkshop(WorkshopDTO dto);
//}
