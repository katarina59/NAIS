package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.HallService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/halls")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping
    public ResponseEntity<HallDTO> createHall(@RequestBody HallDTO dto) {

        HallDTO createdHall = hallService.createHall(dto);
        return new ResponseEntity<>(createdHall, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HallDTO>> getAll(){
        return new ResponseEntity<>(hallService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HallDTO> deleteHall(@PathVariable Long id){
        return new ResponseEntity<>(hallService.deleteHall(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HallDTO> update(@RequestBody HallDTO hallDTO, @PathVariable Long id){
        return  new ResponseEntity<>(hallService.update(hallDTO, id), HttpStatus.OK);
    }

    @GetMapping("/cheapest")
    public ResponseEntity<Long> getCheapestHall(){
        return new ResponseEntity<>(hallService.getCheapestHall(), HttpStatus.OK);
    }


    @GetMapping("/statistics/{location}")
    public HallStatisticsDTO getHallStatistics(@PathVariable String location) {
        String decodedLocation = URLDecoder.decode(location, StandardCharsets.UTF_8);
        return hallService.getHallStatisticsByLocation(decodedLocation);
    }
}

