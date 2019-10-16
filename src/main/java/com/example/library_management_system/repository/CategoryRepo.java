package com.example.library_management_system.repository;

import com.example.library_management_system.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Optional<Category> findById(int id);
    Optional<Category> findByCategoryName(String categoryName);
}
