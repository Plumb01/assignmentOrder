package com.itc25.assignmentorder.controllers;

import com.itc25.assignmentorder.dtos.AssignmentHistory.AssignmentHistoryInsertDto;
import com.itc25.assignmentorder.dtos.RestResponse;
import com.itc25.assignmentorder.services.AssignmentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assignmentHistory")
public class AssignmentHistoryController {

    @Autowired
    private AssignmentHistoryService service;


    @PostMapping("insert")
    public ResponseEntity<RestResponse<Boolean>> createAssignmentHistory(String assignmentId, @RequestBody AssignmentHistoryInsertDto newAssignmentHistory){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertAssignmentHistory(assignmentId,newAssignmentHistory)
                        ,"Berhasil membuat assignment history"
                        , "200")
                , HttpStatus.OK);
    }

    @PutMapping("approve")
    public ResponseEntity<RestResponse<Boolean>> addChairmanApprovalToAssignment(String memberId,String assignmentHistoryId){
        return new ResponseEntity<>(new RestResponse<>(service.managerApprovalForTicket(memberId,assignmentHistoryId)
                ,"Assignment berhasil di approve"
                , "200")
                , HttpStatus.OK);
    }
}
