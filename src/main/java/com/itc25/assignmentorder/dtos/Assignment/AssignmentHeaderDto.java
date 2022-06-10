package com.itc25.assignmentorder.dtos.Assignment;

import com.itc25.assignmentorder.models.Assignment;
import com.itc25.assignmentorder.models.Member;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class AssignmentHeaderDto {

    private final String id;
    private final String title;
    private final String details;
    private final LocalDate requestDate;
    private final LocalDate dueDate;
    private final String urgency;
    private final String memberIdAdmin;
    private final String memberIdSt;

    public static AssignmentHeaderDto set(Assignment assignment){
        Member memberAdmin = assignment.getMemberAd();
        Member memberStaff = assignment.getMemberSt();
        return new AssignmentHeaderDto(assignment.getId(),assignment.getTitle(),assignment.getDetails(),assignment.getRequestDate(),assignment.getDueDate(),assignment.getUrgency(),
                memberAdmin == null ? null : memberAdmin.getId(),memberStaff == null ? null : memberStaff.getId());
    }

    public static Integer nomorIdAssignment(List<Assignment> assignments){
        List<AssignmentHeaderDto> result = new ArrayList<>();

        for (Assignment assignment: assignments){
            result.add(set(assignment));
        }
        int nomorId = result.size() + 1;
        return nomorId;
    }

    public static List<AssignmentHeaderDto> toList(List<Assignment> assignments){
        List<AssignmentHeaderDto> result = new ArrayList<>();

        for (Assignment assignment: assignments) {
            if (assignment.getUrgency().equals("HIGH")) {
                result.add(set(assignment));
            }
        }

        for (Assignment assignment: assignments) {
            if (assignment.getUrgency().equals("MEDIUM")) {
                result.add(set(assignment));
            }
        }

        for (Assignment assignment: assignments) {
            if (assignment.getUrgency().equals("LOW")) {
                result.add(set(assignment));
            }
        }

        return result;
    }
}
