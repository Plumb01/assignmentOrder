package com.itc25.assignmentorder.controllers;

import com.itc25.assignmentorder.dtos.Assignment.AssignmentHeaderDto;
import com.itc25.assignmentorder.dtos.Assignment.AssignmentInsertDto;
import com.itc25.assignmentorder.dtos.RestResponse;
import com.itc25.assignmentorder.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assignment")
public class AssignmentController {

    private AssignmentService service;

    @Autowired
    public AssignmentController(AssignmentService service) {
        this.service = service;
    }

    @PostMapping("insert")
    public ResponseEntity<RestResponse<Boolean>> createAssignment(@RequestBody AssignmentInsertDto newAssignment){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertAssignment(newAssignment)
                        ,"Berhasil membuat entitas"
                        , "200")
                , HttpStatus.OK);
    }

    @PostMapping("cancel")
    public ResponseEntity<RestResponse<Boolean>> cancelAssignment(String assignmentId){
        return ResponseEntity.ok().body(new RestResponse<>(service.cancelAssignmentById(assignmentId)
                ,"Assignment berhasil dicancel",
                "201"));
    }


    @PutMapping("put-ad-st")
    public ResponseEntity<RestResponse<Boolean>> addMemberToAssignment(String assignmentId,String memberIdAd,String memberIdSt){
        return new ResponseEntity<>(new RestResponse<>(service.addMemberToAssignment(assignmentId,memberIdAd,memberIdSt)
                ,"Berhasil menambahkan entitas admin dan member staff ke assignment"
                , "200")
                , HttpStatus.OK);
    }


    @GetMapping("by-year/{year}")
    public ResponseEntity<RestResponse<List<AssignmentHeaderDto>>> findAllAssignmentByYear(@PathVariable String year){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findALlAssignmentByYear(year)
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }

    @GetMapping("by-status/{status}")
    public ResponseEntity<RestResponse<List<AssignmentHeaderDto>>> findAllAssignmentByStatus(@PathVariable String status){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findALlAssignmentByStatus(status)
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }

    @GetMapping
    public ResponseEntity<RestResponse<List<AssignmentHeaderDto>>> findAllAssignmentByOrderOfUrgency(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllAssignmentByOrderOfUrgency()
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }

    @DeleteMapping("delete")
    public ResponseEntity<RestResponse<Boolean>> deleteAssignment( String id){
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteAssignmentById(id)
                        ,"Berhasil menghapus entitas"
                        , "200")
                , HttpStatus.OK);
    }


}
