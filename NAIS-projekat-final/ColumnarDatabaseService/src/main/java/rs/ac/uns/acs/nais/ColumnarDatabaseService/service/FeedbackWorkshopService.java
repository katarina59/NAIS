package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackCountByMaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackWorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.FeedbackWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.User;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.FeedbackWorkshopRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackWorkshopService {

    @Autowired
    private FeedbackWorkshopRepository feedbackWorkshopRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;
    private FeedbackWorkshop mapToEntity(FeedbackWorkshopDTO feedbackWorkshopDTO){
        FeedbackWorkshop feedbackWorkshop = mapper.map(feedbackWorkshopDTO, FeedbackWorkshop.class);
        return feedbackWorkshop;
    }

    private FeedbackWorkshopDTO mapToDTO(FeedbackWorkshop feedbackWorkshop){
        FeedbackWorkshopDTO feedbackWorkshopDTO= mapper.map(feedbackWorkshop, FeedbackWorkshopDTO.class);
        return feedbackWorkshopDTO;
    }



    public FeedbackWorkshopDTO create(FeedbackWorkshopDTO feedbackWorkshopDTO){
       FeedbackWorkshop feedbackWorkshop = mapToEntity(feedbackWorkshopDTO);

        return mapToDTO(feedbackWorkshopRepository.save(feedbackWorkshop));
    }

    public FeedbackWorkshopDTO delete(Long workshop_id, Long user_id){
        FeedbackWorkshop feedbackWorkshop= feedbackWorkshopRepository.getById(workshop_id, user_id);
        feedbackWorkshopRepository.delete(feedbackWorkshop);
        return mapToDTO(feedbackWorkshop);
    }

    public List<FeedbackWorkshopDTO> getAll(){
        List<FeedbackWorkshop> feedbackWorkshops = feedbackWorkshopRepository.findAll();
        return feedbackWorkshops.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public FeedbackWorkshopDTO update(FeedbackWorkshopDTO feedbackWorkshopDTO, Long workshop_id, Long user_id){
       FeedbackWorkshop feedbackWorkshop = feedbackWorkshopRepository.getById(workshop_id, user_id);
       feedbackWorkshop.setContentGrade(feedbackWorkshopDTO.getContentGrade());
       feedbackWorkshop.setFinalGrade(feedbackWorkshopDTO.getFinalGrade());
       feedbackWorkshop.setRecommended(feedbackWorkshopDTO.isRecommended());
       feedbackWorkshop.setPriceGrade(feedbackWorkshopDTO.getPriceGrade());
       feedbackWorkshop.setOrganizationGrade(feedbackWorkshopDTO.getOrganizationGrade());
       feedbackWorkshop.setPsychologistGrade(feedbackWorkshopDTO.getPsychologistGrade());

       return mapToDTO(feedbackWorkshopRepository.save(feedbackWorkshop));


    }


    public List<FeedbackAverageDTO> calculateStatisticsForWorkshop(){
        return feedbackWorkshopRepository.calculateStatisticsForWorkshop();
    }

    public FeedbackCountByMaleDTO countFeedbacksByMale(){
        List<Long> user_ids = userService.getMaleUsers();
        return feedbackWorkshopRepository.countFeedbacksByMale(user_ids);
    }

    public Long getUserByFinalGrade(){
        Double max_finalGrade = feedbackWorkshopRepository.getMaxFinalGrade();
        return feedbackWorkshopRepository.getUserIdByFinalGrade(max_finalGrade);
    }


}
