package com.urlshortner.controllers;

import com.urlshortner.models.requests.LoginRequest;
import com.urlshortner.models.requests.SignUpRequest;
import com.urlshortner.models.responses.UserResponse;
import com.urlshortner.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UsersController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    public UserResponse login(@RequestBody final LoginRequest loginRequest) {
        return userService.findByEmail(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@RequestBody final SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userService.userExists(signUpRequest.getEmail()))) {
            return new ResponseEntity<>("Email already taken", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userService.signup(signUpRequest), HttpStatus.OK);
    }
}
