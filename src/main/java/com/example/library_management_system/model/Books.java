package com.example.library_management_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull( message = "Book Name is required.")
    private String bookName;

    @NotNull( message = "Author Name is required.")
    private String authorName;

    @NotNull( message = "Price is required.")
    private double price;

    @NotNull( message = "Category is required.")
    @OneToOne
    private Category category;

    private boolean available;
}
