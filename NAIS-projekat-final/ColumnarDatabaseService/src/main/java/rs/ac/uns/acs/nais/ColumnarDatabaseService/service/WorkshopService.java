package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDateDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.WorkshopRepository;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private HallService hallService;

    @Autowired
    private ModelMapper mapper;

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

    public List<Long> getWorkshopBycategory(){
        return workshopRepository.getWorkshopsByCategory();
    }
}
