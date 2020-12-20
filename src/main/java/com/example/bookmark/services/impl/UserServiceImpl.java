package com.example.bookmark.services.impl;

import com.example.bookmark.common.utils.StringUtils;
import com.example.bookmark.dto.request.UserCreateDto;
import com.example.bookmark.dto.request.UserLoginRequestDto;
import com.example.bookmark.entities.Token;
import com.example.bookmark.entities.User;
import com.example.bookmark.repos.UserRepository;
import com.example.bookmark.services.TokenService;
import com.example.bookmark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  TokenService tokenService;

  @Override
  public boolean authenticateUserToken(String token) {
    if(StringUtils.isNullOrEmpty(token)) {
      return false;
    }
    try {
      Token t = tokenService.getToken(token);
      return t != null;
    }
    catch (Exception e) {
      System.out.println("Error while authenticating token !");
    }
    return false;
  }

  @Override
  public boolean createUser(UserCreateDto userCreateDto) throws Exception {
    if(userCreateDto == null || userCreateDto.getEmail() == null || userCreateDto.getEmail().isEmpty()) {
      throw new Exception("Error in user create request !");
    }
    User existingUser = userRepository.getTopByEmail(userCreateDto.getEmail());
    if(existingUser != null) {
      throw new Exception("User already exists !");
    }
    String encryptedPassword = new StringBuilder(userCreateDto.getPassword()).reverse().toString();
    User newUser = new User().toBuilder()
            .email(userCreateDto.getEmail())
            .firstName(userCreateDto.getFirstName())
            .lastName(userCreateDto.getLastName())
            .password(encryptedPassword)
            .build();
    userRepository.saveAndFlush(newUser);
    return true;
  }

  @Override
  public String loginUser(UserLoginRequestDto userLoginRequestDto) throws Exception {
    if(userLoginRequestDto == null || StringUtils.isNullOrEmpty(userLoginRequestDto.getEmail()) ||
            StringUtils.isNullOrEmpty(userLoginRequestDto.getPassword())) {
      throw new Exception("Invalid Credentials !");
    }
    User user = userRepository.getTopByEmail(userLoginRequestDto.getEmail());
    if(user == null) {
      throw new Exception("User not found !");
    }
    if(user.getPassword().equals(new StringBuilder(userLoginRequestDto.getPassword()).reverse().toString())) {
      String encryptedPass = user.getEmail() + ":" + user.getPassword();
      encryptedPass = Base64.getEncoder().encodeToString(encryptedPass.getBytes());
      tokenService.saveToken(encryptedPass);
      return encryptedPass;
    }
    throw new Exception("Invalid password !!");
  }

  @Override
  public boolean logoutUser(String token) throws Exception {
    if(StringUtils.isNullOrEmpty(token)) {
      throw new Exception("Logout token error !!");
    }
    tokenService.deleteToken(token);
    return true;
  }
}
