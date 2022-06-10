package com.itc25.assignmentorder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "AssignmentHistory")
@NoArgsConstructor
public class AssignmentHistory {

    @Id
    private String id;

    @Column(name = "Description",nullable = false)
    private String description;
    @Column(name = "CompletedDate",nullable = false)
    private LocalDate completeDate;
    @Column(name = "Status",nullable = false)
    private String status;

    @OneToOne
    @JoinColumn(name = "AssignmentID",referencedColumnName = "AssignmentID")
    @MapsId
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "ApprovedBy")
    private Member member;

    public AssignmentHistory(String description,LocalDate  completeDate) {
        this.description = description;
        this.completeDate = completeDate;

    }

}
