package com.example.library_management_system.controller;

import com.example.library_management_system.model.Category;
import com.example.library_management_system.repository.CategoryRepo;
import com.example.library_management_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryRepo categoryRepo;


    @GetMapping(value = "/list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllCategory(){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategory());
    }

    @GetMapping(value = "/view/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getOneCategoryInfo(@PathVariable("categoryId") int categoryId){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOneCategory(categoryId));
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveCategory(@RequestBody Category category){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.saveCategory(category));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/update/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") int categoryId,  @RequestBody Category category){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(category));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping(value = "/delete/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBook(@PathVariable("categoryId") int categoryId){
        Optional<Category> foundCategory = categoryRepo.findById(categoryId);
        if(foundCategory.isPresent()){
            categoryRepo.deleteById(categoryId);
        }else{
           return ResponseEntity.ok("Category Id Not Found");
        }

        return  ResponseEntity.ok("Category Delete Successfully.");

    }
}
