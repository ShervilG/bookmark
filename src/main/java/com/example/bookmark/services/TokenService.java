package com.example.bookmark.services;

import com.example.bookmark.entities.Token;

public interface TokenService {

    Token getToken(String token);

    void deleteOldTokens();

    void saveToken(String token);

    void deleteToken(String token);
}
