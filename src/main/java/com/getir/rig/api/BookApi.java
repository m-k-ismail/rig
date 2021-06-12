package com.getir.rig.api;

import com.getir.rig.domain.model.book.BookREQ;
import com.getir.rig.domain.model.book.BookRES;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/rig")
public interface BookApi {

    @PostMapping("/books")
    ResponseEntity<BookRES> postBook(@RequestBody BookREQ body);

    @PutMapping("/books/{bookId}")
    ResponseEntity<BookRES> putBook(@RequestBody BookREQ body);
}
