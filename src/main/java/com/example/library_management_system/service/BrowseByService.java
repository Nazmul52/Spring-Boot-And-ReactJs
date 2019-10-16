package com.example.library_management_system.service;

import com.example.library_management_system.model.BrowseBy;
import com.example.library_management_system.repository.BrowseByRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrowseByService {

    @Autowired
    BrowseByRepo browseByRepo;

    public List<BrowseBy> getAll(){
        return browseByRepo.findAll();
    }

    public BrowseBy getOne(int id){
        Optional<BrowseBy> foundBrowseBy = browseByRepo.findById(id);

        return foundBrowseBy.get();
    }

    public List<BrowseBy> getMemberBookList(int memberId){

        return browseByRepo.findByMemberId(memberId);
    }


    public BrowseBy saveBrowseBy(BrowseBy browseBy) {

        return browseByRepo.save(browseBy);
    }

    public void deleteRecord(int id){

        browseByRepo.deleteById(id);
    }
}
