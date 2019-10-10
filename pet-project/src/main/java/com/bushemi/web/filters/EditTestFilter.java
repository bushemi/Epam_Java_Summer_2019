package com.bushemi.web.filters;

import com.bushemi.model.SubjectDto;
import com.bushemi.model.TestForSessionDto;
import com.bushemi.service.implementations.SubjectServiceImpl;
import com.bushemi.service.implementations.TestServiceImpl;
import com.bushemi.service.interfaces.SubjectService;
import com.bushemi.service.interfaces.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class EditTestFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger("EditTestFilter");
    private final SubjectService subjectService = new SubjectServiceImpl();
    private final TestService testService = new TestServiceImpl();

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
