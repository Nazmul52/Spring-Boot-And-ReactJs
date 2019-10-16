package com.example.library_management_system.repository;

import com.example.library_management_system.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {
    Optional<Member> findByMemberName(String memberName);
    Optional<Member> findByUsername(String username);
    Optional<Member> findById(int memberId);

    Optional<Member> findByEmail(String email);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

}
