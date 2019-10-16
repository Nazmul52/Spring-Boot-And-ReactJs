package com.example.library_management_system.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Member Name is required.")
    private String memberName;

    @NotNull(message = "Member Address is required.")
    private String memberAddress;

    @NotNull(message = "Issue Date is required.")
    private String issueDate;

    @NotNull(message = "Expiry Date is required.")
    private String expiry;

    @NotNull
    @Size(max = 50)
    @Email
    private String email;


    @NotNull(message = "Phone Number is required.")
    @Size(max = 11)
    private String phoneNumber;

    @NotNull(message = "Username is required.")
    @Size(min = 5,max = 20)
    private String username;

    @NotNull(message = "Password is required")
    @Size(min=4, max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "member_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Member(){

    }

    public Member(String memberName, String email, String memberAddress,  Set<Role> role,  String issueDate, String expiry,  String encode, String phoneNumber, String username) {
        this.memberName = memberName;
        this.email = email;
        this.memberAddress = memberAddress;
        this.issueDate = issueDate;
        this.expiry = expiry;
        this.password = encode;
        this.roles = role;
        this.phoneNumber = phoneNumber;
        this.username = username;

    }
}
