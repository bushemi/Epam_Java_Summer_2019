package com.bushemi.converters;

import com.bushemi.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(basePackages = {"com.bushemi.controller"})
public class ControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response exceptionHandler(Exception e) {
        return new Response(500, e.getMessage());
    }
}
