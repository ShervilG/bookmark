package com.example.bookmark.services;

import com.example.bookmark.entities.PhoneNumber;

public interface PhoneService {

  Long getIdByPhoneNumber(String phoneNumber);

  void savePhoneNumber(PhoneNumber phoneNumber);
}
