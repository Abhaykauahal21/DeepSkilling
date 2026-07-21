package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("BookService: BookRepository injected via setter.");
    }

    public void addBook(String bookName) {
        System.out.println("BookService: Adding book -> " + bookName);
        bookRepository.save(bookName);
    }

    public void listBooks() {
        System.out.println("BookService: Listing all books...");
        bookRepository.findAll();
    }
}