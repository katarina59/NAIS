package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackAverageDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackCountByMaleDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.FeedbackWorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopCountProjection;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.UserDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.FeedbackWorkshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.User;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.FeedbackWorkshopRepository;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public byte[] exportFeedbackStatistics(List<FeedbackAverageDTO> statistics) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);
        Paragraph title = new Paragraph("FEEDBACK STATISTICS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add spacing
        document.add(new Paragraph("\n"));

        PdfPTable reportTable = new PdfPTable(6);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Workshop ID", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Final Grade", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Content Grade", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("Organization Grade", headerFont));
        PdfPCell headerCell5 = new PdfPCell(new Paragraph("Price Grade", headerFont));
        PdfPCell headerCell6 = new PdfPCell(new Paragraph("Psychologist Grade", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell4.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell5.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell6.setBackgroundColor(new Color(110, 231, 234, 255));

        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);
        reportTable.addCell(headerCell4);
        reportTable.addCell(headerCell5);
        reportTable.addCell(headerCell6);

        for (FeedbackAverageDTO feedback : statistics) {
            reportTable.addCell(feedback.workshopId().toString());
            reportTable.addCell(feedback.finalGrade().toString());
            reportTable.addCell(feedback.contentGrade().toString());
            reportTable.addCell(feedback.organizationGrade().toString());
            reportTable.addCell(feedback.priceGrade().toString());
            reportTable.addCell(feedback.psychologistGrade().toString());
        }

        document.add(reportTable);
        document.close();

        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        }

        return byteArrayOutputStream.toByteArray();
    }


    public FeedbackCountByMaleDTO countFeedbacksByMale(){
        List<Long> user_ids = userService.getMaleUsers();
        return feedbackWorkshopRepository.countFeedbacksByMale(user_ids);
    }

    List<WorkshopCountProjection> countRecommendedByWorkshop(){
        return feedbackWorkshopRepository.countRecommendedByWorkshop();
   }

    List<WorkshopCountProjection>countNotRecommendedByWorkshopId(){
        return feedbackWorkshopRepository.countNotRecommendedByWorkshop();
    }

    List<WorkshopCountProjection> countTotalAttendeesByWorkshopId(){
        return feedbackWorkshopRepository.countTotalAttendeesByWorkshop();
   }
    public Long getUserByFinalGrade(){
        Double max_finalGrade = feedbackWorkshopRepository.getMaxFinalGrade();
        return feedbackWorkshopRepository.getUserIdByFinalGrade(max_finalGrade);
    }


}
