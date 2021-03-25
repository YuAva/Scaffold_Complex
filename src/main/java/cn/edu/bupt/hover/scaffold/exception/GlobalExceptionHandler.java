package cn.edu.bupt.hover.scaffold.exception;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice({"cn.edu.bupt.hover.scaffold.controller"})
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest req) {
        System.out.println(e);
        return "{\"message\": \"exception\"}";
    }
}
