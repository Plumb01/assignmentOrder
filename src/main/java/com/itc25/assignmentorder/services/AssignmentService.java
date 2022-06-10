package com.itc25.assignmentorder.services;

import com.itc25.assignmentorder.dtos.Assignment.AssignmentHeaderDto;
import com.itc25.assignmentorder.dtos.Assignment.AssignmentInsertDto;
import com.itc25.assignmentorder.execptions.EntityNotAcceptableExecption;
import com.itc25.assignmentorder.execptions.EntityNotFoundExecption;
import com.itc25.assignmentorder.models.Assignment;
import com.itc25.assignmentorder.models.Member;
import com.itc25.assignmentorder.repositories.AssignmentHistoryRepository;
import com.itc25.assignmentorder.repositories.AssignmentRepository;
import com.itc25.assignmentorder.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Locale;

@Service
public class AssignmentService {

    private MemberRepository memberRepository;
    private AssignmentRepository assignmentRepository;
    private AssignmentHistoryRepository assignmentHistoryRepository;

    @Autowired
    public AssignmentService(MemberRepository memberRepository, AssignmentRepository assignmentRepository, AssignmentHistoryRepository assignmentHistoryRepository) {
        this.memberRepository = memberRepository;
        this.assignmentRepository = assignmentRepository;
        this.assignmentHistoryRepository = assignmentHistoryRepository;
    }

    public boolean insertAssignment(AssignmentInsertDto newAssignment){

        int year = Year.now().getValue();
        int nomorId = nomorId();
        List<Assignment> assignments = assignmentRepository.findAll();
        Assignment assignment = newAssignment.convert();
        for(Assignment assignment1 : assignments){
            if(assignment1.getId().equals(assignment.getId())){
                assignment.setId(String.format("ARQ/%d/%d",year,nomorId -1));
            }
        }
        assignment.setId(String.format("ARQ/%d/%d",year,nomorId));
        assignment.setUrgency(assignment.getUrgency().toUpperCase(Locale.ROOT));

        assignmentRepository.save(assignment);
        return true;
    }


    public boolean addMemberToAssignment(String assignmentId,String memberIdAd, String memberIdSt){
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(()-> new EntityNotFoundExecption("assignment tidak ditemukan"));
        if (memberIdAd.indexOf("A") != -1 ){
            Member memberAdmin = memberRepository.findById(memberIdAd)
                    .orElseThrow(()-> new EntityNotFoundExecption("member tidak ditemukan"));
            assignment.setMemberAd(memberAdmin);
        }else{
            throw new EntityNotFoundExecption("id tidak sesuai");
        }
        if(memberIdSt.indexOf("B") != -1){
            Member memberStaff = memberRepository.findById(memberIdSt)
                    .orElseThrow(()-> new EntityNotFoundExecption("member tidak ditemukan"));
            assignment.setMemberSt(memberStaff);
        }else{
            throw new EntityNotFoundExecption("id tidak sesuai");
        }
        assignmentRepository.save(assignment);
        return true;
    }

    public Integer nomorId(){
        int x = 0;
        try{x = AssignmentHeaderDto.nomorIdAssignment(assignmentRepository.findAll());}
        catch (Exception ex){x = 0;}
        return x;
    }

    public boolean cancelAssignmentById(String id){
        try {
            Assignment assignment = assignmentRepository.getById(id);

            if(assignment.getStatus().equals("COMPLETED")){
                throw new EntityNotAcceptableExecption("assignment yang ditunjuk telah selesai tidak dapat digagalkan");
            }
            assignment.setStatus("CANCELED");


            assignmentRepository.save(assignment);

        }catch (Exception ex){
            throw new EntityNotFoundExecption("id tidak ditemukan atau assignment yang ditunjuk telah selesai tidak dapat digagalkan");
        }
        return true;
    }

    public AssignmentHeaderDto findAssignmentById(String id){

        try {
            Assignment assignment = assignmentRepository.findById(id).get();

            return AssignmentHeaderDto.set(assignment);
        }catch (Exception ex){
            throw new EntityNotFoundExecption("id tidak ditemukan");
        }
    }

    //findby year
    public List<AssignmentHeaderDto> findALlAssignmentByYear(String year){

        int numberYear = Integer.parseInt(year);

        if(numberYear < 2020 || numberYear > LocalDate.now().getYear()){
            throw new EntityNotFoundExecption("input tahun tidak normal");
        }
        String numberYearStr = String.format("%d",numberYear);

        return AssignmentHeaderDto.toList(assignmentRepository.getAssignmentByYear(numberYearStr));

    }

    //findby Status
    public List<AssignmentHeaderDto> findALlAssignmentByStatus(String status){
        String statusIs ="";
        switch (status.toUpperCase(Locale.ROOT)){
            case "IN_PROGRESS","INPROGRESS":
                statusIs = "IN_PROGRESS";
                break;
            case "COMPLETED":
                statusIs = "COMPLETED";
                break;
            case "CANCELED":
                statusIs = "CANCELED";
                break;
            default:
                throw new EntityNotFoundExecption("input status tidak sesuai");
        }
        return AssignmentHeaderDto.toList(assignmentRepository.getAssignmentByStatus(statusIs));
    }

    //findall orderBy Urgency
    public List<AssignmentHeaderDto> findAllAssignmentByOrderOfUrgency(){
        return AssignmentHeaderDto.toList(assignmentRepository.findAll());
    }

    public boolean deleteAssignmentById(String id){
        try{

            assignmentRepository.deleteById(id);
        }catch (Exception ex){
            throw new EntityNotFoundExecption("id tidak ditemukan");
        }
        return true;

    }

}
