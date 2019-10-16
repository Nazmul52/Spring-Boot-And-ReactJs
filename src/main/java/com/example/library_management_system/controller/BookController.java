package com.example.library_management_system.controller;


import com.example.library_management_system.model.Books;
import com.example.library_management_system.repository.BookRepo;
import com.example.library_management_system.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    BooksService booksService;

    @Autowired
    BookRepo bookRepo;


    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllBooks(){

        return ResponseEntity.status(HttpStatus.OK).body(booksService.getAllBooks());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping(value = "/view/{bookId}")
    public ResponseEntity<?> getOneBookInfo(@PathVariable("bookId") int bookId){

        return ResponseEntity.status(HttpStatus.OK).body(booksService.getOneBooks(bookId));
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping(value = "/search")
    public ResponseEntity<?> getBookNameSearch(@RequestParam(value = "bookName" , required = false, defaultValue = "0") String bookName,
                                               @RequestParam(value = "authorName" , required = false, defaultValue = "0") String authorName){

        return ResponseEntity.status(HttpStatus.OK).body(booksService.getBooksInfo(bookName, authorName));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveBook(@RequestBody Books books){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(booksService.saveBook(books));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/update/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable("bookId") int bookid,  @RequestBody Books books){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(booksService.updateBook(books));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable("bookId") int bookId){


        Optional<Books> foundBook = bookRepo.findById(bookId);
        if(foundBook.isPresent()){
            bookRepo.deleteById(bookId);
        }else{
            return ResponseEntity.ok("Book Id Not Found");
        }

        return  ResponseEntity.ok("Book Delete Successfully.");
    }
}
