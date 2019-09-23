package com.epam.web.servlets;

import com.epam.converters.JsonConverter;
import com.epam.converters.TestJsonConverterImpl;
import com.epam.model.TestEntity;
import com.epam.service.PropertiesLoaderService;
import com.epam.service.TestEntityService;
import com.epam.service.TestEntityServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;

public abstract class AbstractTestServlet extends HttpServlet {
    final TestEntityService service = new TestEntityServiceImpl();
    final JsonConverter<TestEntity> converter = new TestJsonConverterImpl();
    static final String CONTENT_TYPE_JSON = "application/json";
    static final String CONTENT_TYPE_TEXT = "text/html";
    final String encoding;

    AbstractTestServlet() {
        PropertiesLoaderService propertiesLoaderService = new PropertiesLoaderService();
        Properties props = propertiesLoaderService.getProps();
        encoding = props.getProperty("encoding", "utf-8");
    }

    String readFromRequest(HttpServletRequest req) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            reader.lines().forEach(builder::append);
        }
        return builder.toString();
    }

    boolean validateIsTestExist(HttpServletResponse resp, long id, PrintWriter out) {
        boolean testExist = service.isTestExist(id);
        if (!testExist) {
            resp.setContentType(CONTENT_TYPE_TEXT);
            out.println("Тест с указанным ид не найден");
            resp.setStatus(SC_BAD_REQUEST);
            return true;
        }
        return false;
    }
}
