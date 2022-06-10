package com.itc25.assignmentorder.dtos.Member;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class MemberUpdateDto {

    private final String firstName;
    private final String lastName;
    private final String birthDate;
    private final String phone;

    public LocalDate convertDate(){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalLahir = LocalDate.parse(birthDate,formatTanggal);

        return tanggalLahir;
    }
}
