package com.example.bookmark.services;

import com.example.bookmark.entities.Email;

public interface EmailService {

  Long getIdByEmail(String email);

  void saveEmail(Email email);
}
