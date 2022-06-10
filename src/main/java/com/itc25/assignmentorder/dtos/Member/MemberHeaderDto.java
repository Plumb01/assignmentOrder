package com.itc25.assignmentorder.dtos.Member;

import com.itc25.assignmentorder.models.Assignment;
import com.itc25.assignmentorder.models.Member;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
public class MemberHeaderDto {

    private final String firstName;
    private final String lastName;
    private final Integer DutyId;

    public static MemberHeaderDto set(Member member){
        return new MemberHeaderDto(member.getFirstName(),member.getLastName(),member.getDuty().getId());
    }

    public static List<MemberHeaderDto> toList(List<Member> members){
        List<MemberHeaderDto> result = new ArrayList<>();

        for(Member member : members){
            result.add(set(member));
        }

        return result;
    }

    public static List<MemberHeaderDto> toListActiveMember(List<Assignment> assignments){
        List<MemberHeaderDto> result = new ArrayList<>();

        for(Assignment assignment:assignments){
            if(assignment.getMemberSt().getDuty().getId().equals(2) && assignment.getStatus().equals("IN_PROGRESS")){
                result.add(set(assignment.getMemberSt()));
            }
        }

        return result;
    }

    public static List<MemberHeaderDto> toListNonActiveMember(List<Member> members){
        List<MemberHeaderDto> result = new ArrayList<>();

        for(Member member:members){
            if(member.getDuty().getId().equals(2) && member.getAssignmentes().isEmpty()){
                result.add(set(member));
            }
        }
        return result;
    }

    public static List<MemberHeaderDto> toListStaffMember(List<Member> members){
        List<MemberHeaderDto> result = new ArrayList<>();

        Stream<Member> memberStream = members.stream();
        Stream<Member> streamResult = memberStream.filter((member)->{
            if (member.getDuty().getId().equals(2)) {
                return  true;
            } else {
                return false;
            }
        });
        List<Member> listResult = streamResult.collect(Collectors.toList());

        for(var resulte: listResult){
            result.add(set(resulte));
        }
        return result;
    }
}
