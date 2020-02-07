package com.hqgml.small.config;

import com.hqgml.small.exception.ExceptionEnums;
import com.hqgml.small.exception.ExceptionResult;
import com.hqgml.small.exception.XxException;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Devil
 * @date 2019/12/18 21:01
 */
@SuppressWarnings("all")
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResult> Exception(Exception e) {
        if (e instanceof XxException) {
            /**
             * 普通异常
             */
            return ResponseEntity.status(200).body(new ExceptionResult(((XxException) e).getExceptionEnums()));
        }
        else if (e instanceof TooManyResultsException) {
            /**
             * 结果集不唯一
             */
            return ResponseEntity.status(200).body(new ExceptionResult(ExceptionEnums.RESOUT_NOT_ONE));
        }else {
            e.printStackTrace();
        }
        return null;
    }


}
