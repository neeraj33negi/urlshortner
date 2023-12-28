package com.urlshortner.data.reposervices.impl;

import com.urlshortner.data.reposervices.IUserRepoService;
import com.urlshortner.data.repositories.UserRepository;
import com.urlshortner.entities.User;
import com.urlshortner.mappers.UserMapper;
import com.urlshortner.models.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRepoService implements IUserRepoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse findByEmailAndPassword(final String email, final String password) {
//        User user = userRepository.findByEmailAndPassword(email, password);
        User user = User.builder().name("Neeraj").build();
        return UserMapper.INSTANCE.userToUserResponse(user);
    }
}
