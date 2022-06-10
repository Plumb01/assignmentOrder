package com.itc25.assignmentorder.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "[User]")
public class User {

    @Id
    private String id;

    @Column(name = "Username",nullable = false)
    private String username;
    @Column(name = "Password",nullable = false)
    private String password;
    @Column(name = "Enabled",nullable = false)
    private boolean enabled;
    @Column(name = "Role",nullable = false)
    private String role;

    @OneToOne
    @JoinColumn(name = "UserID",referencedColumnName = "MemberID")
    @MapsId
    private Member member;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = true;
    }
}
