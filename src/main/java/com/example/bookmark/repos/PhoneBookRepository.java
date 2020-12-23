package com.example.bookmark.repos;

import com.example.bookmark.entities.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

  @Query(nativeQuery = true, value = "select * from phone_book where user_id = ?1")
  PhoneBook getPhoneBookByUserId(Long userId);
}
