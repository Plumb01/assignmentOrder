package com.itc25.assignmentorder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Duty")
@NoArgsConstructor
public class Duty {

    @Id
    @Column(name = "DutyID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "Title",nullable = false)
    private String title;

    @OneToMany(mappedBy ="duty" ,cascade = CascadeType.PERSIST)
    private List<Member> members;

    public Duty(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

}
