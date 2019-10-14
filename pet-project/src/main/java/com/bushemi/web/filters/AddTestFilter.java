package com.bushemi.web.filters;

import com.bushemi.model.SubjectDto;
import com.bushemi.service.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class AddTestFilter implements Filter {
    @Autowired
    private SubjectService service;

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        List<SubjectDto> subjectDtos = service.findAll();
        httpRequest.getSession().setAttribute("subjects", subjectDtos);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
