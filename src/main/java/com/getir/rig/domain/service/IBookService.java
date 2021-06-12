package com.getir.rig.domain.service;

import com.getir.rig.domain.model.book.Book;

import java.util.List;

public interface IBookService {
    Book create(Book book);

    Book update(Book book);

    List<Book> update(List<Book> books);

    List<Book> getBooks(List<Long> bookIds);

}
