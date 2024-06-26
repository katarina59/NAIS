package rs.ac.uns.acs.nais.ColumnarDatabaseService.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.HallStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.LocationStatisticsDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.dto.WorkshopDTO;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Hall;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.entity.Workshop;
import rs.ac.uns.acs.nais.ColumnarDatabaseService.repository.HallRepository;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HallService {

    @Autowired
    private HallRepository hallRepository;



    @Autowired
    private ModelMapper mapper;
    private Hall mapToEntity(HallDTO hallDTO){
        Hall hall = mapper.map(hallDTO, Hall.class);
        return hall;
    }

    private HallDTO mapToDTO(Hall hall){
        HallDTO hallDTO= mapper.map(hall, HallDTO.class);
        return hallDTO;
    }

    public HallDTO createHall(HallDTO hallDTO){
        Hall hall = mapToEntity(hallDTO);

        return mapToDTO(hallRepository.save(hall));
    }

    public HallDTO deleteHall(Long id){
        Hall hall= hallRepository.getById(id);
        hallRepository.delete(hall);
        return mapToDTO(hall);
    }

    public List<HallDTO> getAll(){
        List<Hall> halls = hallRepository.findAll();
        return halls.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    public HallDTO update(HallDTO hallDTO, Long id){
        Hall hall = hallRepository.getById(id);
        hall.setHallId(hallDTO.getHallId());
        hall.setHallId(hallDTO.getHallId());
        hall.setCapacity(hallDTO.getCapacity());
        hall.setBookingFee(hallDTO.getBookingFee());
        hall.setName(hallDTO.getName());
        hall.setEventsCount(hallDTO.getEventsCount());
        hall.setLocation(hallDTO.getLocation());
        return mapToDTO(hallRepository.save(hall));
    }

    public Long getCheapestHall(){
        Double price =  hallRepository.getLowestPrice();
        return hallRepository.getCheapestHall(price);
    }

    public HallDTO getHall(Long id){
        return mapToDTO(hallRepository.getById(id));
    }

    public List<HallDTO> findHallsWithBookingFeeRange(Double minPrice, Double maxPrice){
        List<Hall> halls = hallRepository.findHallsWithBookingFeeRange(minPrice, maxPrice);
        return halls.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public HallStatisticsDTO getHallStatisticsByLocation(String location) {

        return hallRepository.findHallStatisticsByLocation(location);
    }


    public List<LocationStatisticsDTO> getLocationstatistics(){
        return hallRepository.getLocationStatistics();
    }

    public List<HallDTO> getHallsForReport(){
        return hallRepository.getHallsForReport();
    }

    public byte[] exportSimpleHallReport(List<HallDTO> statistics) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();

        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, Font.BOLD);
        Paragraph title = new Paragraph("HALLS SIMPLE STATISTICS REPORT", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add spacing
        document.add(new Paragraph("\n"));

        PdfPTable reportTable = new PdfPTable(6);
        reportTable.setWidthPercentage(100);

        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Font.BOLD);
        PdfPCell headerCell1 = new PdfPCell(new Paragraph("Hall ID", headerFont));
        PdfPCell headerCell2 = new PdfPCell(new Paragraph("Location", headerFont));
        PdfPCell headerCell3 = new PdfPCell(new Paragraph("Name", headerFont));
        PdfPCell headerCell4 = new PdfPCell(new Paragraph("Capacity", headerFont));
        PdfPCell headerCell5 = new PdfPCell(new Paragraph("Booking fee", headerFont));
        PdfPCell headerCell6 = new PdfPCell(new Paragraph("Events Number", headerFont));

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

        for (HallDTO hall : statistics) {
            reportTable.addCell(hall.getHallId().toString());
            reportTable.addCell(hall.getLocation());
            reportTable.addCell(hall.getName());
            reportTable.addCell(hall.getCapacity().toString());
            reportTable.addCell(hall.getBookingFee().toString());
            reportTable.addCell(hall.getEventsCount().toString());

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
