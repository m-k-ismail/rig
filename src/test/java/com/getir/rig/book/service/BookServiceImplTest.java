
package com.getir.rig.book.service;

import com.getir.rig.domain.model.book.Book;
import com.getir.rig.domain.repository.BookRepository;
import com.getir.rig.domain.service.IBookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    private static final Long BOOK_ID = 1L;

    @Mock
    private BookRepository bookRepository;

    private BookServiceImpl bookService;

    @Before
    public void setUp() {
        bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    public void should_create_book_successfully() {
        // Give
        Book book = createBook();
        when(bookService.create(any(Book.class))).thenReturn(book);

        // When
        Book bookOutput = bookService.create(book);

        // Then
        Assert.assertEquals(BOOK_ID, bookOutput.getId());
    }

    private Book createBook() {
        Book book = new Book();

        book.setId(BOOK_ID);
        book.setName("Test Book");
        book.setStock(5);
        return book;
    }
}

