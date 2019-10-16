package com.example.library_management_system.service;

import com.example.library_management_system.model.Member;
import com.example.library_management_system.model.Role;
import com.example.library_management_system.repository.MemberRepo;
import com.example.library_management_system.repository.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    RoleRepo roleRepo;

    public List<Member> getAllMember(){
        return memberRepo.findAll();
    }

    public Member getOneMember(int memberId){
        Optional<Member> foundMember = memberRepo.findById(memberId);
        return foundMember.get();
    }


    public void createMember(Member member) {
        Optional<Member> localMember = memberRepo.findByUsername(member.getUsername());
        if(localMember.isPresent()){
            LOG.info("member with username {} already exist. Nothing will done");
        }else{
//            for(Role ur: userRoles){
//                roleRepo.save(ur);
//            }
//            member.getRoles().addAll(userRoles);
            memberRepo.save(member);
        }



    }
}
