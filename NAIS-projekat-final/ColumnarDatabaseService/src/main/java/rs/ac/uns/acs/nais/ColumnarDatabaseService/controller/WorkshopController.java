package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.OrderDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.WorkshopService;

import java.util.List;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping
    public ResponseEntity<WorkshopDTO> createWorkshop(@RequestBody WorkshopDTO dto) {

        WorkshopDTO createdWorkshop = workshopService.createWorkshop(dto);
        return new ResponseEntity<>(createdWorkshop, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WorkshopDTO>> getAll(){
        return new ResponseEntity<>(workshopService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<WorkshopDTO> deleteWorkshop(@PathVariable Long id){
        return new ResponseEntity<>(workshopService.deleteWorkshop(id), HttpStatus.OK);
    }

}
