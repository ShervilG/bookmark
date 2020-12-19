package com.example.bookmark.services;

import com.example.bookmark.dto.request.UserCreateDto;

public interface UserService {

    public Boolean authenticateUserToken(String token);

    public Boolean createUser(UserCreateDto userCreateDto);
}
