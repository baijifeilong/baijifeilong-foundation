package io.github.baijifeilong.foundation.web;

import io.github.baijifeilong.standard.api.domain.ApiFailure;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by BaiJiFeiLong@gmail.com at 2019/1/16 下午2:34
 * <p>
 * SpringMVC全局异常处理
 */
@RestControllerAdvice
@ConditionalOnProperty("foundation.web.global-exception-handler-enabled")
public class GlobalExceptionHandler implements ApiResponseWrapper {

    /**
     * 404异常处理
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure processNotFoundException(NoHandlerFoundException e) {
        return failureOf(String.format("接口(%s)未找到", e.getRequestURL()));
    }

    /**
     * 其他异常处理
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure processException(Throwable e) {
        return failureOf(e);
    }
}
