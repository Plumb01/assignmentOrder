package com.itc25.assignmentorder.services;

import com.itc25.assignmentorder.dtos.AssignmentHistory.AssignmentHistoryInsertDto;
import com.itc25.assignmentorder.execptions.EntityNotAcceptableExecption;
import com.itc25.assignmentorder.execptions.EntityNotFoundExecption;
import com.itc25.assignmentorder.models.Assignment;
import com.itc25.assignmentorder.models.AssignmentHistory;
import com.itc25.assignmentorder.models.Member;
import com.itc25.assignmentorder.repositories.AssignmentHistoryRepository;
import com.itc25.assignmentorder.repositories.AssignmentRepository;
import com.itc25.assignmentorder.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentHistoryService {

    private AssignmentHistoryRepository assignmentHistoryRepository;
    private AssignmentRepository assignmentRepository;
    private MemberRepository memberRepository;

    @Autowired
    public AssignmentHistoryService(AssignmentHistoryRepository assignmentHistoryRepository, AssignmentRepository assignmentRepository, MemberRepository memberRepository) {
        this.assignmentHistoryRepository = assignmentHistoryRepository;
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    public boolean insertAssignmentHistory(String assignmentId, AssignmentHistoryInsertDto newAssignmentHistory){
        Assignment assignment = assignmentRepository.getById(assignmentId);

        if(assignment.getStatus().equals("CANCELED")){
            throw new EntityNotAcceptableExecption("Assignment telah di cancel");
        }

        assignment.setStatus("COMPLETED");
        assignmentRepository.save(assignment);
        AssignmentHistory assignmentHistory = newAssignmentHistory.convert();
        if(assignmentHistory.getCompleteDate().isBefore(assignment.getDueDate())){
            assignmentHistory.setStatus("ON_TIME");
        }else{
            assignmentHistory.setStatus("OVERDUE");
        }
        assignmentHistory.setAssignment(assignment);
        assignmentHistoryRepository.save(assignmentHistory);
        return true;
    }

    public boolean managerApprovalForTicket(String memberId, String assignmentHistoryId){

        AssignmentHistory assignmentHistory = assignmentHistoryRepository.findById(assignmentHistoryId)
                .orElseThrow(() -> new EntityNotFoundExecption("id assignment tidak ditemukan"));

        if(memberId.indexOf("C") != -1){
            Member member = memberRepository.findById(memberId)
                    .orElseThrow(()-> new EntityNotFoundExecption("id member chairman tidak ditemukan"));

            assignmentHistory.setMember(member);
        }else{
            throw new EntityNotFoundExecption("id employee tidak sesuai");
        }

        assignmentHistoryRepository.save(assignmentHistory);
        return true;
    }

}
