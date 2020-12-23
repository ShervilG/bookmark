package com.example.bookmark.services.impl;

import com.example.bookmark.dto.request.ContactRequest;
import com.example.bookmark.entities.Email;
import com.example.bookmark.entities.PhoneBook;
import com.example.bookmark.entities.PhoneNumber;
import com.example.bookmark.repos.PhoneBookRepository;
import com.example.bookmark.services.EmailService;
import com.example.bookmark.services.PhoneBookService;
import com.example.bookmark.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneBookServiceImpl implements PhoneBookService {

  @Autowired
  PhoneService phoneService;

  @Autowired
  EmailService emailService;

  @Autowired
  PhoneBookRepository phoneBookRepository;

  @Async
  @Override
  public void syncPhoneBook(List<ContactRequest> contactRequestList, Long userId) {
    if(contactRequestList == null || userId == null || contactRequestList.size() == 0) {
      return;
    }
    PhoneBook existingPhoneBook;
    try {
      existingPhoneBook = getPhoneBookByUserId(userId);
      if(existingPhoneBook == null) {
        throw new Exception();
      }
      return;
    } catch (Exception e) {}
    List<Long> phoneNumberList = new ArrayList<>();
    List<Long> emailList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    contactRequestList.forEach(contact -> {
      try {
        Long prevNumber, prevEmail;
        try {
          prevNumber = phoneService.getIdByPhoneNumber(contact.getPhoneNumber());
          if(prevNumber == null) {
            throw new Exception();
          }
          phoneNumberList.add(prevNumber);
        } catch (Exception exp) {
          PhoneNumber number = new PhoneNumber().toBuilder()
                  .phoneNumber(contact.getPhoneNumber())
                  .build();
          phoneService.savePhoneNumber(number);
          phoneNumberList.add(number.getId());
        }
        try {
          prevEmail = emailService.getIdByEmail(contact.getEmail());
          if(prevEmail == null) {
            throw new Exception();
          }
          emailList.add(prevEmail);
        } catch (Exception exp) {
          Email email = new Email().toBuilder()
                  .email(contact.getEmail())
                  .build();
          emailService.saveEmail(email);
          emailList.add(email.getId());
        }
        nameList.add(contact.getName());
      } catch (Exception e) {}
    });
    existingPhoneBook = new PhoneBook().toBuilder()
            .phoneNumbers(phoneNumberList)
            .emails(emailList)
            .names(nameList)
            .userId(userId)
            .build();
    phoneBookRepository.saveAndFlush(existingPhoneBook);
  }

  private PhoneBook getPhoneBookByUserId(Long userId) {
    return phoneBookRepository.getPhoneBookByUserId(userId);
  }
}
