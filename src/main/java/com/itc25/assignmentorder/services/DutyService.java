package com.itc25.assignmentorder.services;

import com.itc25.assignmentorder.dtos.Duty.DutyInsertDto;
import com.itc25.assignmentorder.models.Duty;
import com.itc25.assignmentorder.repositories.DutyRepository;
import org.springframework.stereotype.Service;

@Service
public class DutyService {

    private DutyRepository dutyRepository;

    public DutyService(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    //insert
    public boolean insertDuty(DutyInsertDto newDuty){
        Duty duty = newDuty.convert();

        dutyRepository.save(duty);
        return true;
    }
}
