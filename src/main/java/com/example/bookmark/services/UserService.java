package com.example.bookmark.services;

import com.example.bookmark.dto.request.UserCreateDto;
import com.example.bookmark.dto.request.UserLoginRequestDto;

public interface UserService {

    boolean authenticateUserToken(String token);

    boolean createUser(UserCreateDto userCreateDto) throws Exception;

    String loginUser(UserLoginRequestDto userLoginRequestDto) throws Exception;

    boolean logoutUser(String token) throws Exception;
}
