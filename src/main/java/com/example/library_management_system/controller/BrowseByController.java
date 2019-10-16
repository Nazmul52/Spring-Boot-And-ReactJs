package com.example.library_management_system.controller;


import com.example.library_management_system.model.BrowseBy;
import com.example.library_management_system.repository.BrowseByRepo;
import com.example.library_management_system.service.BrowseByService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/browseBy")
public class BrowseByController {

    @Autowired
    BrowseByService browseByService;

    @Autowired
    BrowseByRepo browseByRepo;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllBrowseByInfo(){

        return ResponseEntity.status(HttpStatus.OK).body(browseByService.getAll());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/view/{browseId}")
    public ResponseEntity<?> getOneBookInfo(@PathVariable("browseId") int browseId){

        return ResponseEntity.status(HttpStatus.OK).body(browseByService.getOne(browseId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/searchMember")
    public ResponseEntity<?> searchMember(@RequestParam(value = "memberId", required = false, defaultValue = "0") int browseId){

        return ResponseEntity.status(HttpStatus.OK).body(browseByService.getMemberBookList(browseId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveBook(@RequestBody BrowseBy browseBy){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(browseByService.saveBrowseBy(browseBy));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete/{browseId}")
    public ResponseEntity<?> deleteBook(@PathVariable("browseId") int browseId){


        Optional<BrowseBy> foundBrowseBy = browseByRepo.findById(browseId);
        if(foundBrowseBy.isPresent()){
            browseByRepo.deleteById(browseId);
        }else{
            return ResponseEntity.ok("Browse By Id Not Found");
        }

        return  ResponseEntity.ok("Browse By Delete Successfully.");
    }
}
