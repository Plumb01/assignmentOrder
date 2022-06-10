package com.itc25.assignmentorder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "Assignment")
@NoArgsConstructor
public class Assignment {

    @Id
    @Column(name = "AssignmentID",unique = true)
    private String id;
    @Column(name = "Title",nullable = false)
    private String title;
    @Column(name = "Details",nullable = false)
    private String details;
    @Column(name = "RequestDate",nullable = false)
    private LocalDate requestDate;
    @Column(name = "DueDate",nullable = false)
    private LocalDate dueDate;
    @Column(name = "Urgency",nullable = false)
    private String urgency;
    @Column(name = "Status",nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "CreatedBy")
    private Member memberAd;

    @ManyToOne
    @JoinColumn(name = "AppointedTo")
    private Member memberSt;

    @OneToOne(mappedBy = "assignment",cascade = CascadeType.PERSIST)
    private AssignmentHistory assignmentHistory;

    public Assignment( String title, String details, LocalDate requestDate, LocalDate dueDate, String urgency) {
        this.title = title;
        this.details = details;
        this.requestDate = requestDate;
        this.dueDate = dueDate;
        this.urgency = urgency;
        this.status = "IN_PROGRESS";
    }
}
