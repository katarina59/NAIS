package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;
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

    @GetMapping("/getByHall")
    public ResponseEntity<List<WorkshopDateDTO>> getByHall(){
        return  new ResponseEntity<>(workshopService.getByHall(), HttpStatus.OK);
    }

    @GetMapping("/hallAndCount")
    public ResponseEntity<MaxCapacityHallWorkshopCountDTO> getWorkshopCountForMaxCapacityHall(
            @RequestParam(name = "minBookingFee") Double minBookingFee,
            @RequestParam(name = "maxBookingFee") Double maxBookingFee) {
        System.out.println("minBookingFee: " + minBookingFee);
        System.out.println("maxBookingFee: " + maxBookingFee);

        MaxCapacityHallWorkshopCountDTO result = workshopService.getWorkshopCountForMaxCapacityHall(minBookingFee, maxBookingFee);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/workshopWithGenderPercentage")
    public ResponseEntity<WorkshopWithGenderPercentageDTO> getWorkshopWithGenderPercentage(
            @RequestParam Long workshopId) {
        WorkshopWithGenderPercentageDTO result = workshopService.getWorkshopWithGenderPercentage(workshopId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/recommendations")
    public List<WorkshopRecommendation> getWorkshopsRecommendation() {
        return workshopService.getWorkshopsRecommendation();
    }

}
