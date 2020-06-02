package com.abc.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

@ControllerAdvice   // 切面，指定处理器方法为连接点
public class ParamValidAdvice {

    @ExceptionHandler
    public ResponseEntity<String> valideHandle(WebExchangeBindException ex) {
        return new ResponseEntity<String>(exceptionToMessage(ex), HttpStatus.BAD_REQUEST);
    }

    private String exceptionToMessage(WebExchangeBindException ex) {
        return ex.getFieldErrors()
                .stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }

    // fluent风格
}
