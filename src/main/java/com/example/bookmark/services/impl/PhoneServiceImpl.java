package com.example.bookmark.services.impl;

import com.example.bookmark.entities.PhoneNumber;
import com.example.bookmark.repos.PhoneRepository;
import com.example.bookmark.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {

  @Autowired
  PhoneRepository phoneRepository;

  @Override
  public Long getIdByPhoneNumber(String phoneNumber) {
    return phoneRepository.getIdByPhoneNumber(phoneNumber);
  }

  @Override
  public void savePhoneNumber(PhoneNumber phoneNumber) {
    phoneRepository.saveAndFlush(phoneNumber);
  }
}
