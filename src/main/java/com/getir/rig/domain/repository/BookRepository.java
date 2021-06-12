package com.getir.rig.domain.repository;

import com.getir.rig.domain.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
