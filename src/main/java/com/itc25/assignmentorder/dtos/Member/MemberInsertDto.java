package com.itc25.assignmentorder.dtos.Member;

import com.itc25.assignmentorder.models.Member;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class MemberInsertDto {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String birthDate;
    private final String phone;

    public Member convert(){
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return new Member(id,firstName,lastName, LocalDate.parse(birthDate,formatTanggal),phone);
    }
}
