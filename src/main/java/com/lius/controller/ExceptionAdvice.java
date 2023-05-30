package com.lius.controller;

import com.lius.common.Result;
import com.lius.common.ResultCode;
import com.lius.common.Utils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionAdvice {

    public static class CheckTokenException extends Exception {
        @Override
        public String getMessage() {
            return "token 校验失败";
        }
    }

    /**
     * java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'admin@admin.com' for key 'user_email'
     * <p>
     * 表中字段约束为unique,因为往数据库中写入数据不唯一,所以报错
     * <p>
     *
     * @return 该方法返回的错误信息，关键的是需要返回哪个字段不唯一。如下代码
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<Object> doSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        String message = exception.getMessage();

        if (!message.contains("key")) {
            return new Result<>(false, ResultCode.DATABASE_COL_ERROR.getCode(), exception.getMessage());
        } else {
            // 获取到致使报错的column
            String key = message.substring(message.indexOf("key") + 3).trim();
            // 判断是否有_,如果没有就直接返回key,如果有就将字段变为驼峰命名法的字段表示
            return new Result<Object>(false, ResultCode.DATABASE_COL_ERROR.getCode(), exception.getMessage(), Utils.mapUnderscoreToCamelCase(key));
        }
    }

    @ExceptionHandler(CheckTokenException.class)
    public Result<Object> doCheckTokenException(CheckTokenException exception) {
        return new Result<>(false, ResultCode.CHECK_TOKEN_FAIL.getCode(), exception.getMessage());
    }

    @ExceptionHandler(ExpiredJwtException.class) // token过期错误
    public Result<Object> doTokenExpired(ExpiredJwtException exception) {
        if (exception.getMessage().contains("JWT expired at")) {
            return new Result<>(false, ResultCode.CHECK_TOKEN_EXPIRED_SUCCESS.getCode(), ResultCode.CHECK_TOKEN_EXPIRED_SUCCESS.getMsg());
        } else {
            return new Result<>(false, ResultCode.CHECK_TOKEN_FAIL.getCode(), exception.getMessage());
        }
    }

    @ExceptionHandler(JwtException.class) // token为空错误
    public Result<Object> doTokenExpired(JwtException exception) {
        return new Result<>(false, ResultCode.CHECK_TOKEN_ERROR.getCode(), ResultCode.CHECK_TOKEN_ERROR.getMsg());
    }


}
