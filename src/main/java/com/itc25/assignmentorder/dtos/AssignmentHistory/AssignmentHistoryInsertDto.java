package com.itc25.assignmentorder.dtos.AssignmentHistory;

import com.itc25.assignmentorder.models.AssignmentHistory;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class AssignmentHistoryInsertDto {

    private final String description;
    private final String completeDate;


    public AssignmentHistory convert(){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new AssignmentHistory(description, LocalDate.parse(completeDate,formatTanggal));
    }



}
