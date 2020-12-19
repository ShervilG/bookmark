package com.example.bookmark.controllers;

import com.example.bookmark.auth.ClientAuthentication;
import com.example.bookmark.dto.request.BookCreateDto;
import com.example.bookmark.dto.response.GenericResponse;
import com.example.bookmark.entities.Book;
import com.example.bookmark.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

  @Autowired
  BookService bookService;

  @GetMapping("/books/all")
  public List<Book> getAllBooks() {
    return bookService.getAllActiveBooks();
  }

  @PostMapping("/books/add")
  public GenericResponse addBook(@RequestBody BookCreateDto bookCreateDto) throws Exception {
    return bookService.addBook(bookCreateDto);
  }

  @GetMapping("/books")
  public List<Book> getBooksByParam(@RequestParam (required = false) String genre) {
    return bookService.getAllByParam(genre);
  }
}
