package com.example.bookmark.repos;

import com.example.bookmark.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(nativeQuery = true, value = "select * from tokens where token = ?1 limit 1")
    Token getTopByToken(String token);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from tokens where created_at <= now() - interval '1 hours'")
    void deleteOldTokens();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from tokens where token = ?1")
    int deleteTopByToken(String token);
}
