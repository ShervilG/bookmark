package com.example.bookmark.repos;

import com.example.bookmark.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByType(String type);

  @Query(value = "SELECT * FROM books WHERE amount > 0 ORDER BY id DESC", nativeQuery = true)
  List<Book> findAllAvailable();

  Book findTopByNameAndAuthor(String name, String author);
}
