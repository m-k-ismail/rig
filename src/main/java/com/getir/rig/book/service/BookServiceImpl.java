package com.getir.rig.book.service;

import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.repository.BookRepository;
import com.getir.rig.domain.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public List<Book> update(List<Book> books) {

        return bookRepository.saveAll(books);
    }

    @Override
    public List<Book> getBooks(List<Long> bookIds) {
        return bookRepository.findAllById(bookIds);
    }


}
