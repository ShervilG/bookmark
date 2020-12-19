package com.example.bookmark.services.impl;

import com.example.bookmark.dto.request.BookCreateDto;
import com.example.bookmark.dto.response.GenericResponse;
import com.example.bookmark.entities.Book;
import com.example.bookmark.repos.BookRepository;
import com.example.bookmark.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

  @Autowired
  BookRepository bookRepository;

  @Override
  public List<Book> getAllActiveBooks() {
    try {
      return bookRepository.findAllAvailable();
    } catch (Exception e) {
      System.out.println("Error occured while fetching all available books !");
      return new ArrayList<>();
    }
  }

  @Override
  public GenericResponse addBook(BookCreateDto bookCreateDto) throws Exception {
    try {
      Book book = bookRepository.findTopByNameAndAuthor(bookCreateDto.getName(), bookCreateDto.getAuthor());
      if(book == null) {
        Book newBook = new Book().toBuilder()
                .amount(bookCreateDto.getQty())
                .name(bookCreateDto.getName())
                .price(bookCreateDto.getPrice())
                .author(bookCreateDto.getAuthor())
                .type(bookCreateDto.getType())
                .build();
        bookRepository.saveAndFlush(newBook);
      } else {
        book.setAmount(bookCreateDto.getQty() + book.getAmount());
        book.setPrice(bookCreateDto.getPrice() + book.getPrice());
        bookRepository.saveAndFlush(book);
      }
      return new GenericResponse("Book Added.");
    } catch (Exception e) {
      System.out.println("Error occured while fetching all available books !");
      throw new Exception("Error occured while adding book !!");
    }
  }

  @Override
  public List<Book> getAllByParam(String param) {
    try {
      return bookRepository.findAllByType(param);
    } catch (Exception e) {
      System.out.println("Error occured while fetching books by param !");
      return new ArrayList<>();
    }
  }
}
