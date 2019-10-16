package com.example.library_management_system.repository;

import com.example.library_management_system.model.Books;
import com.example.library_management_system.model.BrowseBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrowseByRepo extends JpaRepository<BrowseBy, Integer> {
    Optional<BrowseBy> findById(int id);

//    Optional<BrowseBy> findByMemberId(int memberId);

    List<BrowseBy> findByMemberId(int member);

}
