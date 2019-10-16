package com.example.library_management_system.service;

import com.example.library_management_system.model.Member;
import com.example.library_management_system.model.UserPrinciple;
import com.example.library_management_system.repository.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberRepo memberRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

        return UserPrinciple.build(member);

    }
}
