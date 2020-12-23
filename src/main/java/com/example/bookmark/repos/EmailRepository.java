package com.example.bookmark.repos;

import com.example.bookmark.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

  @Query(nativeQuery = true, value = "select id from emails where email = ?1")
  Long getIdByEmail(String email);
}
