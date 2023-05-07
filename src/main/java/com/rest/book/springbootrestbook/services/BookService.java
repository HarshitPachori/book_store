package com.rest.book.springbootrestbook.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.book.springbootrestbook.dao.BookRepo;
import com.rest.book.springbootrestbook.models.Book;

@Component
public class BookService {
    // private static List<Book> list = new ArrayList<>();

    // dummy data or fake service / db
    // static {
    // list.add(new Book(12, "Java Complete Reference", "XYZ"));
    // list.add(new Book(14, "Java Collection FrameWork", "ABC"));
    // list.add(new Book(16, "Java Spring FrameWork", "UVW"));
    // }

    @Autowired
    private BookRepo bookRepo;

    // get All books
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepo.findAll();
        return list;
    }

    // get single book by id
    public Book getBookById(int id) {
        Book book = null;
        // by using stream api
        // stream lagayi then filter kiya then condition lagayi
        try {
            // book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = this.bookRepo.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // adding the book
    public Book addBook(Book book) {
        Book res = bookRepo.save(book);
        // list.add(book);
        return res;
    }

    // delete book
    public void deleteBook(int bookId) {
        // collect krlega jahan id bid same nahi honge wahan se books ko aur list me
        // place krdega
        // list = list.stream().filter(book -> book.getId() !=
        // bookId).collect(Collectors.toList());
        bookRepo.deleteById(bookId);
    }

    // update book
    public void updateBook(Book book, int bookId) {
        // map is used when we have to work on each item while filter only filters the
        // whole list at once
        // list = list.stream().map(b -> {
        // if (b.getId() == bookId) {
        // b.setAuthor(book.getAuthor());
        // b.setTitle(book.getTitle());
        // }
        // return b;
        // }).collect(Collectors.toList());

        book.setId(bookId);
        bookRepo.save(book);
    }
}
