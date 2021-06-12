package com.getir.rig.book.controller;

import com.getir.rig.api.BookApi;
import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.model.book.BookREQ;
import com.getir.rig.domain.model.book.BookRES;
import com.getir.rig.domain.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookApiImpl implements BookApi {

    private IBookService bookService;

    @Autowired
    public BookApiImpl(IBookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public ResponseEntity<BookRES> postBook(BookREQ body) {
        Book book = bookService.create(body.getData());

        BookRES bookRES = new BookRES(book);

        return new ResponseEntity<>(bookRES, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookRES> putBook(BookREQ body) {
        Book book = bookService.update(body.getData());

        BookRES bookRES = new BookRES(book);

        return new ResponseEntity<>(bookRES, HttpStatus.CREATED);
    }
}
