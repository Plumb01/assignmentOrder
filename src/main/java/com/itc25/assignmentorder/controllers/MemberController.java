package com.itc25.assignmentorder.controllers;

import com.itc25.assignmentorder.dtos.Member.MemberHeaderDto;
import com.itc25.assignmentorder.dtos.Member.MemberInsertDto;
import com.itc25.assignmentorder.dtos.Member.MemberUpdateDto;
import com.itc25.assignmentorder.dtos.RestResponse;
import com.itc25.assignmentorder.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {

    private MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping
    public Authentication index(Authentication authentication){
        return authentication;
    }

    @GetMapping("{id}")
    public ResponseEntity<RestResponse<MemberHeaderDto>> findMemberById(@PathVariable String id){
        return ResponseEntity.ok()
                .body(new RestResponse<>(service.findMemberById(id)
                        , "Berhasil ditemkuan"
                        ,"200"));
    }

    @GetMapping("find-all")
    public ResponseEntity<RestResponse<List<MemberHeaderDto>>> findAllMember(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllMember()
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }


    @PostMapping("insert")
    public ResponseEntity<RestResponse<Boolean>> insert(@RequestBody MemberInsertDto newMember){
        return new ResponseEntity<>(
                new RestResponse<>(service.insertMember(newMember)
                        ,"Berhasil membuat entitas",
                        "201"),
                HttpStatus.CREATED);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<RestResponse<Boolean>> update(@PathVariable String id, @RequestBody MemberUpdateDto updateMember){
        return new ResponseEntity<>(
                new RestResponse<>(service.updateMember(id,updateMember)
                        ,"Berhasil mengupdate entitas"
                        , "200")
                , HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<RestResponse<Boolean>> delete(@PathVariable String id){
        return new ResponseEntity<>(
                new RestResponse<>(service.deleteMemberById(id)
                        ,"Berhasil menghapus entitas"
                        , "200")
                , HttpStatus.OK);
    }

    @GetMapping("staff-mem")
    public ResponseEntity<RestResponse<List<MemberHeaderDto>>> findAllStaffMember(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllStaffMember()
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }

    @GetMapping("active-st")
    public ResponseEntity<RestResponse<List<MemberHeaderDto>>> findAllActiveTechSupEmployee(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllActiveMember()
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }

    @GetMapping("non-active-st")
    public ResponseEntity<RestResponse<List<MemberHeaderDto>>> findAllNonActiveStaffMember(){
        return ResponseEntity.ok()
                .body(new RestResponse<>(
                        service.findAllNonActiveMember()
                        ,"Berhasil ditemkuan"
                        ,"200"));
    }

}
