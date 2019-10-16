package com.example.library_management_system.repository;

import com.example.library_management_system.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends JpaRepository<Books, Integer> {
    List<Books> findByBookNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(String bookName, String authorName);
    Optional<Books> findById(int bookId);
    Optional<Books> findByBookName(String bookName);


}
