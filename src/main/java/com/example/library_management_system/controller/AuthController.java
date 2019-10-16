package com.example.library_management_system.controller;

import com.example.library_management_system.config.JwtResponse;
import com.example.library_management_system.config.LoginForm;
import com.example.library_management_system.model.Member;
import com.example.library_management_system.repository.MemberRepo;
import com.example.library_management_system.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MemberRepo memberRepo;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm member, HttpServletResponse response) {

//        HttpHeaders responseHeaders = new HttpHeaders();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        member.getUsername(),
                        member.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        response.setHeader("x-auth-token", jwt);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping("/new/member")
    public ResponseEntity<String> registerUser(@Valid @RequestBody Member member, HttpServletResponse response) {


        if(memberRepo.existsByEmail(member.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Member member1 = new Member(member.getMemberName(), member.getEmail(),
                member.getMemberAddress(), member.getRoles(), member.getIssueDate(), member.getExpiry(), encoder.encode(member.getPassword()),member.getPhoneNumber(),member.getUsername());




        memberRepo.save(member1);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        member.getUsername(),
                        member.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        response.setHeader("x-auth-token", jwt);

        return ResponseEntity.ok().body("Member registered successfully!");
    }
}
