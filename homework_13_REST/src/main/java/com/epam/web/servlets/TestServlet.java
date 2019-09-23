package com.epam.web.servlets;

import com.epam.model.TestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_OK;

public class TestServlet extends AbstractTestServlet {
    private static final Logger LOG = LoggerFactory.getLogger("TestServlet");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("doGet from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        String pathInfo = req.getPathInfo();
        LOG.info("doGet {}", pathInfo);
        if (pathInfo.startsWith("/")) {
            pathInfo = pathInfo.substring(1);
        }
        long id;
        try (PrintWriter out = resp.getWriter()) {
            try {
                id = Long.parseLong(pathInfo);
            } catch (NumberFormatException e) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println("после слэша \"/\" должна быть цифра - ид теста");
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            TestEntity entity = service.findById(id);
            if (isNull(entity)) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println("Тест с указанным ид не найден");
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            resp.setContentType(CONTENT_TYPE_JSON);
            out.println(converter.toJson(entity));
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
            TestEntity testEntity;
            try {
                testEntity = converter.fromJson(fromRequest);
            } catch (NumberFormatException e) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println(e.getMessage());
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            long savedId = service.save(testEntity);
            out.println(savedId);
        }
        resp.setStatus(SC_OK);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOG.info("doPut from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        try (PrintWriter out = resp.getWriter()) {
            String fromRequest = readFromRequest(req);
            TestEntity testEntity;
            try {
                testEntity = converter.fromJson(fromRequest);
            } catch (NumberFormatException e) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println(e.getMessage());
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            service.update(testEntity);
        }
        resp.setStatus(SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //delete
        LOG.info("doDelete from session id = {}", req.getSession().getId());
        resp.setCharacterEncoding(encoding);
        String pathInfo = req.getPathInfo();
        LOG.info("doGet {}", pathInfo);
        if (pathInfo.startsWith("/")) {
            pathInfo = pathInfo.substring(1);
        }
        long id;
        try (PrintWriter out = resp.getWriter()) {
            try {
                id = Long.parseLong(pathInfo);
            } catch (NumberFormatException e) {
                resp.setContentType(CONTENT_TYPE_TEXT);
                out.println("после слэша \"/\" должна быть цифра - ид теста");
                resp.setStatus(SC_BAD_REQUEST);
                return;
            }
            if (validateIsTestExist(resp, id, out)) return;
            service.delete(id);
        }
        resp.setStatus(SC_OK);
    }

}
