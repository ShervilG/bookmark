package com.example.bookmark.services.impl;

import com.example.bookmark.dto.request.UserCreateDto;
import com.example.bookmark.entities.Token;
import com.example.bookmark.entities.User;
import com.example.bookmark.repos.UserRepository;
import com.example.bookmark.services.TokenService;
import com.example.bookmark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  TokenService tokenService;

  @Override
  public Boolean authenticateUserToken(String token) {
    if(token == null || StringUtils.isEmpty(token)) {
      return false;
    }
    try {
      String decodedToken = new String(Base64.getDecoder().decode(token.getBytes()));
      User user = userRepository.getUserById(Long.valueOf(decodedToken.split(":")[0]));
      if (user == null) {
        return false;
      }
      Token t = tokenService.getToken(token);
      return t != null;
    }
    catch (Exception e) {
      System.out.println("Error while authenticating token !");
    }
    return false;
  }

  @Override
  public Boolean createUser(UserCreateDto userCreateDto) { return null; }
}
