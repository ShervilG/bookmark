package com.example.bookmark.services;

import com.example.bookmark.dto.request.ContactRequest;
import java.util.List;

public interface PhoneBookService {

  void syncPhoneBook(List<ContactRequest> contactRequestList, Long userId);
}
