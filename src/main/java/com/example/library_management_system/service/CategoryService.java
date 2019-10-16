package com.example.library_management_system.service;

import com.example.library_management_system.model.Category;
import com.example.library_management_system.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }

    public Category getOneCategory(int categoryId){
        Optional<Category> foundCategory = categoryRepo.findById(categoryId);
        return foundCategory.get();
    }

    public Category saveCategory(Category category) throws Exception {
        Optional<Category> foundCategory = categoryRepo.findByCategoryName(category.getCategoryName());

        if(foundCategory.isPresent()){
            throw new Exception("Category Already Exist");
        }

        return categoryRepo.save(category);
    }

    public Category updateCategory(Category category)  {

        Optional<Category> foundCategory = categoryRepo.findById(category.getId());
        foundCategory.get().setId(category.getId());
        foundCategory.get().setCategoryName(category.getCategoryName());


        return categoryRepo.save(foundCategory.get());
    }

}
