package com.epam.web.servlets;

import com.epam.model.TestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class MultiTestsServlet extends AbstractTestServlet {
    private static final Logger LOG = LoggerFactory.getLogger("MultiTestsServlet");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("doGet from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        resp.setContentType(CONTENT_TYPE_JSON);

        String allTests = service.findAll()
                .stream()
                .map(converter::toJson)
                .collect(Collectors.joining(","));

        try (PrintWriter out = resp.getWriter()) {
            out.println("[" + allTests + "]");
        }
        resp.setStatus(SC_OK);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) {
        LOG.info("doHead from session id = {}", req.getSession().getId());
        resp.setStatus(SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("doPost from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        try (PrintWriter out = resp.getWriter()) {
            String fromRequest = readFromRequest(req);
            fromRequest = validateAndDeleteBrackets(resp, out, fromRequest);
            if (isNull(fromRequest)) return;
            String[] jsons = fromRequest.split("},");
            List<TestEntity> testEntities = new ArrayList<>();
            try {
                Stream.of(jsons)
                        .map(converter::fromJson)
                        .forEach(testEntities::add);
            } catch (NumberFormatException e) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println(e.getMessage());
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            service.saveAll(testEntities);
        }
        resp.setStatus(SC_OK);
    }

    private String validateAndDeleteBrackets(HttpServletResponse resp, PrintWriter out, String fromRequest) {
        if (fromRequest.startsWith("[") && fromRequest.endsWith("]")) {
            fromRequest = fromRequest.substring(1, fromRequest.length() - 1);
        } else {
            resp.setContentType(CONTENT_TYPE_TEXT);
            out.println("Объекты типа \"test\" должны быть внутри квадратных скобок []");
            resp.setStatus(SC_BAD_REQUEST);
            return null;
        }
        return fromRequest;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        LOG.info("doPut from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        try (PrintWriter out = resp.getWriter()) {
            String fromRequest = readFromRequest(req);
            fromRequest = validateAndDeleteBrackets(resp, out, fromRequest);
            if (isNull(fromRequest)) return;
            String[] jsons = fromRequest.split("},");
            List<TestEntity> testEntities = new ArrayList<>();
            try {
                Stream.of(jsons)
                        .map(converter::fromJson)
                        .forEach(testEntities::add);
            } catch (NumberFormatException e) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println(e.getMessage());
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            service.updateAll(testEntities);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("doDelete from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        try (PrintWriter out = resp.getWriter()) {
            resp.setContentType(CONTENT_TYPE_TEXT);
            out.println("Удалять несколько тестов одновременно запрещено. Удаляйте по одному.");
            resp.setStatus(SC_BAD_REQUEST);
        }
    }
}
