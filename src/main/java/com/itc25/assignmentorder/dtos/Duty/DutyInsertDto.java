package com.itc25.assignmentorder.dtos.Duty;

import com.itc25.assignmentorder.models.Duty;
import lombok.Data;

@Data
public class DutyInsertDto {

    private final Integer id;
    private final String title;

    public Duty convert(){return new Duty(id,title);}
}
