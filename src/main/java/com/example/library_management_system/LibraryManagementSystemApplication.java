package com.example.library_management_system;

import com.example.library_management_system.model.Member;
import com.example.library_management_system.model.Role;
import com.example.library_management_system.repository.MemberRepo;
import com.example.library_management_system.repository.RoleRepo;
import com.example.library_management_system.service.MemberService;
import com.example.library_management_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableAsync
public class LibraryManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Autowired
	MemberService memberService;

	@Autowired
	RoleService roleRepo;

	@Autowired
	PasswordEncoder encoder;

	@Override
	public void run(String... args) throws Exception {
		Member member = new Member();

		member.setMemberName("Nazmul Huda");
		member.setPassword(encoder.encode("nazmul"));

		member.setEmail("nazmul8842@gmail.com");
        member.setUsername("Nazmul42");

        member.setPhoneNumber("01767888842");
		member.setExpiry("01/12/3030");
		member.setIssueDate("01/10/2019");
		member.setMemberAddress("Uttara, Dhaka");

		Set<Role> roleSet = new HashSet<>();
		Role role = new Role();

		role.setName("ROLE_ADMIN");
		roleSet.add(role);
		roleRepo.saveRole(role);
		member.setRoles(roleSet);
//		System.out.println("member" + member);
		memberService.createMember(member);
		roleSet.clear();
	}
}
