package com.example.library_management_system.service;

import com.example.library_management_system.model.Books;
import com.example.library_management_system.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    @Autowired
    BookRepo bookRepo;

    public List<Books> getAllBooks(){
        return bookRepo.findAll();
    }

    public Books getOneBooks(int bookId){
        Optional<Books> foundBook = bookRepo.findById(bookId);

        return foundBook.get();
    }

    public List<Books> getBooksInfo(String bookName, String authorName){
        List<Books> foundBookName = bookRepo.findByBookNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(bookName, authorName);

        System.out.println(foundBookName);
        return bookRepo.findByBookNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCase(bookName, authorName);
    }

    public Books saveBook(Books books) throws Exception {
        Optional<Books> foundBooks = bookRepo.findByBookName(books.getBookName());

        if(foundBooks.isPresent()){
            throw new Exception("Book Already Exist");
        }

        return bookRepo.save(books);
    }

    public Books updateBook(Books books) throws Exception {

        Optional<Books> foundBook = bookRepo.findById(books.getId());



        foundBook.get().setBookName(books.getBookName());
        foundBook.get().setAuthorName(books.getAuthorName());
        foundBook.get().setPrice(books.getPrice());
        foundBook.get().setAvailable(books.isAvailable());
        foundBook.get().setCategory(books.getCategory());

        return bookRepo.save(foundBook.get());
    }



}
