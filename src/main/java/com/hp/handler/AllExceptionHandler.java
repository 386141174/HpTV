package com.hp.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author liusibo
 * @Date 2023/2/24 11:04
 **/
@ControllerAdvice
@ResponseBody
@Slf4j
public class AllExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandler(HttpServletRequest request, Exception e) {
        log.error("Exception路径:{}", request.getRequestURI());
        //绑定异常是需要明确提示给用户的
        if (e instanceof BindException) {
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            //获取自错误信息
            String msg = errors.get(0).getDefaultMessage();
            //将具体错误信息设置到CodeMsg中返回
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        // 其余异常简单返回为服务器异常
        return new ResponseEntity("系统出现异常,请联系管理员,异常:" + e.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
