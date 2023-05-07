package com.rest.book.springbootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.book.springbootrestbook.models.Book;
import com.rest.book.springbootrestbook.services.BookService;

// @Controller
// @controller for spring mvc but for creating the REST API we use @restcontroller 
@RestController
public class BookController {

    // handler
    // @RequestMapping(value = "/books", method = RequestMethod.GET)
    // both are same
    // @ResponseBody
    // @GetMapping("/books")
    // public String getBooks() {

    // return "this is testing books";
    // }

    // this will automatically converts book object into the json using the jackson
    // dependency in spring boot

    @Autowired
    private BookService bookService;

    // @GetMapping("/books")
    // public List<Book> getBooks() {
    // // Book book = new Book();
    // // book.setId(1234);
    // // book.setTitle("Java Complete Reference");
    // // book.setAuthor("XYZ");
    // return this.bookService.getAllBooks();
    // }

    // to get the status code or tu return the status code with the data we use
    // response entity
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        // Book book = new Book();
        // book.setId(1234);
        // book.setTitle("Java Complete Reference");
        // book.setAuthor("XYZ");
        List<Book> list = null;
        try {
            list = this.bookService.getAllBooks();
            if (list.size() <= 0) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.of(Optional.of(list));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book b = null;

        try {
            b = this.bookService.getBookById(id);
            if (b == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.of(Optional.of(b));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
        try {
            this.bookService.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // here we use @req body to send data in json format
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
        try {
            this.bookService.updateBook(book, bookId);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}
