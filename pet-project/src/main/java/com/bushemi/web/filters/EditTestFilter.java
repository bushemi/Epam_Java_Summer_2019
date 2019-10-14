package com.bushemi.web.filters;

import com.bushemi.model.SubjectDto;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.service.interfaces.SubjectService;
import com.bushemi.service.interfaces.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
public class EditTestFilter implements Filter {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TestService testService;

    public EditTestFilter() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String pathInfo = httpRequest.getPathInfo();
        if (nonNull(pathInfo)) {
            long testId = 0;
            testId = parseTestId(pathInfo, testId);
            TestForSessionDto testById = testService.findTestById(testId);
            if (isNull(testById)) {
                httpResponse.sendRedirect("404");
            }
            httpRequest.getSession().setAttribute("test", testById);
        }
        System.out.println("pathInfo = " + pathInfo);
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
        List<SubjectDto> subjectDtos = subjectService.findAll();
        httpRequest.getSession().setAttribute("subjects", subjectDtos);
//        testService.findTestById();
        chain.doFilter(request, response);
    }

    private long parseTestId(String pathInfo, long testId) {
        try {
            testId = Long.parseLong(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return testId;
    }

    @Override
    public void destroy() {

    }
}
