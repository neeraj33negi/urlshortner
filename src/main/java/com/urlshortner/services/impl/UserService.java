package com.urlshortner.services.impl;

import com.urlshortner.data.reposervices.UserRepoService;
import com.urlshortner.entities.User;
import com.urlshortner.models.requests.SignUpRequest;
import com.urlshortner.models.responses.UserResponse;
import com.urlshortner.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Integer TOKEN_LENGTH = 10;

    private static final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    @Override
    public UserResponse findByEmail(final String email, final String password) {
        return userRepoService.findByEmailAndPassword(email, password);
    }

    @Override
    public User findByToken(final String token) {
        return userRepoService.findByToken(token);
    }

    @Override
    public UserResponse signup(SignUpRequest signUpRequest) {
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        signUpRequest.setId(uuidForUser());
        signUpRequest.setAuthenticationToken(authToken());
        return userRepoService.create(signUpRequest);
    }

    @Override
    public Boolean userExists(String email) {
        return userRepoService.existsByEmail(email);
    }

    private UUID uuidForUser() {
        UUID uuid = UUID.randomUUID();
        while (Boolean.TRUE.equals(userRepoService.existsById(uuid))) {
            uuid = UUID.randomUUID();
        }
        return uuid;
    }

    private String authToken() {
        Random random = new Random();
        String token = authTokenHelper(random);
        while (Boolean.TRUE.equals(userRepoService.existsByAuthToken(token))) {
            token = authTokenHelper(random);
        }
        return token;
    }

    public static String authTokenHelper(final Random random) {
        StringBuilder salt = new StringBuilder();
        while (salt.length() < TOKEN_LENGTH) { // length of the random string.
            int index = (int)(random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

}
