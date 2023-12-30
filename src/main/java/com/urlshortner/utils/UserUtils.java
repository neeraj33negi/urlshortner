package com.urlshortner.utils;

import com.urlshortner.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class UserUtils {

    public static User currentUser() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (Objects.nonNull(user)) {
            return (User)user;
        }
        return null;
    }
}
