package com.example.bookmark.services.impl;

import com.example.bookmark.entities.Token;
import com.example.bookmark.repos.TokenRepository;
import com.example.bookmark.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired TokenRepository tokenRepository;

    @Override
    public Token getToken(String token) {
        return tokenRepository.getTopByToken(token);
    }

    @Override
    public void deleteOldTokens() {
        tokenRepository.deleteOldTokens();
    }

    @Override
    public void saveToken(String token) {
        tokenRepository.saveAndFlush(new Token().toBuilder().createdAt(new Date(System.currentTimeMillis())).token(token)
                .build());
    }

    @Override
    public int deleteToken(String token) {
        return tokenRepository.deleteTopByToken(token);
    }
}
