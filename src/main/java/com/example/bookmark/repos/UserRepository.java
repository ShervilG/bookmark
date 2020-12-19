package com.example.bookmark.repos;

import com.example.bookmark.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from users where id = ?1")
    User getUserById(Long id);

    User getTopByEmail(String email);
}
