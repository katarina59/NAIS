package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.OrderDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDateDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.WorkshopService;

import java.io.IOException;
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

    @GetMapping(value="/WorkshopPDFSimple", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePDF(){
        List<WorkshopDTO> dtos = workshopService.getWorkshopsForReport();
        try {
            byte[] pdfContents = workshopService.exportSimpleWorkshopReport(dtos);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "products.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfContents);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


}
