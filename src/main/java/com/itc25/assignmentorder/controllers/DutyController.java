package com.itc25.assignmentorder.controllers;

import com.itc25.assignmentorder.dtos.Duty.DutyInsertDto;
import com.itc25.assignmentorder.dtos.RestResponse;
import com.itc25.assignmentorder.services.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duty")
public class DutyController {

    private DutyService service;

    @Autowired
    public DutyController(DutyService service) {
        this.service = service;
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<Boolean>> insert(@RequestBody DutyInsertDto newDuty){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertDuty(newDuty)
                        ,"Berhasil membuat entitas",
                        "201"),
                HttpStatus.CREATED);
    }
}
