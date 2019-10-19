package com.bushemi.web.interceptors;

import com.bushemi.service.interfaces.UrlSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger("SecurityInterceptor");

    @Autowired
    private UrlSecurityService urlSecurityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role = (String) request.getSession(true).getAttribute("role");
        String uri = request.getRequestURI();
        if (!urlSecurityService.doesRoleHasAccessToUrl(role, uri, request.getMethod())) {
            LOG.info("this uri {} is forbidden for this role {}", uri, role);
            response.sendRedirect("/404");
            return false;
        }
        return true;
    }

}
