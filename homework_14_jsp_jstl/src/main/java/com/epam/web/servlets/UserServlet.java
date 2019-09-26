package com.epam.web.servlets;

import com.epam.RegistrationValidationException;
import com.epam.dao.UserCreating;
import com.epam.service.UserParserService;
import com.epam.service.UserService;
import com.epam.service.UserServiceImpl;
import com.epam.service.UserValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static javax.servlet.http.HttpServletResponse.SC_NOT_ACCEPTABLE;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class UserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger("UserServlet");
    private final UserParserService userParserService = new UserParserService();
    private final UserService userService = new UserServiceImpl();
    private final UserValidationService validationService = new UserValidationService();

    private String textFromRequest(HttpServletRequest req) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            reader.lines().forEach(builder::append);
        }
        return builder.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("doPost from user with session id = {}", req.getSession(true).getId());
        String fromRequest = textFromRequest(req);
        UserCreating user = userParserService.fromString(fromRequest);
        try {
            validationService.validateUser(user);
        } catch (RegistrationValidationException e) {
            Map<String, String> errorsMap = e.getErrorsMap();
            errorsMap.forEach((fieldName, message) -> req.getSession().setAttribute(fieldName, message));
            resp.setStatus(SC_NOT_ACCEPTABLE);
            resp.sendRedirect("/registration");
            return;
        }
        if (userService.isUserExist(user.getLogin())) {
            resp.setStatus(SC_NOT_ACCEPTABLE);
            resp.sendRedirect("/error");
        } else {
            userService.save(user);
            resp.setStatus(SC_OK);
            resp.sendRedirect("/start");
        }
    }
}
