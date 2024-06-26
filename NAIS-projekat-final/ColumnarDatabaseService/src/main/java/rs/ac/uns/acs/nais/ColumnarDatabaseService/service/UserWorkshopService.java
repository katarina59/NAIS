package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserWorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopsUserCountDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Hall;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.UserWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.HallRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.UserWorkshopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserWorkshopService {

    @Autowired
    private UserWorkshopRepository userWorkshopRepository;

    @Autowired
    private WorkshopService workshopService;

    @Autowired
    private ModelMapper mapper;
    private UserWorkshop mapToEntity(UserWorkshopDTO userWorkshopDTO){
        UserWorkshop userWorkshop = mapper.map(userWorkshopDTO, UserWorkshop.class);
        return userWorkshop;
    }

    private UserWorkshopDTO mapToDTO(UserWorkshop userWorkshop){
        UserWorkshopDTO userWorkshopDTO= mapper.map(userWorkshop, UserWorkshopDTO.class);
        return userWorkshopDTO;
    }

    public UserWorkshopDTO create(UserWorkshopDTO userWorkshopDTO){
        UserWorkshop userWorkshop = mapToEntity(userWorkshopDTO);

        return mapToDTO(userWorkshopRepository.save(userWorkshop));
    }

    public UserWorkshopDTO delete(Long user_id, Long workshop_id){
        UserWorkshop userWorkshop= userWorkshopRepository.getById(user_id, workshop_id);
        userWorkshopRepository.delete(userWorkshop);
        return mapToDTO(userWorkshop);
    }

    public List<UserWorkshopDTO> getAll(){
        List<UserWorkshop> userWorkshops = userWorkshopRepository.findAll();
        return userWorkshops.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserWorkshopDTO update(UserWorkshopDTO userWorkshopDTO, Long user_id, Long workshop_id){
        UserWorkshop userWorkshop = userWorkshopRepository.getById(user_id, workshop_id);
        userWorkshop.setCanceled(userWorkshopDTO.isCanceled());
        userWorkshop.setSessionDuration(userWorkshopDTO.getSessionDuration());

        return mapToDTO(userWorkshopRepository.save(userWorkshop));

    }

    List<Long> findUserIdsByWorkshopId(Long workshopId){
        return userWorkshopRepository.findUserIdsByWorkshopId(workshopId);
    }

    public List<UserSessionStatisticsDTO> findUserSessionStatistics() {
        return userWorkshopRepository.findUserSessionStatistics();
    }

    }

    public List<WorkshopsUserCountDTO> getAttendeesByWorkshop(){
        List<Long> workshop_ids = workshopService.getWorkshopBycategory();
        return userWorkshopRepository.getAttendeesByWorkshop(workshop_ids);
    }
}
