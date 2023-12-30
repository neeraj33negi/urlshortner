package com.urlshortner.interceptors;

import com.urlshortner.entities.User;
import com.urlshortner.services.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiInterceptor implements HandlerInterceptor {

    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    private IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String userToken = request.getHeader(AUTH_HEADER);
        if (Boolean.FALSE.equals(userToken.isBlank())) {
            User user = userService.findByToken(userToken);
            SecurityContextHolder.getContext().setAuthentication(new UserAuthentication(user));
        }
        return true;
    }
}
