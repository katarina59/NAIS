package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserSessionStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.HallService;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO dto) {

        UserDTO created = userService.createUser(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Long id){
        return  new ResponseEntity<>(userService.update(userDTO, id), HttpStatus.OK);
    }

    @GetMapping("/maleUsers")
    public ResponseEntity<List<Long>> getMaleUsers(){
        return  new ResponseEntity<>(userService.getMaleUsers(), HttpStatus.OK);
    }

    @GetMapping("/user-session-statistics")
    public List<UserSessionStatisticsDTO> getUserSessionStatistics() {
        return userService.getUserSessionStatistics();
    }
}
