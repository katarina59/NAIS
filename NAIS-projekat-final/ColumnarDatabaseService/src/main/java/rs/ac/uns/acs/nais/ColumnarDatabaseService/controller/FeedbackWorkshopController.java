package rs.ac.uns.acs.nais.ColumnarDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackCountByMaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackWorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserWorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.FeedbackWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.FeedbackWorkshopService;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.service.UserWorkshopService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackWorkshopController {

    @Autowired
    private FeedbackWorkshopService feedbackWorkshopService;

    @PostMapping
    public ResponseEntity<FeedbackWorkshopDTO> create(@RequestBody FeedbackWorkshopDTO dto) {

        FeedbackWorkshopDTO created = feedbackWorkshopService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackWorkshopDTO>> getAll(){
        return new ResponseEntity<>(feedbackWorkshopService.getAll(), HttpStatus.OK);
    }


    @DeleteMapping("/{workshop_id}/{user_id}")
    public ResponseEntity<FeedbackWorkshopDTO> delete(@PathVariable Long workshop_id, @PathVariable Long user_id){
        return new ResponseEntity<>(feedbackWorkshopService.delete(workshop_id, user_id), HttpStatus.OK);
    }

    @PutMapping("/update/{workshop_id}/{user_id}")
    public ResponseEntity<FeedbackWorkshopDTO> update(@RequestBody FeedbackWorkshopDTO feedbackWorkshopDTO, @PathVariable Long workshop_id, @PathVariable Long user_id){
        return  new ResponseEntity<>(feedbackWorkshopService.update(feedbackWorkshopDTO, workshop_id, user_id), HttpStatus.OK);
    }

    @GetMapping("/statisticsAverage")
    public ResponseEntity<List<FeedbackAverageDTO>> calculateStatisticsForWorkshop(){
        return new ResponseEntity<>(feedbackWorkshopService.calculateStatisticsForWorkshop(), HttpStatus.OK);
    }

    @GetMapping("/feedbacksByMale")
    public ResponseEntity<FeedbackCountByMaleDTO> countFeedbacksByMale(){
        return new ResponseEntity<>(feedbackWorkshopService.countFeedbacksByMale(), HttpStatus.OK);
    }

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {
        List<FeedbackAverageDTO> statistics = feedbackWorkshopService.calculateStatisticsForWorkshop();
        try {
            byte[] pdfContents = feedbackWorkshopService.exportFeedbackStatistics(statistics);
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
