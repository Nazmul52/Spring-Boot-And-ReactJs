package com.example.library_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "browse_by")
public class BrowseBy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Due Date is required.")
    private String dueDate;

    private String returnDate;

    @NotNull(message = "Issue Date is required.")
    private String issueDate;

    @OneToOne
    private Member member;

    @OneToMany
    private Set<Books> bookList = new HashSet<>();
}
