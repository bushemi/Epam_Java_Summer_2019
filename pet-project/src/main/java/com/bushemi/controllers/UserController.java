package com.bushemi.controllers;

import com.bushemi.model.RedirectResponse;
import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.interfaces.SecurityService;
import com.bushemi.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger("UserController");
    private static final String LOGIN_PAGE = "/login";

    private final SecurityService securityService;
    private final UserService userService;

    @Autowired
    public UserController(SecurityService securityService, UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
    }

    @GetMapping("/userProfile")
    public String userProfile() {
        return "userProfile";
    }

    @GetMapping("/allUsers")
    public String allUsers() {
        return "allUsers";
    }

    @PostMapping(path = "/users")
    @ResponseBody
    public RedirectResponse login(@RequestBody UserCreatingDto user, HttpSession session) {
        if (userService.isUserExist(user.getLogin())) {
            session.setAttribute("loginError", "Указанный логин уже занят");
            LOG.info("login is already taken");
            return new RedirectResponse(LOGIN_PAGE);
        } else {
            UserForSessionDto savedUser = userService.save(user);
            addUserInformationToSession(session, savedUser);
            LOG.info("user is  registered!");
            return new RedirectResponse("navigation");
        }
    }

    @PostMapping(path = "/authentication")
    @ResponseBody
    public RedirectResponse authentication(@RequestBody UserCreatingDto user, HttpSession session) {
        UserForSessionDto userForSessionDto;
        try {
            userForSessionDto = securityService.login(user);
        } catch (Exception e) {
            addGeneralErrors(session);
            LOG.error("An error occurs = {}", e);
            return new RedirectResponse(LOGIN_PAGE);
        }

        LOG.info("logged in with user = {}", userForSessionDto);
        addUserInformationToSession(session, userForSessionDto);
        return new RedirectResponse("navigation");
    }

    private void addGeneralErrors(HttpSession session) {
        session.setAttribute("loginError", "Проверьте логин");
        session.setAttribute("passwordError", "Проверьте пароль");
    }

    static void addUserInformationToSession(HttpSession session, UserForSessionDto userForSessionDto) {
        session.setAttribute("login", userForSessionDto.getLogin());
        session.setAttribute("locale", userForSessionDto.getLocale().getShortName());
        session.setAttribute("role", userForSessionDto.getRole().getRoleName());
        session.setAttribute("userId", userForSessionDto.getId());
    }
}
