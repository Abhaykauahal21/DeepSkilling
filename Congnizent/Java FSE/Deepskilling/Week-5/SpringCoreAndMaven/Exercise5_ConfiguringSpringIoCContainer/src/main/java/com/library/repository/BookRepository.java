package com.library.repository;

public class BookRepository {

    public void save(String bookName) {
        System.out.println("BookRepository: Saving book -> " + bookName);
    }

    public void findAll() {
        System.out.println("BookRepository: Finding all books...");
    }
}