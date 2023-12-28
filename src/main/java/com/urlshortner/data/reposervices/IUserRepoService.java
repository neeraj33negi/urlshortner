package com.urlshortner.data.reposervices;

import com.urlshortner.models.responses.UserResponse;

public interface IUserRepoService {

    UserResponse findByEmailAndPassword(final String email, final String password);
}
