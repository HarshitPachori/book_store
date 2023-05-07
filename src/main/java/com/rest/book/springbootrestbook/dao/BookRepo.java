package com.rest.book.springbootrestbook.dao;

import org.springframework.data.repository.CrudRepository;

import com.rest.book.springbootrestbook.models.Book;

public interface BookRepo extends CrudRepository<Book, Integer> {
    public Book findById(int bookId);
}
