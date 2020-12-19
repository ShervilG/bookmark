package com.example.bookmark.controllers;

import com.example.bookmark.auth.ClientAuthentication;
import com.example.bookmark.dto.request.UserCreateDto;
import com.example.bookmark.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@ClientAuthentication
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired UserService userService;

    @GetMapping("/authenticate-token")
    public Boolean authenticateUser(@RequestHeader(name = "user_token") String userToken) {
        return userService.authenticateUserToken(userToken);
    }

    @PostMapping("/create")
    public Boolean createUser(@RequestBody UserCreateDto userCreateDto) {
        return true;
    }

    @DeleteMapping("/delete")
    public void deleteUser() {}
}
