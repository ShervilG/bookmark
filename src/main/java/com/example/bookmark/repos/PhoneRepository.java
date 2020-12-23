package com.example.bookmark.repos;

import com.example.bookmark.entities.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneNumber, Long> {

  @Query(nativeQuery = true, value = "select id from phone_numbers where phone_number = ?1")
  Long getIdByPhoneNumber(String phoneNumber);
}
