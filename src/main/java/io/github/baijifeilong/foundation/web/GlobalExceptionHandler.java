package io.github.baijifeilong.foundation.web;

import io.github.baijifeilong.foundation.FoundationProperties;
import io.github.baijifeilong.foundation.exception.AbstractBaseException;
import io.github.baijifeilong.standard.api.domain.ApiFailure;
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
public class GlobalExceptionHandler implements ApiResponseWrapper {

    private final FoundationProperties foundationProperties;

    public GlobalExceptionHandler(FoundationProperties foundationProperties) {
        this.foundationProperties = foundationProperties;
    }

    /**
     * 404异常处理
     * <p>
     * 需要配置以下两个选项才能生效:
     * spring.mvc.throw-exception-if-no-handler-found=true
     * spring.resources.add-mappings=false
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure processNotFoundException(NoHandlerFoundException e) {
        FoundationProperties.Web web = foundationProperties.getWeb();
        return failureOf(
                web.getNotFoundExceptionCode(),
                String.format(web.getNotFoundExceptionMessageTemplate(), e.getRequestURL())
        );
    }

    /**
     * 业务异常处理
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(AbstractBaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiFailure processBizException(AbstractBaseException e) {
        return failureOf(e.getCode(), e.getMessage());
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
        FoundationProperties.Web web = foundationProperties.getWeb();
        return failureOf(
                web.getDefaultExceptionCode(),
                String.format(web.getDefaultExceptionMessageTemplate(), e.getMessage())
        );
    }
}
