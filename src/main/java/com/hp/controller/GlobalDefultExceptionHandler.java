package com.hp.controller;


import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalDefultExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        Map map = new HashMap();
        map.put("state", 40001);
        map.put("msg", ex.getMessage());
        return map;
    }



    //json格式
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map errorHandler(MethodArgumentNotValidException ex) {
        StringBuilder errorMsg = new StringBuilder();
        BindingResult re = ex.getBindingResult();
        for (ObjectError error : re.getAllErrors()) {
            errorMsg.append(error.getDefaultMessage()).append(",");
        }
        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
        Map map = new HashMap();
        map.put("state", 40002);
        map.put("msg", errorMsg.toString());
        return map;
    }


//    //  //表单格式
//    @ExceptionHandler(value = BindException.class)
//    public Map errorHandler(BindException ex) {
//        BindingResult result = ex.getBindingResult();
//        StringBuilder errorMsg = new StringBuilder();
//        for (ObjectError error : result.getAllErrors()) {
//            errorMsg.append(error.getDefaultMessage()).append(",");
//        }
//        errorMsg.delete(errorMsg.length() - 1, errorMsg.length());
//        Map map = new HashMap();
//        map.put("state", 40003);
//        map.put("msg", errorMsg.toString());
//        return map;
//    }
}
