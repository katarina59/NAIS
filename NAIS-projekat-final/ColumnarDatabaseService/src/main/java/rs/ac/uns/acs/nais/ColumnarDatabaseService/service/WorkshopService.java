package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.WorkshopRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private FeedbackWorkshopService feedbackWorkshopService;
    @Autowired
    private HallService hallService;

    @Autowired
    private UserWorkshopService userWorkshopService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    public WorkshopDTO createWorkshop(WorkshopDTO workshopDTO){
        Workshop workshop = mapToEntity(workshopDTO);

        return mapToDTO(workshopRepository.save(workshop));
    }

    public WorkshopDTO deleteWorkshop(Long id){
        Workshop workshop = workshopRepository.getById(id);
        workshopRepository.delete(workshop);
        return mapToDTO(workshop);
    }

    public List<WorkshopDTO> getAll(){
        List<Workshop> workshops = workshopRepository.findAll();
        return workshops.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private Workshop mapToEntity(WorkshopDTO workshopDTO){
        Workshop workshop = mapper.map(workshopDTO, Workshop.class);
        return workshop;
    }

    private WorkshopDTO mapToDTO(Workshop workshop){
        WorkshopDTO workshopDTO= mapper.map(workshop, WorkshopDTO.class);
        return workshopDTO;
    }

    //prvi slozeni
    public List<WorkshopDateDTO> getByHall(){
        Long hall_id = hallService.getCheapestHall();
        return workshopRepository.getByHall(hall_id);
    }


    public MaxCapacityHallWorkshopCountDTO getWorkshopCountForMaxCapacityHall(Double minBookingFee, Double maxBookingFee) {
        List<HallDTO> halls = hallService.findHallsWithBookingFeeRange(minBookingFee, maxBookingFee);
        if (halls.isEmpty()) {
            return null;
        }

        HallDTO maxCapacityHall = halls.stream()
                .max(Comparator.comparingInt(HallDTO::getCapacity))
                .orElse(null);
        if (maxCapacityHall == null) {
            return null;
        }

        long hallId = maxCapacityHall.getHallId();

        HallDTO hallDTO = hallService.getHall(hallId);

        long workshopCount = workshopRepository.countWorkshopsForHall(hallId);

        MaxCapacityHallWorkshopCountDTO hallWorkshopCountDTO = new MaxCapacityHallWorkshopCountDTO();
        hallWorkshopCountDTO.setHallId(hallDTO.getHallId());
        hallWorkshopCountDTO.setName(hallDTO.getName());
        hallWorkshopCountDTO.setCapacity(hallDTO.getCapacity());
        hallWorkshopCountDTO.setBookingFee(hallDTO.getBookingFee());
        hallWorkshopCountDTO.setLocation(hallDTO.getLocation());
        hallWorkshopCountDTO.setWorkshopsCount((int) workshopCount);

        return hallWorkshopCountDTO;
    }

    public WorkshopWithGenderPercentageDTO getWorkshopWithGenderPercentage(Long id) {
        Workshop workshop = workshopRepository.getById(id);
        List<Long> userIds = userWorkshopService.findUserIdsByWorkshopId(id);

        long maleCountInWorkshop = userService.countMaleUsersByIds(userIds);
        long femaleCountInWorkshop = userService.countFemaleUsersByIds(userIds);
        long totalCountInWorkshop = maleCountInWorkshop + femaleCountInWorkshop;

        double malePercentage = totalCountInWorkshop > 0 ? (double) maleCountInWorkshop / totalCountInWorkshop * 100 : 0;
        double femalePercentage = totalCountInWorkshop > 0 ? (double) femaleCountInWorkshop / totalCountInWorkshop * 100 : 0;

        WorkshopWithGenderPercentageDTO dto = new WorkshopWithGenderPercentageDTO();
        dto.setWorkshopId(workshop.getWorkshopId());
        dto.setCategory(workshop.getCategory());
        dto.setDescription(workshop.getDescription());
        dto.setName(workshop.getName());
        dto.setDate(workshop.getDate());
        dto.setStartTime(workshop.getStartTime());
        dto.setEndTime(workshop.getEndTime());
        dto.setIs_online(workshop.isIs_online());
        dto.setMax_attendees(workshop.getMax_attendees());
        dto.setPrice(workshop.getPrice());
        dto.setPsychologistId(workshop.getPsychologistId());
        dto.setMalePercentage(malePercentage);
        dto.setFemalePercentage(femalePercentage);

        return dto;
    }


    public List<WorkshopRecommendation> getWorkshopsRecommendation() {
        List<Workshop> workshops = (List<Workshop>) workshopRepository.findAll();

        List<WorkshopCountProjection> totalAttendeesList = feedbackWorkshopService.countTotalAttendeesByWorkshopId();
        List<WorkshopCountProjection> recommendedList = feedbackWorkshopService.countRecommendedByWorkshop();
        List<WorkshopCountProjection> notRecommendedList = feedbackWorkshopService.countNotRecommendedByWorkshopId();

        Map<Long, Long> totalAttendeesMap = totalAttendeesList.stream()
                .collect(Collectors.toMap(WorkshopCountProjection::getWorkshopId, WorkshopCountProjection::getCount));
        Map<Long, Long> recommendedMap = recommendedList.stream()
                .collect(Collectors.toMap(WorkshopCountProjection::getWorkshopId, WorkshopCountProjection::getCount));
        Map<Long, Long> notRecommendedMap = notRecommendedList.stream()
                .collect(Collectors.toMap(WorkshopCountProjection::getWorkshopId, WorkshopCountProjection::getCount));

        return workshops.stream().map(workshop -> {
            long workshopId = workshop.getWorkshopId();
            long totalAttendees = totalAttendeesMap.getOrDefault(workshopId, 0L);
            long recommendedCount = recommendedMap.getOrDefault(workshopId, 0L);
            long notRecommendedCount = notRecommendedMap.getOrDefault(workshopId, 0L);

            double recommendedPercentage = (totalAttendees > 0) ? (recommendedCount * 100.0 / totalAttendees) : 0.0;
            double notRecommendedPercentage = (totalAttendees > 0) ? (notRecommendedCount * 100.0 / totalAttendees) : 0.0;

            WorkshopRecommendation dto = new WorkshopRecommendation();
            dto.setWorkshopId((long) workshopId);
            dto.setMax_attendees(workshop.getMax_attendees());
            dto.setEndTime(workshop.getEndTime());
            dto.setStartTime(workshop.getStartTime());
            dto.setHallId(workshop.getHallId());
            dto.setName(workshop.getName());
            dto.setDescription(workshop.getDescription());
            dto.setDate(workshop.getDate());
            dto.setCategory(workshop.getCategory());
            dto.setPrice(workshop.getPrice());
            dto.setTotalAttendees((int) totalAttendees);
            dto.setRecommendedPercentage(recommendedPercentage);
            dto.setNotRecommendedPercentage(notRecommendedPercentage);

            return dto;
        }).collect(Collectors.toList());
    }
}
