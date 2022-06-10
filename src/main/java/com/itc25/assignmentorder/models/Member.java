package com.itc25.assignmentorder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name="Member")
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "MemberID",unique = true)
    private String id;
    @Column(name = "FirstName",nullable = false)
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "BirthDate",nullable = false)
    private LocalDate birthDate;
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "DutyID")
    private Duty duty;

    @OneToMany(mappedBy = "memberAd",cascade = CascadeType.PERSIST)
    private List<Assignment> Assignment;

    @OneToMany(mappedBy = "memberSt",cascade = CascadeType.PERSIST)
    private List<Assignment> Assignmentes;

    @OneToMany(mappedBy = "member",cascade = CascadeType.PERSIST)
    private List<AssignmentHistory> assignmentHistories;

    @OneToOne(mappedBy = "member",cascade = CascadeType.PERSIST)
    private User user;

    public Member(String id, String firstName, String lastName, LocalDate birthDate, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }
}
