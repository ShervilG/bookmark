package com.example.bookmark.controllers;

import com.example.bookmark.dto.request.ContactRequest;
import com.example.bookmark.dto.response.GenericResponse;
import com.example.bookmark.services.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneBookController {

  @Autowired
  PhoneBookService phoneBookService;

  @Async
  @PostMapping("/phonebook/sync")
  private GenericResponse syncPhoneBook(@RequestBody List<ContactRequest> contactRequestList, @RequestParam
          (required = true) Long userId) {
    phoneBookService.syncPhoneBook(contactRequestList, userId);
    return new GenericResponse("Request queued !!");
  }
}
