package com.example.library_management_system.service;

import com.example.library_management_system.model.Role;
import com.example.library_management_system.repository.RoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    RoleRepo roleRepo;

    public void saveRole(Role role){

        Optional<Role> foundRole = roleRepo.findByName(role.getName());

        if(foundRole.isPresent()){
            LOG.info("Role already Created");

        }else{
            roleRepo.save(role);

        }
    }
}
