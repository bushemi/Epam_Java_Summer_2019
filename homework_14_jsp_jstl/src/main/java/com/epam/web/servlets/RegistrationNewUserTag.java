package com.epam.web.servlets;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class RegistrationNewUserTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        try {
                pageContext.getOut().write("Регистрация нового пользователя.");

        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }



}
