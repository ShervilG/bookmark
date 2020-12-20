package com.example.bookmark.controllers;

import com.example.bookmark.auth.ClientAuthentication;
import com.example.bookmark.dto.request.UserCreateDto;
import com.example.bookmark.dto.request.UserLoginRequestDto;
import com.example.bookmark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ClientAuthentication
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/authenticate-token")
    public boolean authenticateUser(@RequestHeader(name = "user_token") String userToken) {
        return userService.authenticateUserToken(userToken);
    }

    @PostMapping("/create")
    public boolean createUser(@RequestBody UserCreateDto userCreateDto) throws Exception {
        return userService.createUser(userCreateDto);
    }

    @DeleteMapping("/delete")
    public void deleteUser() {}

    @GetMapping("/login")
    public String loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto) throws Exception {
        return userService.loginUser(userLoginRequestDto);
    }

    @GetMapping("/logout")
    public boolean logoutUser(@RequestBody String token) throws Exception {
        return userService.logoutUser(token);
    }
}
