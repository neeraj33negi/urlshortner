package com.urlshortner.data.reposervices;

import com.urlshortner.data.repositories.UserRepository;
import com.urlshortner.entities.User;
import com.urlshortner.mappers.UserMapper;
import com.urlshortner.models.requests.SignUpRequest;
import com.urlshortner.models.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Integer PASSWORD_STRENGTH = 10;

    public UserResponse findByEmailAndPassword(final String email, final String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Not found");
        }
        User user = optionalUser.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return UserMapper.INSTANCE.userToUserResponse(user);
        }
        throw new UsernameNotFoundException("Bad Credentials");
    }

    public User findByToken(final String token) {
        Optional<User> optionalUser = userRepository.findByAuthenticationToken(token);
        if (Boolean.FALSE.equals(optionalUser.isEmpty())) {
            return optionalUser.get();
        }
        return null;
    }

    public Boolean existsByEmail(final String email) {
        return Objects.equals(Boolean.FALSE, userRepository.findByEmail(email).isEmpty());
    }

    public Boolean existsById(final UUID uuid) {
        return Objects.equals(Boolean.FALSE, userRepository.findById(uuid).isEmpty());
    }

    public Boolean existsByAuthToken(final String token) {
        return Objects.equals(Boolean.FALSE, userRepository.findByAuthenticationToken(token).isEmpty());
    }

    public UserResponse create(final SignUpRequest signUpRequest) {
       User user = UserMapper.INSTANCE.signUpRequestToUser(signUpRequest);
       userRepository.save(user);
       return UserMapper.INSTANCE.userToUserResponse(user);
    }

}
