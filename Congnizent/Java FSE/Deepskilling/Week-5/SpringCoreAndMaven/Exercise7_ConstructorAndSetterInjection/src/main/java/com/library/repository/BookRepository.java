package com.library.repository;

public class BookRepository {

    public void saveBook(String bookName){
        System.out.println("BookRepository : Saving " + bookName);
    }

    public void showBooks(){
        System.out.println("BookRepository : Showing Books");
    }
}