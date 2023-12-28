package com.urlshortner.controllers;

import com.urlshortner.data.reposervices.IUserRepoService;
import com.urlshortner.data.reposervices.impl.UserRepoService;
import com.urlshortner.models.requests.LoginRequest;
import com.urlshortner.models.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UsersController {

    @Autowired
    private IUserRepoService userService;


    @PostMapping(value = "/login")
    public UserResponse getUserByNameAndPassword(@RequestBody final LoginRequest loginRequest) {
        return userService.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
