package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserWorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopsUserCountDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.UserWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.HallService;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.UserWorkshopService;

import java.util.List;

@RestController
@RequestMapping("/user_workshop")
public class UserWorkshopController {

    @Autowired
    private UserWorkshopService userWorkshopService;

    @PostMapping
    public ResponseEntity<UserWorkshopDTO> create(@RequestBody UserWorkshopDTO dto) {

        UserWorkshopDTO created = userWorkshopService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserWorkshopDTO>> getAll(){
        return new ResponseEntity<>(userWorkshopService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{user_id}/{workshop_id}")
    public ResponseEntity<UserWorkshopDTO> delete(@PathVariable Long user_id, @PathVariable Long workshop_id){
        return new ResponseEntity<>(userWorkshopService.delete(user_id, workshop_id), HttpStatus.OK);
    }

    @PutMapping("/update/{user_id}/{workshop_id}")
    public ResponseEntity<UserWorkshopDTO> update(@RequestBody UserWorkshopDTO userWorkshopDTO, @PathVariable Long user_id, @PathVariable Long workshop_id){
        return  new ResponseEntity<>(userWorkshopService.update(userWorkshopDTO, user_id, workshop_id), HttpStatus.OK);
    }

    @GetMapping("/attendeesByWorkshop")
    public ResponseEntity<List<WorkshopsUserCountDTO>> getAttendeesByWorkshop(){
        return  new ResponseEntity<>(userWorkshopService.getAttendeesByWorkshop(), HttpStatus.OK);
    }
}
