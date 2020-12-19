package com.example.bookmark.services;

import com.example.bookmark.entities.Token;

public interface TokenService {

    public Token getToken(String token);

    public void deleteOldTokens();
}
