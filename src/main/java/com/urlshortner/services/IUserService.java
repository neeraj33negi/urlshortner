package com.urlshortner.services;

import com.urlshortner.entities.User;
import com.urlshortner.models.requests.SignUpRequest;
import com.urlshortner.models.responses.UserResponse;

public interface IUserService {

    UserResponse findByEmail(final String email, final String password);

    User findByToken(final String token);

    UserResponse signup(final SignUpRequest signUpRequest);

    Boolean userExists(final String email);

}
