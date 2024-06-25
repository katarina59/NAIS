package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDateDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;

import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.WorkshopRepository;


import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;




@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private HallService hallService;

    @Autowired
    private ModelMapper mapper;

    public WorkshopDTO createWorkshop(WorkshopDTO workshopDTO){
        Workshop workshop = mapToEntity(workshopDTO);

        return mapToDTO(workshopRepository.save(workshop));
    }

    public WorkshopDTO deleteWorkshop(Long id){
        Workshop workshop = workshopRepository.getById(id);
        workshopRepository.delete(workshop);
        return mapToDTO(workshop);
    }

    public List<WorkshopDTO> getAll(){
        List<Workshop> workshops = workshopRepository.findAll();
        return workshops.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private Workshop mapToEntity(WorkshopDTO workshopDTO){
        Workshop workshop = mapper.map(workshopDTO, Workshop.class);
        return workshop;
    }

    private WorkshopDTO mapToDTO(Workshop workshop){
        WorkshopDTO workshopDTO= mapper.map(workshop, WorkshopDTO.class);
        return workshopDTO;
    }

    //prvi slozeni
    public List<WorkshopDateDTO> getByHall(){
        Long hall_id = hallService.getCheapestHall();
        return workshopRepository.getByHall(hall_id);
    }

    public List<Long> getWorkshopBycategory(){
        return workshopRepository.getWorkshopsByCategory();
    }

    public List<WorkshopDTO> getWorkshopsForReport(){
        return workshopRepository.getWorkshopsForReport();
    }

    public byte[] exportSimpleWorkshopReport(List<WorkshopDTO> statistics) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A3);

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);
        Paragraph title = new Paragraph("WORKSHOP SIMPLE STATISTICS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add spacing
        document.add(new Paragraph("\n"));

        PdfPTable reportTable = new PdfPTable(11);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Workshop ID", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Category", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Name", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("Date", headerFont));
        PdfPCell headerCell5 = new PdfPCell(new Paragraph("Online", headerFont));
        PdfPCell headerCell6 = new PdfPCell(new Paragraph("Price", headerFont));
        PdfPCell headerCell7 = new PdfPCell(new Paragraph("Psychologist", headerFont));
        PdfPCell headerCell8 = new PdfPCell(new Paragraph("Description", headerFont));
        PdfPCell headerCell9 = new PdfPCell(new Paragraph("Start time", headerFont));
        PdfPCell headerCell10 = new PdfPCell(new Paragraph("End time", headerFont));
        PdfPCell headerCell11 = new PdfPCell(new Paragraph("Hall ID", headerFont));

        headerCell1.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell2.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell3.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell4.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell5.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell6.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell7.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell8.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell9.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell10.setBackgroundColor(new Color(110, 231, 234, 255));
        headerCell11.setBackgroundColor(new Color(110, 231, 234, 255));


        reportTable.addCell(headerCell1);
        reportTable.addCell(headerCell2);
        reportTable.addCell(headerCell3);
        reportTable.addCell(headerCell4);
        reportTable.addCell(headerCell5);
        reportTable.addCell(headerCell6);
        reportTable.addCell(headerCell7);
        reportTable.addCell(headerCell8);
        reportTable.addCell(headerCell9);
        reportTable.addCell(headerCell10);
        reportTable.addCell(headerCell11);


        for (WorkshopDTO workshopDTO : statistics) {
            reportTable.addCell(workshopDTO.getWorkshopId().toString());
            reportTable.addCell(workshopDTO.getCategory().toString());
            reportTable.addCell(workshopDTO.getName().toString());
            reportTable.addCell(workshopDTO.getDate().toString());
            reportTable.addCell(workshopDTO.isIs_online().toString());
            reportTable.addCell(workshopDTO.getPrice().toString());
            reportTable.addCell(workshopDTO.getPsychologistId().toString());
            reportTable.addCell(workshopDTO.getDescription().toString());
            reportTable.addCell(workshopDTO.getStartTime().toString());
            reportTable.addCell(workshopDTO.getEndTime().toString());
            reportTable.addCell(workshopDTO.getHallId().toString());


        }

        document.add(reportTable);
        document.close();

        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss")) + ".pdf";
        try (FileOutputStream fileOutputStream = new FileOutputStream(filename)) {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        }

        return byteArrayOutputStream.toByteArray();
    }
}
