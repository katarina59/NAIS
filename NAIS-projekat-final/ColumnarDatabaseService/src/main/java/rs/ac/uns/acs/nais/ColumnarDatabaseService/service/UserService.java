package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Hall;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.User;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.UserWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.HallRepository;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserWorkshopService userWorkshopService;

    @Autowired
    private ModelMapper mapper;
        private User mapToEntity(UserDTO userDTO){
        User user = mapper.map(userDTO, User.class);
        return user;
    }

    private UserDTO mapToDTO(User user){
       UserDTO userDTO= mapper.map(user, UserDTO.class);
        return userDTO;
    }

    public UserDTO createUser(UserDTO userDTO){
        User user = mapToEntity(userDTO);

        return mapToDTO(userRepository.save(user));
    }

    public UserDTO deleteUser(Long id){
        User user= userRepository.getById(id);
        userRepository.delete(user);
        return mapToDTO(user);
    }

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO update(UserDTO userDTO, Long id){

            //birthdate se ne sme menjati jer je deo pk

            User user = userRepository.getById(id);
            user.setName(userDTO.getName());
            user.setLastName(userDTO.getLastName());
            user.setGender(userDTO.getGender());
            user.setRole(userDTO.getRole());
            user.setUsername(userDTO.getUsername());


            return mapToDTO(userRepository.save(user));
    }


    public List<Long> getMaleUsers(){
         return userRepository.getMaleUser();
    }

    long countMaleUsersByIds(List<Long> userIds){
            return userRepository.countMaleUsersByIds(userIds);
    }

    long countFemaleUsersByIds(List<Long> userIds){
            return userRepository.countFemaleUsersByIds(userIds);
    }

    public List<UserSessionStatisticsDTO> getUserSessionStatistics() {
        List<UserSessionStatisticsDTO> sessionStatistics = userWorkshopService.findUserSessionStatistics();

        return sessionStatistics.stream().map(stat -> {
            User user = userRepository.getById(stat.getUserId());
            stat.setName(user.getName());
            stat.setGender(user.getGender());
            stat.setLastName(user.getLastName());
            return stat;
        }).collect(Collectors.toList());
    }
}
