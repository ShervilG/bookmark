package com.example.bookmark.services;

import com.example.bookmark.dto.request.BookCreateDto;
import com.example.bookmark.dto.response.GenericResponse;
import com.example.bookmark.entities.Book;

import java.util.List;

public interface BookService {

  List<Book> getAllActiveBooks();

  List<Book> getAllByParam(String paramList);

  GenericResponse addBook(BookCreateDto bookCreateDto) throws Exception;
}
