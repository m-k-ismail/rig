package com.getir.rig.book.controller;

import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.model.book.BookREQ;
import com.getir.rig.domain.model.book.BookRES;
import com.getir.rig.domain.service.IBookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookApiImplTest {

    @Mock
    private IBookService bookService;

    private BookApiImpl bookApi;

    @Before
    public void setUp() {
        bookApi = new BookApiImpl(bookService);
    }

    @Test
    public void should_post_book_successfully() {
        // Given
        BookREQ bookREQ = createBookREQ();
        Book bookWithId = createBookWithId();
        when(bookService.create(bookREQ.getData())).thenReturn(bookWithId);

        // When
        ResponseEntity<BookRES> bookRESResponseEntity = bookApi.postBook(bookREQ);

        // Then
        Assert.assertEquals(bookWithId.getId(), bookRESResponseEntity.getBody().getData().getId());
        Assert.assertEquals(HttpStatus.CREATED, bookRESResponseEntity.getStatusCode());
    }


    @Test
    public void should_put_book_successfully() {
        // Given
        BookREQ bookREQ = creatBookREQWithId();
        Book bookWithLessStockAmount = createBookWithLessStockAmount();
        when(bookService.update(bookREQ.getData())).thenReturn(bookWithLessStockAmount);

        // When
        ResponseEntity<BookRES> bookRESResponseEntity = bookApi.putBook(bookREQ);

        // Then
        Book data = bookRESResponseEntity.getBody().getData();
        Assert.assertEquals(bookWithLessStockAmount.getStock(), data.getStock());
        Assert.assertEquals(bookWithLessStockAmount.getId(), data.getId());
        Assert.assertEquals(HttpStatus.CREATED, bookRESResponseEntity.getStatusCode());
    }


    private BookREQ createBookREQ() {
        Book book = createBook();

        BookREQ bookREQ = new BookREQ();
        bookREQ.setData(book);

        return bookREQ;
    }

    private BookREQ creatBookREQWithId() {
        Book book = createBookWithId();

        BookREQ bookREQ = new BookREQ();
        bookREQ.setData(book);

        return bookREQ;
    }

    private Book createBook() {
        Book book = new Book();

        book.setName("Test Book");
        book.setStock(5);

        return book;
    }

    private Book createBookWithId() {
        Book book = createBook();
        book.setId(1L);
        return book;
    }

    private Book createBookWithLessStockAmount() {
        Book book = createBook();
        book.setStock(book.getStock()-1);
        return book;
    }
}
