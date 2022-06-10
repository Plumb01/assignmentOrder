package com.itc25.assignmentorder.services;

import com.itc25.assignmentorder.dtos.Member.MemberHeaderDto;
import com.itc25.assignmentorder.dtos.Member.MemberInsertDto;
import com.itc25.assignmentorder.dtos.Member.MemberUpdateDto;
import com.itc25.assignmentorder.execptions.EntityNotAcceptableExecption;
import com.itc25.assignmentorder.execptions.EntityNotFoundExecption;
import com.itc25.assignmentorder.models.Member;
import com.itc25.assignmentorder.repositories.AssignmentRepository;
import com.itc25.assignmentorder.repositories.DutyRepository;
import com.itc25.assignmentorder.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    private DutyRepository dutyRepository;
    private AssignmentRepository assignmentRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository, DutyRepository dutyRepository, AssignmentRepository assignmentRepository) {
        this.memberRepository = memberRepository;
        this.dutyRepository = dutyRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public boolean insertMember(MemberInsertDto newMember){

        Member member = newMember.convert();

        List<Member> members = memberRepository.findAll();
        for (Member membero:members){
            if(membero.getId().equals(member.getId())){
                throw new EntityNotAcceptableExecption(" id sudah terpakai");
            }
        }

        if(member.getId().indexOf("A") > -1){
            member.setDuty(dutyRepository.getById(1));
        }else if (member.getId().indexOf("B") > -1){
            member.setDuty(dutyRepository.getById(2));
        }else if(member.getId().indexOf("C") > -1){
            member.setDuty(dutyRepository.getById(3));
        }

        memberRepository.save(member);
        return true;
    }

    //update
    public boolean updateMember(String id, MemberUpdateDto member){
        Member oldMember = memberRepository.getById("");
        try{
            oldMember = memberRepository.getById(id);
        }catch (Exception ex){
            throw new EntityNotFoundExecption("id tidak ditemukan");
        }

        oldMember.setFirstName(member.getFirstName());
        oldMember.setLastName(member.getLastName());
        oldMember.setBirthDate(member.convertDate());
        oldMember.setPhoneNumber(member.getPhone());

        memberRepository.save(oldMember);
        return true;
    }


    //delete
    public boolean deleteMemberById(String id){
        try{
            memberRepository.deleteById(id);
        }catch (Exception ex){
            throw new EntityNotFoundExecption("id tidak ditemukan");
        }
        return true;

    }

    public MemberHeaderDto findMemberById(String id){
        Member  member = memberRepository.findById(id).get();
        return MemberHeaderDto.set(member);
    }

    public List<MemberHeaderDto> findAllMember(){
        return MemberHeaderDto.toList(memberRepository.findAll());
    }

    public List<MemberHeaderDto> findAllActiveMember(){
        return MemberHeaderDto.toListActiveMember(assignmentRepository.findAll());
    }

    public List<MemberHeaderDto> findAllNonActiveMember(){
        return MemberHeaderDto.toListNonActiveMember(memberRepository.findAll());
    }

    public List<MemberHeaderDto> findAllStaffMember(){
        return MemberHeaderDto.toListStaffMember(memberRepository.findAll());
    }
}
