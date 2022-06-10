package com.itc25.assignmentorder.dtos.Assignment;

import com.itc25.assignmentorder.models.Assignment;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class AssignmentInsertDto {

    private final String title;
    private final String details;
    private final String requestDate;
    private final String dueDate;
    private final String urgency;

    public Assignment convert(){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new Assignment(title,details, LocalDate.parse(requestDate,formatTanggal),
                LocalDate.parse(dueDate,formatTanggal),urgency);
    }
}
