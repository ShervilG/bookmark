package com.example.bookmark.services.impl;

import com.example.bookmark.entities.Email;
import com.example.bookmark.repos.EmailRepository;
import com.example.bookmark.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  @Autowired
  EmailRepository emailRepository;

  @Override
  public Long getIdByEmail(String email) {
    return emailRepository.getIdByEmail(email);
  }

  @Override
  public void saveEmail(Email email) {
    emailRepository.saveAndFlush(email);
  }
}
