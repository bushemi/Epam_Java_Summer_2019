package com.bushemi.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.util.Objects.nonNull;

public class LogoutInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger("LogoutInterceptor");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        if (uri.endsWith("login")) {
            HttpSession session = request.getSession(true);
            if (nonNull(session.getAttribute("role"))) {
                LOG.info("log out by session id {}", session.getId());
                session.invalidate();
            }
        }
        return true;
    }
}
