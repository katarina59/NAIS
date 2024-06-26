package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.TopInternshipDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.FeedbackService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.InternshipService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.StudentService;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/internships.json")
public class InternshipController {

    @Autowired
    private final InternshipService internshipService;

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final FeedbackService feedbackService;


    public InternshipController(InternshipService internshipService, StudentService studentService, FeedbackService feedbackService) {
        this.internshipService = internshipService;
        this.studentService = studentService;
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<Internship>> getAllInternship() {
        List<Internship> internships = internshipService.findAllInternships();
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("recommend-by-faculty/{studentId}")
    public ResponseEntity<Internship> recommendInternshipsByFaculty(@PathVariable Long studentId) {
        Internship internship = internshipService.recommendInternshipsByFaculty(studentId);
        return new ResponseEntity<>(internship, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Internship> addNewInternship(@RequestBody Internship internship){
        Internship createdInternship = internshipService.addNewInternship(internship);
        return new ResponseEntity<>(createdInternship, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Internship> deleteInternship(@PathVariable("id") Long id){
        if(internshipService.deleteInternship(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping
    public ResponseEntity<Internship> updateInternship(@RequestBody Internship internship){
        if(internshipService.updateInternship(internship)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/find-top-by-category")
    public ResponseEntity<List<TopInternshipDTO>> findTopInternshipsByCategory(){
        List<TopInternshipDTO> list= internshipService.findTopInternshipsByCategory();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/child-adol-category")
    public ResponseEntity<List<Internship>> findInternshipsByChildCategory() {
        List<Internship> internships = internshipService.findInternshipsByChildCategory();
        return new ResponseEntity<>(internships, HttpStatus.OK);
    }

    @GetMapping("/recommendByRating")
    public ResponseEntity<List<Internship>> recommendInternshipsByRating() {
        List<Internship> recommendedInternships = internshipService.recommendInternshipsByRating();
        if (!recommendedInternships.isEmpty()) {
            return new ResponseEntity<>(recommendedInternships, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internship> findById(@PathVariable long id) {
        Internship internship = internshipService.findById(id);
        if (internship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(internship, HttpStatus.OK);
    }

    @GetMapping(value = "/export-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportPdf() {
        // Prosti upiti
        List<Internship> internships1 = internshipService.findInternshipsByChildCategory();
        List<Feedback> feedbacks = feedbackService.findAboveThree();
        // Složeni upiti
        List<Student> students = studentService.recommendStudentsWithBestAvrageGrade(44L);
        List<TopInternshipDTO> internships = internshipService.findTopInternshipsByCategory();

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);
            document.setMargins(20, 20, 20, 20);

            // Internships1 section
            document.add(new Paragraph("Internships in CHILD_AND_ADOLESCENT_PSYCHOLOG Category")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20));
            addInternshipsTable(document, internships1);

            // Feedbacks section
            document.add(new Paragraph("Feedbacks with Rating Above 3")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20)
                    .setMarginBottom(20));
            addFeedbacksTable(document, feedbacks);

            // Internships section
            document.add(new Paragraph("Top Internships by Category")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20)
                    .setMarginBottom(20));
            addTopInternshipsTable(document, internships);

            // Students section
            document.add(new Paragraph("Students for Clinical Psychology Intern with Above Average Grades")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20)
                    .setMarginBottom(20));
            addStudentsTable(document, students);

            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void addInternshipsTable(Document document, List<Internship> internships) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3})).useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Title").setBold()));
        for (Internship internship : internships) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(internship.getId()))));
            table.addCell(new Cell().add(new Paragraph(internship.getTitle())));
        }
        document.add(table);
    }

    private void addFeedbacksTable(Document document, List<Feedback> feedbacks) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 4, 1})).useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Comment").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Rating").setBold()));
        for (Feedback feedback : feedbacks) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(feedback.getId()))));
            table.addCell(new Cell().add(new Paragraph(feedback.getComment())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(feedback.getRating()))));
        }
        document.add(table);
    }

    private void addTopInternshipsTable(Document document, List<TopInternshipDTO> internships) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{2, 2})).useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("Category").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Title").setBold()));
        for (TopInternshipDTO internship : internships) {
            table.addCell(new Cell().add(new Paragraph(internship.getCategoryName().toString())));
            table.addCell(new Cell().add(new Paragraph(internship.getTopInternshipTitle())));
        }
        document.add(table);
    }

    private void addStudentsTable(Document document, List<Student> students) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 2, 2, 2, 1})).useAllAvailableWidth();
        table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Name").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Last Name").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Username").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Average Grade").setBold()));
        for (Student student : students) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(student.getId()))));
            table.addCell(new Cell().add(new Paragraph(student.getName())));
            table.addCell(new Cell().add(new Paragraph(student.getLastName())));
            table.addCell(new Cell().add(new Paragraph(student.getUsername())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(student.getAverageGrade()))));
        }
        document.add(table);
    }
}